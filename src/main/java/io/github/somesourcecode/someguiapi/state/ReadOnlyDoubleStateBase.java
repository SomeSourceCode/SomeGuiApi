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
 * The base class for all double states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyDoubleStateBase extends ReadOnlyNumberStateBase<Double> implements ReadOnlyDoubleState {

	@Override
	public Class<Double> getType() {
		return Double.class;
	}

	@Override
	public DoubleState asMutableState() {
		final DoubleState state = new SimpleDoubleState();
		state.reflect(this);
		return state;
	}

	@Override
	public ReadOnlyDoubleState add(int value) {
		return add((double) value);
	}

	@Override
	public ReadOnlyDoubleState add(long value) {
		return add((double) value);
	}

	@Override
	public ReadOnlyDoubleState add(float value) {
		return add((double) value);
	}

	@Override
	public ReadOnlyDoubleState add(double value) {
		return Reflect.createDoubleReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyDoubleState subtract(int value) {
		return subtract((double) value);
	}

	@Override
	public ReadOnlyDoubleState subtract(long value) {
		return subtract((double) value);
	}

	@Override
	public ReadOnlyDoubleState subtract(float value) {
		return subtract((double) value);
	}

	@Override
	public ReadOnlyDoubleState subtract(double value) {
		return Reflect.createDoubleReflection(() -> get() - value, this);
	}

	@Override
	public ReadOnlyDoubleState multiply(int value) {
		return multiply((double) value);
	}

	@Override
	public ReadOnlyDoubleState multiply(long value) {
		return multiply((double) value);
	}

	@Override
	public ReadOnlyDoubleState multiply(float value) {
		return multiply((double) value);
	}

	@Override
	public ReadOnlyDoubleState multiply(double value) {
		return Reflect.createDoubleReflection(() -> get() * value, this);
	}

	@Override
	public ReadOnlyDoubleState min(int value) {
		return min((double) value);
	}

	@Override
	public ReadOnlyDoubleState min(long value) {
		return min((double) value);
	}

	@Override
	public ReadOnlyDoubleState min(float value) {
		return min((double) value);
	}

	@Override
	public ReadOnlyDoubleState min(double value) {
		return Reflect.createDoubleReflection(() -> Math.min(get(), value), this);
	}

	@Override
	public ReadOnlyDoubleState max(int value) {
		return max((double) value);
	}

	@Override
	public ReadOnlyDoubleState max(long value) {
		return max((double) value);
	}

	@Override
	public ReadOnlyDoubleState max(float value) {
		return max((double) value);
	}

	@Override
	public ReadOnlyDoubleState max(double value) {
		return Reflect.createDoubleReflection(() -> Math.max(get(), value), this);
	}

	@Override
	public ReadOnlyDoubleState clamp(int min, int max) {
		return clamp((double) min, (double) max);
	}

	@Override
	public ReadOnlyDoubleState clamp(long min, long max) {
		return clamp((double) min, (double) max);
	}

	@Override
	public ReadOnlyDoubleState clamp(float min, float max) {
		return clamp((double) min, (double) max);
	}

	@Override
	public ReadOnlyDoubleState clamp(double min, double max) {
		if (min > max) {
			throw new IllegalArgumentException("min must be less than or equal to max");
		}
		return Reflect.createDoubleReflection(() -> Math.min(Math.max(get(), min), max), this);
	}

	@Override
	public ReadOnlyDoubleState negate() {
		return Reflect.createDoubleReflection(() -> -get(), this);
	}

	@Override
	public ReadOnlyDoubleState abs() {
		return Reflect.createDoubleReflection(() -> Math.abs(get()), this);
	}

}
