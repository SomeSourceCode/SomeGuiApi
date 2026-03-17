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
 * A proxy for {@link ReadOnlyLongState} that forwards all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @see ReadOnlyDoubleStateWrapper
 * @since 3.0.0
 */
public class ReadOnlyLongStateProxy extends ReadOnlyNumberStateProxy<Long> implements ReadOnlyLongState {

	private final ReadOnlyLongState delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyLongState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyLongStateProxy(ReadOnlyLongState delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public LongState asMutableState() {
		return delegate.asMutableState();
	}

	@Override
	public ReadOnlyLongState add(int value) {
		return delegate.add(value);
	}

	@Override
	public ReadOnlyLongState add(long value) {
		return delegate.add(value);
	}

	@Override
	public ReadOnlyFloatState add(float value) {
		return delegate.add(value);
	}

	@Override
	public ReadOnlyDoubleState add(double value) {
		return delegate.add(value);
	}

	@Override
	public ReadOnlyLongState subtract(int value) {
		return delegate.subtract(value);
	}

	@Override
	public ReadOnlyLongState subtract(long value) {
		return delegate.subtract(value);
	}

	@Override
	public ReadOnlyFloatState subtract(float value) {
		return delegate.subtract(value);
	}

	@Override
	public ReadOnlyDoubleState subtract(double value) {
		return delegate.subtract(value);
	}

	@Override
	public ReadOnlyLongState multiply(int value) {
		return delegate.multiply(value);
	}

	@Override
	public ReadOnlyLongState multiply(long value) {
		return delegate.multiply(value);
	}

	@Override
	public ReadOnlyFloatState multiply(float value) {
		return delegate.multiply(value);
	}

	@Override
	public ReadOnlyDoubleState multiply(double value) {
		return delegate.multiply(value);
	}

	@Override
	public ReadOnlyLongState min(int value) {
		return delegate.min(value);
	}

	@Override
	public ReadOnlyLongState min(long value) {
		return delegate.min(value);
	}

	@Override
	public ReadOnlyFloatState min(float value) {
		return delegate.min(value);
	}

	@Override
	public ReadOnlyDoubleState min(double value) {
		return delegate.min(value);
	}

	@Override
	public ReadOnlyLongState max(int value) {
		return delegate.max(value);
	}

	@Override
	public ReadOnlyLongState max(long value) {
		return delegate.max(value);
	}

	@Override
	public ReadOnlyFloatState max(float value) {
		return delegate.max(value);
	}

	@Override
	public ReadOnlyDoubleState max(double value) {
		return delegate.max(value);
	}

	@Override
	public ReadOnlyLongState clamp(int min, int max) {
		return delegate.clamp(min, max);
	}

	@Override
	public ReadOnlyLongState clamp(long min, long max) {
		return delegate.clamp(min, max);
	}

	@Override
	public ReadOnlyFloatState clamp(float min, float max) {
		return delegate.clamp(min, max);
	}

	@Override
	public ReadOnlyDoubleState clamp(double min, double max) {
		return delegate.clamp(min, max);
	}

	@Override
	public ReadOnlyLongState negate() {
		return delegate.negate();
	}

	@Override
	public ReadOnlyLongState abs() {
		return delegate.abs();
	}

	@Override
	public ReadOnlyIntegerState round() {
		return delegate.round();
	}

	@Override
	public ReadOnlyIntegerState roundUp() {
		return delegate.roundUp();
	}

	@Override
	public ReadOnlyIntegerState roundDown() {
		return delegate.roundDown();
	}

	@Override
	public ReadOnlyIntegerState floor() {
		return delegate.floor();
	}

	@Override
	public ReadOnlyIntegerState ceiling() {
		return delegate.ceiling();
	}

}
