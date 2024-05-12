/*
 * Copyright 2024, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.scene.data;

/**
 * This class is used to store data. It contains and id and the data.
 * The type of data stored must be specified upon creation.
 * @param <T> the type of data stored
 * @since 2.0.0
 */
public class ContextData<T> {

	private final String id;
	private final Class<T> type;

	private T data;

	/**
	 * Constructs a new ContextData instance with the given id and class.
	 * @param id the id of the data
	 * @param type the type of the data
	 * @throws IllegalArgumentException if the id is null or blank, or if the type is null or primitive
	 * @since 2.0.0
	 */
	public ContextData(String id, Class<T> type) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		if (id.isBlank()) {
			throw new IllegalArgumentException("Id cannot be blank");
		}

		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}
		if (type.isPrimitive()) {
			throw new IllegalArgumentException("Type cannot be primitive");
		}

		this.id = id;
		this.type = type;
	}

	/**
	 * Constructs a new ContextData instance with the given id, class and data.
	 * @param id the id of the data
	 * @param type the type of the data
	 * @param data the initial data
	 * @throws IllegalArgumentException if the id is null or blank, or if the type is null or primitive
	 * @since 2.0.0
	 */
	public ContextData(String id, Class<T> type, T data) {
		this(id, type);
		this.data = data;
	}

	/**
	 * Returns the id of the data.
	 * @return the id of the data
	 * @since 2.0.0
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the data.
	 * If the data is not of the correct type, this method will fail silently.
	 * @param data the data to set
	 * @return true if the data was set, false if the data is not of the correct type
	 * @since 2.0.0
	 */
	public boolean set(Object data) {
		if (data != null && !type.isInstance(data) || data == null && type.isPrimitive()) {
			return false;
		}
		this.data = (T) data;
		return true;
	}

	/**
	 * Returns whether the data is set.
	 * @return true if the data is set, false otherwise
	 * @since 2.0.0
	 */
	public boolean isSet() {
		return data != null;
	}

	/**
	 * Unsets the data.
	 * @since 2.0.0
	 */
	public void unset() {
		this.data = null;
	}

	/**
	 * Returns the stored data.
	 * @return the stored data
	 * @since 2.0.0
	 */
	public T get() {
		return data;
	}

	/**
	 * Returns the stored data as an unchecked type.
	 * This is useful if the type of the data is known.
	 * @param <E> the type of the data
	 * @return the stored data
	 * @throws ClassCastException if the data is not of the correct type
	 * @since 2.0.0
	 */
	public <E> E getUnchecked() {
		return (E) data;
	}

	/**
	 * Returns the stored data as the given type.
	 * If the data is not of the given type, this method will throw a {@link ClassCastException}.
	 *
	 * @param type the type of the data
	 * @param <E> the type of the data
	 * @return the stored data as the given type
	 * @throws ClassCastException if the data is not of the given type
	 * @since 2.0.0
	 */
	public <E> E getAs(Class<E> type) {
		return type.cast(data);
	}

	/**
	 * Returns the stored data as a string.
	 * If the data is not a string, it will automatically be converted.
	 *
	 * @return the stored data as a string
	 * @since 2.0.0
	 */
	public String getAsString() {
		return data.toString();
	}

	/**
	 * Returns the stored data as an integer.
	 *
	 * @return the stored data as an integer
	 * @throws ClassCastException if the data cannot be converted to an integer
	 * @since 2.0.0
	 */
	public int getAsInt() {
		return (int) data;
	}

	/**
	 * Returns the stored data as a long.
	 *
	 * @return the stored data as a long
	 * @throws ClassCastException if the data cannot be converted to a long
	 * @since 2.0.0
	 */
	public long getAsLong() {
		return (long) data;
	}

	/**
	 * Returns the stored data as a double.
	 *
	 * @return the stored data as a double
	 * @throws ClassCastException if the data cannot be converted to a double
	 * @since 2.0.0
	 */
	public double getAsDouble() {
		return (double) data;
	}

	/**
	 * Returns the stored data as a float.
	 *
	 * @return the stored data as a float
	 * @throws ClassCastException if the data cannot be converted to a float
	 * @since 2.0.0
	 */
	public float getAsFloat() {
		return (float) data;
	}

	/**
	 * Returns the stored data as a boolean.
	 *
	 * @return the stored data as a boolean
	 * @throws ClassCastException if the data cannot be converted to a boolean
	 * @since 2.0.0
	 */
	public boolean getAsBoolean() {
		return (boolean) data;
	}

	/**
	 * Returns the stored data as a byte.
	 *
	 * @return the stored data as a byte
	 * @throws ClassCastException if the data cannot be converted to a byte
	 * @since 2.0.0
	 */
	public byte getAsByte() {
		return (byte) data;
	}

	/**
	 * Returns the stored data as a short.
	 *
	 * @return the stored data as a short
	 * @throws ClassCastException if the data cannot be converted to a short
	 * @since 2.0.0
	 */
	public short getAsShort() {
		return (short) data;
	}

	/**
	 * Returns the stored data as a char.
	 *
	 * @return the stored data as a char
	 * @throws ClassCastException if the data cannot be converted to a char
	 * @since 2.0.0
	 */
	public char getAsChar() {
		return (char) data;
	}

}
