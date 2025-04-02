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
 * The base class for all float states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyFloatStateBase extends ReadOnlyNumberStateBase<Float> implements ReadOnlyFloatState {

	@Override
	public Class<Float> getType() {
		return Float.class;
	}

	@Override
	public FloatState asMutableState() {
		final FloatState state =  new SimpleFloatState(get());
		state.reflect(this);
		return state;
	}

	@Override
	public ReadOnlyFloatState add(int value) {
		return add((float) value);
	}

	@Override
	public ReadOnlyFloatState add(long value) {
		return add((float) value);
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
	public ReadOnlyFloatState subtract(int value) {
		return subtract((float) value);
	}

	@Override
	public ReadOnlyFloatState subtract(long value) {
		return subtract((float) value);
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
	public ReadOnlyFloatState multiply(int value) {
		return multiply((float) value);
	}

	@Override
	public ReadOnlyFloatState multiply(long value) {
		return multiply((float) value);
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
	public ReadOnlyFloatState min(int value) {
		return min((float) value);
	}

	@Override
	public ReadOnlyFloatState min(long value) {
		return min((float) value);
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
	public ReadOnlyFloatState max(int value) {
		return max((float) value);
	}

	@Override
	public ReadOnlyFloatState max(long value) {
		return max((float) value);
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
	public ReadOnlyFloatState clamp(int min, int max) {
		return clamp((float) min, (float) max);
	}

	@Override
	public ReadOnlyFloatState clamp(long min, long max) {
		return clamp((float) min, (float) max);
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
	public ReadOnlyFloatState negate() {
		return Reflect.createFloatReflection(() -> -get(), this);
	}

	@Override
	public ReadOnlyFloatState abs() {
		return Reflect.createFloatReflection(() -> Math.abs(get()), this);
	}

}
