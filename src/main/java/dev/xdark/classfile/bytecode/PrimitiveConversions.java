package dev.xdark.classfile.bytecode;

import dev.xdark.classfile.type.PrimitiveKind;
import dev.xdark.classfile.type.PrimitiveType;

public final class PrimitiveConversions {

	private PrimitiveConversions() {
	}

	public static void performConversion(PrimitiveConversion conversion, PrimitiveType from, PrimitiveType to) {
		PrimitiveKind a = from.kind();
		PrimitiveKind b = to.kind();
		err:
		{
			if (a == PrimitiveKind.T_VOID || b == PrimitiveKind.T_VOID) {
				break err;
			}
			if (a == b) return;
			switch (a) {
				case T_INT:
				case T_BYTE:
				case T_CHAR:
				case T_SHORT:
				case T_BOOLEAN:
					switch (b) {
						case T_LONG:
							conversion.i2l();
							break;
						case T_FLOAT:
							conversion.i2f();
							break;
						case T_DOUBLE:
							conversion.i2d();
							break;
						case T_BYTE:
							conversion.i2b();
							break;
						case T_CHAR:
							conversion.i2c();
							break;
						case T_SHORT:
							conversion.i2s();
							break;
						default:
							break err;
					}
					break;
				case T_LONG:
					switch (b) {
						case T_INT:
							conversion.l2i();
							break;
						case T_FLOAT:
							conversion.l2f();
							break;
						case T_DOUBLE:
							conversion.l2d();
							break;
						case T_CHAR:
							conversion.l2i();
							conversion.i2c();
							break;
						case T_SHORT:
							conversion.l2i();
							conversion.i2s();
							break;
						case T_BYTE:
						case T_BOOLEAN:
							conversion.l2i();
							conversion.i2b();
							break;
						default:
							break err;
					}
					break;
				case T_FLOAT:
					switch (b) {
						case T_INT:
							conversion.f2i();
							break;
						case T_LONG:
							conversion.f2l();
							break;
						case T_DOUBLE:
							conversion.f2d();
							break;
						case T_CHAR:
							conversion.f2i();
							conversion.i2c();
							break;
						case T_SHORT:
							conversion.f2i();
							conversion.i2s();
							break;
						case T_BYTE:
						case T_BOOLEAN:
							conversion.f2i();
							conversion.i2b();
							break;
						default:
							break err;
					}
					break;
				case T_DOUBLE:
					switch (b) {
						case T_INT:
							conversion.d2i();
							break;
						case T_LONG:
							conversion.d2l();
							break;
						case T_FLOAT:
							conversion.d2f();
							break;
						case T_CHAR:
							conversion.d2i();
							conversion.i2c();
							break;
						case T_SHORT:
							conversion.d2i();
							conversion.i2s();
							break;
						case T_BYTE:
						case T_BOOLEAN:
							conversion.d2i();
							conversion.i2b();
							break;
						default:
							break err;
					}
			}
			return;
		}
		throw new IllegalStateException(String.format("Cannot convert %s to %s", a, b));
	}
}
