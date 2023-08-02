package dev.xdark.classfile.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class TypeIterator implements Iterator<Type> {
	private final CharSequence cs;
	private int index;
	private Type type;

	public TypeIterator(CharSequence cs) {
		this.cs = cs;
	}

	@Override
	public boolean hasNext() {
		Type type = this.type;
		if (type != null) {
			return true;
		}
		type = read(this.index);
		if (type == null) {
			this.index = -1;
			return false;
		}
		this.type = type;
		return true;
	}

	@Override
	public Type next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Type type = this.type;
		this.type = null;
		return type;
	}

	private Type read(int index) {
		CharSequence cs = this.cs;
		int length = cs.length();
		if (index < length) {
			char c = cs.charAt(index++);
			if (c == '[') {
				this.index = index;
				Type type = read(index);
				if (!(type instanceof ClassType)) {
					this.index = index - 1;
					throw new IllegalStateException("Expected ClassType");
				}
				return ArrayType.create((ClassType) type);
			}
			if (c == 'L') {
				int start = index;
				for (; index < length; index++) {
					if (cs.charAt(index) == ';') {
						InstanceType instanceType = InstanceType.ofInternalName(cs.subSequence(start, index).toString());
						this.index = index + 1;
						return instanceType;
					}
				}
				this.index = start - 1;
				throw new IllegalStateException("Expected InstanceType");
			}
			this.index = index;
			switch (c) {
				case 'V':
					return PrimitiveType.T_VOID;
				case 'J':
					return PrimitiveType.T_LONG;
				case 'D':
					return PrimitiveType.T_DOUBLE;
				case 'I':
					return PrimitiveType.T_INT;
				case 'F':
					return PrimitiveType.T_FLOAT;
				case 'C':
					return PrimitiveType.T_CHAR;
				case 'S':
					return PrimitiveType.T_SHORT;
				case 'B':
					return PrimitiveType.T_BYTE;
				case 'Z':
					return PrimitiveType.T_BOOLEAN;
			}
			if (c != '(') {
				throw new IllegalStateException("Expected MethodType start");
			}
			int start = index;
			List<ClassType> parameterTypes = new ArrayList<>();
			while (cs.charAt(index) != ')') {
				Type parameter = read(index);
				if (!(parameter instanceof ClassType)) {
					this.index = start - 1;
					throw new IllegalStateException("Expected ClassType");
				}
				parameterTypes.add((ClassType) parameter);
				index = this.index;
			}
			Type returnType = read(this.index += 1);
			if (!(returnType instanceof ClassType)) {
				this.index = start - 1;
				throw new IllegalStateException("Expected ClassType");
			}
			return MethodType.create((ClassType) returnType, parameterTypes);
		}
		return null;
	}
}
