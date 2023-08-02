package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;

public interface BranchInstruction extends FlowInstruction {

	Label target();
}
