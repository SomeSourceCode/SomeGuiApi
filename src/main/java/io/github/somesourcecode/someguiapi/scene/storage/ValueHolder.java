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

package io.github.somesourcecode.someguiapi.scene.storage;

/**
 * A holder for a value of a specified type.
 *
 * @param <T> the type of the value
 * @since 2.1.0
 */
public final class ValueHolder<T> {

	private final Class<T> type;
	private T value;

	/**
	 * Constructs a new ValueHolder with the specified type.
	 *
	 * @param type the type of the value
	 * @throws IllegalArgumentException if the type is null or a primitive
	 * @see #ValueHolder(Class, Object)
	 * @since 2.1.0
	 */
	public ValueHolder(Class<T> type) {
		this(type, null);
	}

	/**
	 * Constructs a new ValueHolder with the specified type and value.
	 *
	 * @param type the type of the value
	 * @param value the value to hold
	 * @throws IllegalArgumentException if the type is null or a primitive
	 * @since 2.1.0
	 */
	public ValueHolder(Class<T> type, T value) {
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}
		if (type.isPrimitive()) {
			throw new IllegalArgumentException("Type cannot be a primitive");
		}
		this.type = type;
		this.value = value;
	}

	/**
	 * Returns the type of the value.
	 *
	 * @return the type of the value
	 * @since 2.1.0
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * Returns whether the value is from the specified type.
	 *
	 * @param clazz the type to check
	 * @return true if the given class is the same or a subclass of the type, false otherwise
	 * @since 2.1.0
	 */
	public boolean isFromType(Class<?> clazz) {
		return clazz != null && type.isAssignableFrom(clazz);
	}

	/**
	 * Returns whether the value is compatible with the specified object.
	 *
	 * @param object the object to check
	 * @return true, if the value can be passed to the holder, false otherwise
	 * @since 2.1.0
	 */
	public boolean isCompatible(Object object) {
		return type.isInstance(object);
	}

	/**
	 * Returns whether the value is empty.
	 *
	 * @return whether the value is empty
	 * @since 2.1.0
	 */
	public boolean isEmpty() {
		return value == null;
	}

	/**
	 * Returns this holder as a ValueHolder of the specified type.
	 *
	 * @return this holder as a ValueHolder of the specified type
	 * @throws IllegalArgumentException if the holder is not compatible with the specified type
	 * @since 2.1.0
	 */
	@SuppressWarnings("unchecked")
	public <C> ValueHolder<C> holding(Class<C> clazz) {
		if (!isFromType(clazz)) {
			throw new IllegalArgumentException("Holder from type " + type.getName() + " is not compatible with type " + clazz.getName());
		}
		return (ValueHolder<C>) this;
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link String}.
	 *
	 * @return this holder as a ValueHolder of type {@link String}
	 * @since 2.1.0
	 */
	public ValueHolder<String> holdingString() {
		return holding(String.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Integer}.
	 *
	 * @return this holder as a ValueHolder of type {@link Integer}
	 * @since 2.1.0
	 */
	public ValueHolder<Integer> holdingInt() {
		return holding(Integer.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Double}.
	 *
	 * @return this holder as a ValueHolder of type {@link Double}
	 * @since 2.1.0
	 */
	public ValueHolder<Double> holdingDouble() {
		return holding(Double.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Float}.
	 *
	 * @return this holder as a ValueHolder of type {@link Float}
	 * @since 2.1.0
	 */
	public ValueHolder<Float> holdingFloat() {
		return holding(Float.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Long}.
	 *
	 * @return this holder as a ValueHolder of type {@link Long}
	 * @since 2.1.0
	 */
	public ValueHolder<Long> holdingLong() {
		return holding(Long.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Short}.
	 *
	 * @return this holder as a ValueHolder of type {@link Short}
	 * @since 2.1.0
	 */
	public ValueHolder<Short> holdingShort() {
		return holding(Short.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Byte}.
	 *
	 * @return this holder as a ValueHolder of type {@link Byte}
	 * @since 2.1.0
	 */
	public ValueHolder<Byte> holdingByte() {
		return holding(Byte.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Boolean}.
	 *
	 * @return this holder as a ValueHolder of type {@link Boolean}
	 * @since 2.1.0
	 */
	public ValueHolder<Boolean> holdingBoolean() {
		return holding(Boolean.class);
	}

	/**
	 * Returns this holder as a ValueHolder of type {@link Character}.
	 *
	 * @return this holder as a ValueHolder of type {@link Character}
	 * @since 2.1.0
	 */
	public ValueHolder<Character> holdingChar() {
		return holding(Character.class);
	}

	/**
	 * Sets the value of this holder.
	 *
	 * @param value the value to set
	 * @throws IllegalArgumentException if the value is not compatible with the type
	 * @since 2.1.0
	 */
	public void set(T value) {
		if (!isCompatible(value)) {
			throw new IllegalArgumentException("Expected " + type.getName() + " but provided " + value.getClass().getName());
		}
		this.value = value;
	}

	/**
	 * Returns the value of this holder.
	 *
	 * @return the value of this holder
	 * @since 2.1.0
	 */
	public T value() {
		return value;
	}

	/**
	 * Returns the value of this holder as the specified type.
	 *
	 * @param clazz the type to cast the value to
	 * @param <C> the type to cast the value to
	 * @return the value of this holder as the specified type
	 * @throws ClassCastException if the value is not compatible with the specified type
	 * @since 2.1.0
	 */
	public <C> C as(Class<C> clazz) {
		return clazz.cast(value);
	}

	/**
	 * Returns the value of this holder as a string.
	 * If the value is not a {@link String}, the string representation of
	 * the value is returned.
	 *
	 * @return the value as a string
	 * @since 2.1.0
	 */
	public String asString() {
		return String.valueOf(value);
	}

	/**
	 * Returns the value of this holder as an int.
	 *
	 * @return the value as an int
	 * @throws ClassCastException if the value cannot be cast to an int
	 * @since 2.1.0
	 */
	public int asInt() {
		return (int) value;
	}

	/**
	 * Returns the value of this holder as a double.
	 *
	 * @return the value as a double
	 * @throws ClassCastException if the value cannot be cast to a double
	 * @since 2.1.0
	 */
	public double asDouble() {
		return (double) value;
	}

	/**
	 * Returns the value of this holder as a float.
	 *
	 * @return the value as a float
	 * @throws ClassCastException if the value cannot be cast to a float
	 * @since 2.1.0
	 */
	public float asFloat() {
		return (float) value;
	}

	/**
	 * Returns the value of this holder as a long.
	 *
	 * @return the value as a long
	 * @throws ClassCastException if the value cannot be cast to a long
	 * @since 2.1.0
	 */
	public long asLong() {
		return (long) value;
	}

	/**
	 * Returns the value of this holder as a short.
	 *
	 * @return the value as a short
	 * @throws ClassCastException if the value cannot be cast to a short
	 * @since 2.1.0
	 */
	public short asShort() {
		return (short) value;
	}

	/**
	 * Returns the value of this holder as a byte.
	 *
	 * @return the value as a byte
	 * @throws ClassCastException if the value cannot be cast to a byte
	 * @since 2.1.0
	 */
	public byte asByte() {
		return (byte) value;
	}

	/**
	 * Returns the value of this holder as a boolean.
	 *
	 * @return the value as a boolean
	 * @throws ClassCastException if the value cannot be cast to a boolean
	 * @since 2.1.0
	 */
	public boolean asBoolean() {
		return (boolean) value;
	}

	/**
	 * Returns the value of this holder as a char.
	 *
	 * @return the value as a char
	 * @throws ClassCastException if the value cannot be cast to a char
	 * @since 2.1.0
	 */
	public char asChar() {
		return (char) value;
	}

	/**
	 * Increments the value of this holder by 1.
	 *
	 * @return this holder for method chaining
	 * @throws UnsupportedOperationException if the type cannot be incremented
	 * @see #increment(int)
	 * @since 2.1.0
	 */
	public ValueHolder<T> increment() {
		return increment(1);
	}

	/**
	 * Increments the value of this holder by the specified amount.
	 *
	 * @param amount the amount to increment by
	 * @return this holder for method chaining
	 * @throws UnsupportedOperationException if the type cannot be incremented
	 * @since 2.1.0
	 */
	@SuppressWarnings("unchecked")
	public ValueHolder<T> increment(int amount) {
		if (!isFromType(Integer.class)) {
			throw new UnsupportedOperationException("Cannot increment type " + type.getName());
		}
		int intValue = (int) value;
		value = (T) (Integer) (intValue + amount);
		return this;
	}

	/**
	 * Decrements the value of this holder by 1.
	 *
	 * @return this holder for method chaining
	 * @throws UnsupportedOperationException if the type cannot be decremented
	 * @see #decrement(int)
	 * @since 2.1.0
	 */
	public ValueHolder<T> decrement() {
		return decrement(1);
	}

	/**
	 * Decrements the value of this holder by the specified amount.
	 *
	 * @param amount the amount to decrement by
	 * @return this holder for method chaining
	 * @throws UnsupportedOperationException if the type cannot be decremented
	 * @since 2.1.0
	 */
	@SuppressWarnings("unchecked")
	public ValueHolder<T> decrement(int amount) {
		if (!isFromType(Integer.class)) {
			throw new UnsupportedOperationException("Cannot decrement type " + type.getName());
		}
		value = (T) (Integer) ((int) value - amount);
		return this;
	}

	/**
	 * Inverts the value of this holder.
	 *
	 * @return this holder for method chaining
	 * @throws UnsupportedOperationException if the type cannot be inverted
	 * @since 2.1.0
	 */
	@SuppressWarnings("unchecked")
	public ValueHolder<T> invert() {
		if (!isFromType(Boolean.class)) {
			throw new UnsupportedOperationException("Cannot invert type " + type.getName());
		}
		value = (T) (Boolean) !(boolean) value;
		return this;
	}

	/**
	 * Clears the value of this holder.
	 *
	 * @since 2.1.0
	 */
	public void clear() {
		value = null;
	}

	@Override
	public String toString() {
		return "ValueHolder[" +
				"type=" + type.getSimpleName() +
				", value=" + asString() +
				']';
	}

}
