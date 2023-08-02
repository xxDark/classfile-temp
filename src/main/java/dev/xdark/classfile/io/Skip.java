package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

public interface Skip {

	void skip(ClassReader reader) throws IOException;

	default Skip then(Skip next) {
		return reader -> {
			skip(reader);
			next.skip(reader);
		};
	}

	static Skip exact(long skip) {
		return new ExactSkip(skip);
	}

	static Skip vary(VariableSkip.VariableRead variableRead) {
		return new VariableSkip(variableRead);
	}

	static Skip zero() {
		return ZeroSkip.INSTANCE;
	}
}
