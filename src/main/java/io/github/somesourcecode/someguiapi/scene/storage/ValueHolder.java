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

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A holder for a value of a specified type.
 *
 * @param <T> the type of the value
 * @since 2.1.0
 */
public final class ValueHolder<T> {

	private final Class<T> type;
	private T value;
	private final boolean registered;

	/**
	 * Constructs a new ValueHolder with the specified type.
	 *
	 * @param type the type of the value
	 * @param registered whether the value is registered
	 * @throws IllegalArgumentException if the type is null or a primitive
	 * @see #ValueHolder(Class, Object, boolean)
	 * @since 2.1.0
	 */
	ValueHolder(Class<T> type, boolean registered) {
		this(type, null, registered);
	}

	/**
	 * Constructs a new ValueHolder with the specified type and value.
	 *
	 * @param type the type of the value
	 * @param value the value to hold
	 * @param registered whether the value is registered
	 * @throws IllegalArgumentException if the type is null or a primitive
	 * @since 2.1.0
	 */
	ValueHolder(Class<T> type, T value, boolean registered) {
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}
		if (type.isPrimitive()) {
			throw new IllegalArgumentException("Type cannot be a primitive");
		}
		this.type = type;
		this.value = value;
		this.registered = registered;
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
	 * Returns whether the value is registered. This will be
	 * true when the value was registered via {@link Storage#register(String, Class)}.
	 * If this is a dummy holder, this will be false.
	 *
	 * @return whether the value is registered
	 * @since 2.1.0
	 */
	public boolean isRegistered() {
		return registered;
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
	 * Maps the value of this holder to a new value using the specified function.
	 * The returned value holder will carry the registered state of this holder.
	 *
	 * @param newType the type of the new value
	 * @param mappingFunctions the mapping function
	 * @param <R> the type of the new value
	 * @return the new value holder
	 * @since 2.1.0
	 */
	public <R> ValueHolder<R> map(Class<R> newType, Function<T, ? extends R> mappingFunctions) {
		if (mappingFunctions == null) {
			return new ValueHolder<>(newType, registered);
		}
		return new ValueHolder<>(newType, mappingFunctions.apply(value), registered);
	}

	/**
	 * Maps the value of this holder to a new value using the specified function.
	 * The returned value holder will carry the registered state of this holder.
	 *
	 * @param mappingFunction the mapping function
	 * @return the new value holder
	 * @since 2.1.0
	 */
	public ValueHolder<T> map(Function<T, T> mappingFunction) {
		if (mappingFunction == null) {
			return new ValueHolder<>(type, value, registered);
		}
		return new ValueHolder<>(type, mappingFunction.apply(value), registered);
	}

	/**
	 * Returns the mapped value of this holder using the specified function.
	 *
	 * @param mappingFunction the mapping function
	 * @param <R> the type of the mapped value
	 * @return the mapped value
	 * @since 2.1.0
	 */
	public <R> R mapped(Function<T, R> mappingFunction) {
		if (mappingFunction == null) {
			return null;
		}
		return mappingFunction.apply(value);
	}

	/**
	 * Executes the consumer if the value is present.
	 *
	 * @param consumer the consumer
	 * @since 2.1.0
	 */
	public void ifPresent(Consumer<T> consumer) {
		if (value == null || consumer == null) {
			return;
		}
		consumer.accept(value);
	}

	/**
	 * Executes the consumer if the value is registered.
	 *
	 * @param consumer the consumer
	 * @since 2.1.0
	 */
	public void ifRegistered(Consumer<ValueHolder<T>> consumer) {
		if (registered && consumer != null) {
			consumer.accept(this);
		}
	}

	/**
	 * Executes the consumer if the value is present and registered.
	 *
	 * @param consumer the consumer
	 * @since 2.1.0
	 */
	public void ifPresentAndRegistered(Consumer<T> consumer) {
		if (value != null && registered && consumer != null) {
			consumer.accept(value);
		}
	}

	/**
	 * Returns this holder as a ValueHolder of the specified type.
	 *
	 * @param clazz the type
	 * @param <C> the type
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
	 * Sets the value of this holder to the result of the specified function.
	 *
	 * @param mappingFunction the mapping function
	 * @since 2.1.0
	 */
	public void set(Function<T, T> mappingFunction) {
		if (mappingFunction == null) {
			return;
		}
		set(mappingFunction.apply(value));
	}

	/**
	 * Sets the value of this holder to the specified object if it is compatible.
	 * If the object is not compatible, this method does nothing.
	 *
	 * @param object the object to set
	 * @return true if the object was set, false otherwise
	 * @since 2.1.0
	 */
	public boolean setIfCompatible(Object object) {
		if (!isCompatible(object)) {
			return false;
		}
		set(type.cast(object));
		return true;
	}

	/**
	 * Sets the value of this holder to the specified object.
	 *
	 * @throws ClassCastException if the object is not compatible with the type
	 * @param object the object to set
	 */
	public void setUnsafe(Object object) {
		set(type.cast(object));
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
	 * Returns the associated value of this holder as the specified type,
	 * unless at least one of the following conditions is met:
	 *
	 * <ul>
	 *     <li>the value is not registered</li>
	 *     <li>the value is null</li>
	 *     <li>the value is not from the specified type</li>
	 * </ul>
	 *
	 * in which case the default value is returned.
	 * To accept null values, use {@link #asOrElseNullable(Class, Object)}.
	 *
	 * @param clazz the type
	 * @param defaultValue the default value
	 * @param <C> the type
	 * @return the value as the specified type or the default value
	 * @see #asOrElseNullable(Class, Object)
	 * @since 2.1.0
	 */
	public <C> C asOrElse(Class<C> clazz, C defaultValue) {
		return registered && value != null && isFromType(clazz) ? as(clazz) : defaultValue;
	}

	/**
	 * Returns the associated value of this holder as the specified type,
	 * unless at least one of the following conditions is met:
	 *
	 * <ul>
	 *     <li>the value is not registered</li>
	 *     <li>the value is not from the specified type</li>
	 * </ul>
	 *
	 * in which case the default value is returned.
	 * To return the default value if the value is null,
	 * use {@link #asOrElse(Class, Object)}.
	 *
	 * @param clazz the type
	 * @param defaultValue the default value
	 * @param <C> the type
	 * @return the value as the specified type or the default value
	 * @see #asOrElse(Class, Object)
	 * @since 2.1.0
	 */
	public <C> C asOrElseNullable(Class<C> clazz, C defaultValue) {
		return registered && isFromType(clazz) ? as(clazz) : defaultValue;
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
	 * Returns the string representation of the value, unless
	 * it is not registered or null, in which case the default
	 * value is returned.
	 * <p>
	 * To accept null values, use {@link #asStringOrElseNullable(String)}.
	 *
	 * @param defaultValue the default value
	 * @return the value as a string or the default value
	 * @see #asStringOrElseNullable(String)
	 * @since 2.1.0
	 */
	public String asStringOrElse(String defaultValue) {
		return registered && value != null ? asString() : defaultValue;
	}

	/**
	 * Returns the string representation of the value, unless
	 * it is not registered, in which case the default value is returned.
	 * <p>
	 * To return the default value if the value is null, use
	 * {@link #asStringOrElse(String)}.
	 *
	 * @param defaultValue the default value
	 * @return the value as a string or the default value
	 * @see #asStringOrElse(String)
	 * @since 2.1.0
	 */
	public String asStringOrElseNullable(String defaultValue) {
		return registered ? asString() : defaultValue;
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
	 * Returns the value of this holder as an int, unless
	 * it is not registered, null or not from the type {@link Integer},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as an int or the default value
	 */
	public int asIntOrElse(int defaultValue) {
		return registered && value != null && isFromType(Integer.class) ? asInt() : defaultValue;
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
	 * Returns the value of this holder as a double, unless
	 * it is not registered, null or not from the type {@link Double},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a double or the default value
	 */
	public double asDoubleOrElse(double defaultValue) {
		return registered && value != null && isFromType(Double.class) ? asDouble() : defaultValue;
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
	 * Returns the value of this holder as a float, unless
	 * it is not registered, null or not from the type {@link Float},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a float or the default value
	 */
	public float asFloatOrElse(float defaultValue) {
		return registered && value != null && isFromType(Float.class) ? asFloat() : defaultValue;
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
	 * Returns the value of this holder as a long, unless
	 * it is not registered, null or not from the type {@link Long},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a long or the default value
	 */
	public long asLongOrElse(long defaultValue) {
		return registered && value != null && isFromType(Long.class) ? asLong() : defaultValue;
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
	 * Returns the value of this holder as a short, unless
	 * it is not registered, null or not from the type {@link Short},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a short or the default value
	 */
	public short asShortOrElse(short defaultValue) {
		return registered && value != null && isFromType(Short.class) ? asShort() : defaultValue;
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
	 * Returns the value of this holder as a byte, unless
	 * it is not registered, null or not from the type {@link Byte},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a byte or the default value
	 */
	public byte asByteOrElse(byte defaultValue) {
		return registered && value != null && isFromType(Byte.class) ? asByte() : defaultValue;
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
	 * Returns the value of this holder as a boolean, unless
	 * it is not registered, null or not from the type {@link Boolean},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a boolean or the default value
	 */
	public boolean asBooleanOrElse(boolean defaultValue) {
		return registered && value != null && isFromType(Boolean.class) ? asBoolean() : defaultValue;
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
	 * Returns the value of this holder as a char, unless
	 * it is not registered, null or not from the type {@link Character},
	 * in which case the default value is returned.
	 *
	 * @param defaultValue the default value
	 * @return the value as a char or the default value
	 */
	public char asCharOrElse(char defaultValue) {
		return registered && value != null && isFromType(Character.class) ? asChar() : defaultValue;
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
