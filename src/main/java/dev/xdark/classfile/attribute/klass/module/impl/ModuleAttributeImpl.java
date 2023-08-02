package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.module.Export;
import dev.xdark.classfile.attribute.klass.module.ModuleAttribute;
import dev.xdark.classfile.attribute.klass.module.Open;
import dev.xdark.classfile.attribute.klass.module.Provide;
import dev.xdark.classfile.attribute.klass.module.Require;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;

public final class ModuleAttributeImpl implements ModuleAttribute {
	private final int nameIndex;
	private final int flags;
	private final int versionIndex;
	private final List<Require> requires;
	private final List<Export> exports;
	private final List<Open> opens;
	private final int[] uses;
	private final List<Provide> provides;

	ModuleAttributeImpl(int nameIndex, int flags, int versionIndex, List<Require> requires, List<Export> exports, List<Open> opens, int[] uses, List<Provide> provides) {
		this.nameIndex = nameIndex;
		this.flags = flags;
		this.versionIndex = versionIndex;
		this.requires = requires;
		this.exports = exports;
		this.opens = opens;
		this.uses = uses;
		this.provides = provides;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public int flags() {
		return flags;
	}

	@Override
	public int versionIndex() {
		return versionIndex;
	}

	@Override
	public List<Require> requires() {
		return requires;
	}

	@Override
	public List<Export> exports() {
		return exports;
	}

	@Override
	public List<Open> opens() {
		return opens;
	}

	@Override
	public int[] uses() {
		return uses;
	}

	@Override
	public List<Provide> provides() {
		return provides;
	}

	@Override
	public AttributeInfo<ModuleAttribute> info() {
		return AttributeInfo.Module;
	}

	public static Codec<ModuleAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkAtLeast(reader.readAttributeLength(), 16);
					int nameIndex = reader.readConstantPoolIndex();
					int flags = reader.readAccessFlags();
					int versionIndex = reader.readConstantPoolIndex();
					List<Require> requires = AttributeHelper.readList(reader, Require.CODEC);
					List<Export> exports = AttributeHelper.readList(reader, Export.CODEC);
					List<Open> opens = AttributeHelper.readList(reader, Open.CODEC);
					int[] uses = AttributeHelper.readUnsignedShorts(reader);
					List<Provide> provides = AttributeHelper.readList(reader, Provide.CODEC);
					return new ModuleAttributeImpl(nameIndex, flags, versionIndex, requires, exports, opens, uses, provides);
				}, VariableSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.nameIndex());
					writer.writeAccessFlags(value.flags());
					writer.writeConstantPoolIndex(value.versionIndex());
					AttributeHelper.writeList(writer, value.requires(), Require.CODEC);
					AttributeHelper.writeList(writer, value.exports(), Export.CODEC);
					AttributeHelper.writeList(writer, value.opens(), Open.CODEC);
					AttributeHelper.writeUnsignedShorts(writer, value.uses());
					AttributeHelper.writeList(writer, value.provides(), Provide.CODEC);
				}
		);
	}
}
