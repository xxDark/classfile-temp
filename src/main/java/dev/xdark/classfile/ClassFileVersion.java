package dev.xdark.classfile;

import dev.xdark.classfile.impl.ClassFileVersionImpl;
import dev.xdark.classfile.io.Codec;

public interface ClassFileVersion {
	int MAJOR_VERSION_OFFSET = 44;
	ClassFileVersion
			V1_0 = of(45),
			V1_1 = V1_0.withMajor(3),
			V1_2 = of(46),
			V1_3 = of(47),
			V1_4 = of(48),
			V1_5 = of(49),
			V6 = of(50),
			V7 = of(51),
			V8 = of(52),
			V9 = of(53),
			V10 = of(54),
			V11 = of(55),
			V12 = of(56),
			V13 = of(57),
			V14 = of(58),
			V15 = of(59),
			V16 = of(60),
			V17 = of(61),
			V18 = of(62),
			V19 = of(63),
			V20 = of(64),
			V21 = of(65),
			V22 = of(66);
	Codec<ClassFileVersion> CODEC = ClassFileVersionImpl.codec();

	int major();

	int minor();

	int pack();

	ClassFileVersion withMajor(int major);

	ClassFileVersion withMinor(int minor);

	ClassFileVersion withPreviewFeatures();

	ClassFileVersion dropPreviewFeatures();

	static ClassFileVersion of(int major, int minor) {
		return new ClassFileVersionImpl(major, minor);
	}

	static ClassFileVersion of(int major) {
		return of(major, 0);
	}
}
