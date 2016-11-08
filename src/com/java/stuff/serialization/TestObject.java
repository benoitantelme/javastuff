package com.java.stuff.serialization;

import java.io.Serializable;
import java.util.Arrays;

public class TestObject implements Serializable
{

	private static final long serialVersionUID = 2627172196156791707L;

	protected long id;
	protected boolean state;
	protected double[] values;
	protected String text;

	public TestObject() {
		super();
	}

	public TestObject(long id, boolean state, double[] values, String text) {
		super();
		this.id = id;
		this.state = state;
		this.values = values;
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (state ? 1231 : 1237);
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		TestObject other = (TestObject) obj;
		if (id != other.id)
			return false;
		if (state != other.state)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		}
		else if (!text.equals(other.text))
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
