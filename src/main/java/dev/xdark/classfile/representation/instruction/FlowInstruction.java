package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;

import java.util.stream.Stream;

public interface FlowInstruction extends Instruction {

	Stream<Label> targets();
}
