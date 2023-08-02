package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.Accessible;
import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.Signed;
import dev.xdark.classfile.representation.Typed;

public interface MemberModel extends Accessible, Typed, Signed, Attributed {

	String name();
}
