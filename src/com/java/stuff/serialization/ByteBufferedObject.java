package com.java.stuff.serialization;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferedObject extends TestObject
{
	private static final long serialVersionUID = 8286258406673569390L;

	public ByteBufferedObject(long id, boolean state, double[] values, String text) {
		super(id, state, values, text);
	}

	public void writeToFile(String filename) {
		try (RandomAccessFile store = new RandomAccessFile(filename, "rw"); FileChannel channel = store.getChannel()) {
			ByteBuffer buffer = allocateByteBuffer();
			write(buffer);
			buffer.flip();

			channel.write(buffer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ByteBufferedObject readFromFile(String filename) {
		ByteBufferedObject object = null;
		try (RandomAccessFile store = new RandomAccessFile(filename, "rw"); FileChannel channel = store.getChannel()) {
			ByteBuffer buffer = ByteBuffer.allocate((int) store.length());

			channel.read(buffer);
			buffer.flip();
			object = read(buffer);
			channel.close();
			store.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}
	
	private void write(final ByteBuffer byteBuffer) {
		byteBuffer.putLong(id);
		byteBuffer.put((byte) (state ? 1 : 0));

		byteBuffer.putInt(values.length);
		for (final double value : values)
			byteBuffer.putDouble(value);

		byteBuffer.putInt(text.length());
		byteBuffer.put(text.getBytes());
	}

	private static ByteBufferedObject read(final ByteBuffer byteBuffer) {
		final long id = byteBuffer.getLong();
		final boolean state = 0 != byteBuffer.get();

		final int valuesLength = byteBuffer.getInt();
		final double[] values = new double[valuesLength];
		for (int i = 0; i < valuesLength; i++)
			values[i] = byteBuffer.getDouble();

		final String text;
		final int textLength = byteBuffer.getInt();
		byte[] bytes = new byte[textLength];
		byteBuffer.get(bytes);
		text = new String(bytes);

		return new ByteBufferedObject(id, state, values, text);
	}
	
	private ByteBuffer allocateByteBuffer() {
		// long = 8 bytes, boolean as 1 byte, double = 8 bytes, int = 4 bytes	for lengths of values and text
		int size = 8 + 1 + 2 * 4 + values.length * 8 + text.length();

		return ByteBuffer.allocate(size);
	}

}
