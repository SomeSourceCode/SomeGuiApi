/*
 * Copyright 2025, SomeSourceCode - MIT License
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

package io.github.somesourcecode.someguiapi.state;

/**
 * The base class for all integer states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyIntegerStateBase extends ReadOnlyNumberStateBase<Integer> implements ReadOnlyIntegerState {

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public IntegerState asMutableState() {
		final IntegerState state = new SimpleIntegerState();
		state.reflect(this);
		return state;
	}

	@Override
	public ReadOnlyIntegerState add(int value) {
		return Reflect.createIntegerReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyLongState add(long value) {
		return Reflect.createLongReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyFloatState add(float value) {
		return Reflect.createFloatReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyDoubleState add(double value) {
		return Reflect.createDoubleReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyIntegerState subtract(int value) {
		return Reflect.createIntegerReflection(() -> get() - value, this);
	}

	@Override
	public ReadOnlyLongState subtract(long value) {
		return Reflect.createLongReflection(() -> get() - value, this);
	}

	@Override
	public ReadOnlyFloatState subtract(float value) {
		return Reflect.createFloatReflection(() -> get() - value, this);
	}

	@Override
	public ReadOnlyDoubleState subtract(double value) {
		return Reflect.createDoubleReflection(() -> get() - value, this);
	}

	@Override
	public ReadOnlyIntegerState multiply(int value) {
		return Reflect.createIntegerReflection(() -> get() * value, this);
	}

	@Override
	public ReadOnlyLongState multiply(long value) {
		return Reflect.createLongReflection(() -> get() * value, this);
	}

	@Override
	public ReadOnlyFloatState multiply(float value) {
		return Reflect.createFloatReflection(() -> get() * value, this);
	}

	@Override
	public ReadOnlyDoubleState multiply(double value) {
		return Reflect.createDoubleReflection(() -> get() * value, this);
	}

	@Override
	public ReadOnlyIntegerState min(int value) {
		return Reflect.createIntegerReflection(() -> Math.min(get(), value), this);
	}

	@Override
	public ReadOnlyLongState min(long value) {
		return Reflect.createLongReflection(() -> Math.min(get(), value), this);
	}

	@Override
	public ReadOnlyFloatState min(float value) {
		return Reflect.createFloatReflection(() -> Math.min(get(), value), this);
	}

	@Override
	public ReadOnlyDoubleState min(double value) {
		return Reflect.createDoubleReflection(() -> Math.min(get(), value), this);
	}

	@Override
	public ReadOnlyIntegerState max(int value) {
		return Reflect.createIntegerReflection(() -> Math.max(get(), value), this);
	}

	@Override
	public ReadOnlyLongState max(long value) {
		return Reflect.createLongReflection(() -> Math.max(get(), value), this);
	}

	@Override
	public ReadOnlyFloatState max(float value) {
		return Reflect.createFloatReflection(() -> Math.max(get(), value), this);
	}

	@Override
	public ReadOnlyDoubleState max(double value) {
		return Reflect.createDoubleReflection(() -> Math.max(get(), value), this);
	}

	@Override
	public ReadOnlyIntegerState clamp(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createIntegerReflection(() -> Math.min(Math.max(get(), min), max), this);
	}

	@Override
	public ReadOnlyLongState clamp(long min, long max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createLongReflection(() -> Math.min(Math.max(get(), min), max), this);
	}

	@Override
	public ReadOnlyFloatState clamp(float min, float max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createFloatReflection(() -> Math.min(Math.max(get(), min), max), this);
	}

	@Override
	public ReadOnlyDoubleState clamp(double min, double max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createDoubleReflection(() -> Math.min(Math.max(get(), min), max), this);
	}

	@Override
	public ReadOnlyIntegerState negate() {
		return Reflect.createIntegerReflection(() -> -get(), this);
	}

	@Override
	public ReadOnlyIntegerState abs() {
		return Reflect.createIntegerReflection(() -> Math.abs(get()), this);
	}

	@Override
	public ReadOnlyBooleanState isEven() {
		return Reflect.createBooleanReflection(() -> get() % 2 == 0, this);
	}

	@Override
	public ReadOnlyBooleanState isOdd() {
		return Reflect.createBooleanReflection(() -> get() % 2 != 0, this);
	}

	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	@Override
	public ReadOnlyIntegerState gcd(int value) {
		return Reflect.createIntegerReflection(() -> gcd(get(), value), this);
	}

	@Override
	public ReadOnlyIntegerState gcd(ObservableValue<Integer> value) {
		return Reflect.createIntegerReflection(() -> gcd(get(), value.get()), this, value);
	}

	private int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	@Override
	public ReadOnlyIntegerState lcm(int value) {
		return Reflect.createIntegerReflection(() -> lcm(get(), value), this);
	}

	@Override
	public ReadOnlyIntegerState lcm(ObservableValue<Integer> value) {
		return Reflect.createIntegerReflection(() -> lcm(get(), value.get()), this, value);
	}

}
