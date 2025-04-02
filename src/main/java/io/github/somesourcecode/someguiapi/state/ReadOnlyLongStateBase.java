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
 * The base class for all long states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyLongStateBase extends ReadOnlyNumberStateBase<Long> implements ReadOnlyLongState {

	@Override
	public Class<Long> getType() {
		return Long.class;
	}

	@Override
	public LongState asMutableState() {
		return null;
	}

	@Override
	public ReadOnlyLongState add(int value) {
		return add((long) value);
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
	public ReadOnlyLongState subtract(int value) {
		return subtract((long) value);
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
	public ReadOnlyLongState multiply(int value) {
		return multiply((long) value);
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
	public ReadOnlyLongState min(int value) {
		return min((long) value);
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
	public ReadOnlyLongState max(int value) {
		return max((long) value);
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
	public ReadOnlyLongState clamp(int min, int max) {
		return clamp((long) min, (long) max);
	}

	@Override
	public ReadOnlyLongState clamp(long min, long max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createLongReflection(() -> Math.clamp(get(), min, max), this);
	}

	@Override
	public ReadOnlyFloatState clamp(float min, float max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createFloatReflection(() -> Math.clamp(get(), min, max), this);
	}

	@Override
	public ReadOnlyDoubleState clamp(double min, double max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createDoubleReflection(() -> Math.clamp(get(), min, max), this);
	}

	@Override
	public ReadOnlyLongState negate() {
		return Reflect.createLongReflection(() -> -get(), this);
	}

	@Override
	public ReadOnlyLongState abs() {
		return Reflect.createLongReflection(() -> Math.abs(get()), this);
	}

}
