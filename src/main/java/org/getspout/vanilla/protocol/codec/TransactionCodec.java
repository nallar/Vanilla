package org.getspout.vanilla.protocol.codec;

import java.io.IOException;

import org.getspout.api.protocol.MessageCodec;
import org.getspout.vanilla.protocol.msg.TransactionMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public final class TransactionCodec extends MessageCodec<TransactionMessage> {
	public TransactionCodec() {
		super(TransactionMessage.class, 0x6A);
	}

	@Override
	public TransactionMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readUnsignedByte();
		int transaction = buffer.readUnsignedShort();
		boolean accepted = buffer.readUnsignedByte() != 0;
		return new TransactionMessage(id, transaction, accepted);
	}

	@Override
	public ChannelBuffer encode(TransactionMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.buffer(4);
		buffer.writeByte(message.getId());
		buffer.writeShort(message.getTransaction());
		buffer.writeByte(message.isAccepted() ? 1 : 0);
		return buffer;
	}
}
