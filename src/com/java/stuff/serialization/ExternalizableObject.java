package com.java.stuff.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The double array is written and read by hand using its size in this class
 *
 */
public class ExternalizableObject extends TestObject implements Externalizable
{

	public ExternalizableObject() {
		super();
	}

	public ExternalizableObject(long id, boolean state, double[] values, String text) {
		super(id, state, values, text);
	}

	@Override
	public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
		id = oi.readLong();
		state = oi.readBoolean();

		int size = oi.readInt();
		values = new double[size];
		for (int i = 0; i < size; i++)
			values[i] = oi.readDouble();

		text = (String) oi.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput oo) throws IOException {
		oo.writeLong(getId());
		oo.writeBoolean(isState());

		oo.writeInt(getValues().length);
		for (double d : getValues())
			oo.writeDouble(d);

		oo.writeObject(text);
	}

}
