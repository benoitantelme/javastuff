package com.java.stuff.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The double array is written and read directly using an object in this class
 *
 */
public class DirectlyExternalizableObject extends TestObject implements Externalizable
{

	public DirectlyExternalizableObject() {
		super();
	}

	public DirectlyExternalizableObject(long id, boolean state, double[] values, String text) {
		super(id, state, values, text);
	}

	@Override
	public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
		id = oi.readLong();
		state = oi.readBoolean();
		values = (double[]) oi.readObject();
		text = (String) oi.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput oo) throws IOException {
		oo.writeLong(getId());
		oo.writeBoolean(isState());
		oo.writeObject(values);
		oo.writeObject(text);
	}

}
