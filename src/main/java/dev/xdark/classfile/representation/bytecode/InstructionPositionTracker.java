package dev.xdark.classfile.representation.bytecode;

import java.util.function.LongConsumer;

public interface InstructionPositionTracker extends LongConsumer {

	int position();
}
