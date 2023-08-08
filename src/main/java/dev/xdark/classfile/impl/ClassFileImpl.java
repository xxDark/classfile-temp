package dev.xdark.classfile.impl;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.ClassAssembler;
import dev.xdark.classfile.ClassFile;
import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.MemberVisitor;
import dev.xdark.classfile.Option;
import dev.xdark.classfile.ParseOption;
import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.AttributeLocation;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.attribute.AttributeMapperResult;
import dev.xdark.classfile.attribute.AttributeMapperResults;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.internal.AttributeReader;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.BasicConstantPool;
import dev.xdark.classfile.constantpool.Constant;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.ConstantWide;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.ClassVisitor;

import java.util.Set;

public final class ClassFileImpl implements ClassFile {
	private final AttributeMapper attributeMapper;
	private final AttributeMapper prefetchMapper;

	public ClassFileImpl(AttributeMapper attributeMapper, Set<? extends Option> options) {
		this.attributeMapper = attributeMapper;
		if (options.contains(ParseOption.PREFETCH_DEPENDENT_ATTRIBUTES)) {
			prefetchMapper = (location, name) -> {
				AttributeMapperResult info = attributeMapper.getInfo(location, name);
				return info == AttributeInfo.BootstrapMethods ? AttributeMapperResults.SKIP : info;
			};
		} else {
			prefetchMapper = null;
		}
	}

	@Override
	public void read(BinaryInput input, ClassVisitor visitor) {
		ClassReaderImpl classReader = new ClassReaderImpl(input);
		{
			int magic = classReader.readInt();
			if (MAGIC != magic) {
				throw new UncheckedIOException(String.format("Bad classfile magic %d", magic));
			}
		}
		{
			ClassFileVersion version = ClassFileVersion.CODEC.read(classReader);
			classReader.version = version;
			visitor.visitVersion(version);
		}
		// Some attributes need to be visited early as they are
		// dependent between constant pool and their contents.
		// Stupid decision... Really.
		AttributeMapper attributeMapper = this.attributeMapper;
		AttributeMapper prefetchMapper = this.prefetchMapper;
		boolean bootstrapMethodsSeen = false;
		prefetch:
		if (prefetchMapper != null) {
			ClassReader fork = classReader.fork(classReader.position());
			int constantCount = fork.readUnsignedShort() - 1;
			int off = 1;
			long[] cpPositions = new long[constantCount + off];
			while (constantCount-- != 0) {
				int rawTag = fork.readUnsignedByte();
				Tag<?> tag = Tag.byId(rawTag);
				if (tag == null) {
					throw new UncheckedIOException(String.format("Unknown constant pool tag %d", rawTag));
				}
				cpPositions[off++] = fork.position();
				tag.codec().skip(fork);
				if (tag == Tag.InvokeDynamic || tag == Tag.ConstantDynamic) {
					bootstrapMethodsSeen = true;
				} else if (tag == Tag.Long || tag == Tag.Double) {
					constantCount--;
					off++;
				}
			}
			if (bootstrapMethodsSeen) {
				bootstrapMethodsSeen = false;
				AttributesVisitor attributesVisitor = visitor.visitAttributes();
				if (attributesVisitor != null) {
					// Skip "header" part
					fork.skipBytes(6L);
					fork.skipBytes(2L * fork.readUnsignedShort());
					// Skip all fields and methods
					for (int i = 0; i < 2; i++) {
						int count = fork.readUnsignedShort();
						while (count-- != 0) {
							fork.skipBytes(6L);
							AttributeReader.skipAll(fork);
						}
					}
					int attrCount = fork.readUnsignedShort();
					AttributeInfo<BootstrapMethodsAttribute> bootstrapMethods = AttributeInfo.BootstrapMethods;
					while (attrCount-- != 0) {
						int nameIndex = fork.readConstantPoolIndex();
						long pos = fork.position();
						long constPos = cpPositions[nameIndex];
						fork.position(constPos);
						if (fork.readUnsignedShort() == 16) {
							fork.position(constPos);
							ConstantUtf8 utf8 = Tag.Utf8.codec().read(fork);
							if (attributeMapper.getInfo(AttributeLocation.CLASS, utf8.value()) == bootstrapMethods) {
								fork.position(pos);
								BootstrapMethodsAttribute attribute = bootstrapMethods.codec().read(fork);
								bootstrapMethodsSeen = true;
								attributesVisitor.visit(nameIndex, attribute);
								break prefetch;
							}
						}
						fork.position(pos);
						fork.skipBytes(fork.readAttributeLength());
					}
				}
			}
		}
		if (bootstrapMethodsSeen) {
			classReader.attributeMapper = prefetchMapper;
		} else {
			classReader.attributeMapper = attributeMapper;
		}
		MutableConstantPool constantPool = visitor.visitConstantPool();
		if (constantPool == null) {
			// We still need constant pool if we want to parse attributes
			constantPool = new BasicConstantPool();
		}
		{
			int constantCount = classReader.readUnsignedShort() - 1;
			while (constantCount-- != 0) {
				int rawTag = classReader.readUnsignedByte();
				Tag<?> tag = Tag.byId(rawTag);
				if (tag == null) {
					throw new UncheckedIOException(String.format("Unknown constant pool tag %d", rawTag));
				}
				Constant constant = tag.codec().read(classReader);
				constantPool.add(constant);
				if (constant instanceof ConstantWide) {
					constantCount--;
				}
			}
		}
		{
			int accessFlags = classReader.readAccessFlags();
			int thisClass = classReader.readConstantPoolIndex();
			int superClass = classReader.readConstantPoolIndex();
			int[] interfaces;
			{
				int interfaceCount = classReader.readUnsignedShort();
				interfaces = new int[interfaceCount];
				for (int i = 0; i < interfaceCount; i++) {
					interfaces[i] = classReader.readConstantPoolIndex();
				}
			}
			visitor.visitHeader(accessFlags, thisClass, superClass, interfaces);
			classReader.constantPool = constantPool;
			visitMembers(AttributeLocation.FIELD, classReader, visitor, ClassVisitor::visitField);
			visitMembers(AttributeLocation.METHOD, classReader, visitor, ClassVisitor::visitMethod);
			AttributesVisitor av = visitor.visitAttributes();
			if (av == null) {
				AttributeReader.skipAll(classReader);
			} else {
				AttributeReader.readAll(classReader, AttributeLocation.CLASS, av);
			}
		}
	}

	@Override
	public ClassAssembler newAssembler(ConstantPool constantPool) {
		return new ClassAssemblerImpl(constantPool, attributeMapper);
	}

	@Override
	public ClassAssembler newAssembler() {
		return newAssembler(null);
	}

	private static void visitMembers(AttributeLocation location, ClassReader cr, ClassVisitor cv, VisitMember visitMember) {
		int methodCount = cr.readUnsignedShort();
		while (methodCount-- != 0) {
			int accessFlags = cr.readAccessFlags();
			int nameIndex = cr.readConstantPoolIndex();
			int descriptorIndex = cr.readConstantPoolIndex();
			MemberVisitor mv = visitMember.visit(cv, accessFlags, nameIndex, descriptorIndex);
			AttributesVisitor av;
			if (mv == null || (av = mv.visitAttributes()) == null) {
				AttributeReader.skipAll(cr);
			} else {
				AttributeReader.readAll(cr, location, av);
			}
		}
	}

	private interface VisitMember {

		MemberVisitor visit(ClassVisitor cv, int accessFlags, int nameIndex, int descriptorIndex);
	}
}
