package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;

import java.util.List;

public interface SwitchInstruction extends ImmediateJumpInstruction {

	Label defaultBranch();

	List<Label> cases();

	Label select(int key);
}
