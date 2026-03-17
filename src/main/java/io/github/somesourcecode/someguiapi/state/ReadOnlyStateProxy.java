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

import java.util.function.Function;

/**
 * The base class for proxy classes that forward all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public abstract class ReadOnlyStateProxy<T> implements ReadOnlyState<T> {

	private final ReadOnlyState<T> delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyStateProxy(ReadOnlyState<T> delegate) {
		this.delegate = delegate;
	}

	@Override
	public Class<? super T> getType() {
		return delegate.getType();
	}

	@Override
	public T get() {
		return delegate.get();
	}

	@Override
	public Subscription observe(Observer<? super T> observer) {
		return delegate.observe(observer);
	}

	@Override
	public void removeObserver(Observer<? super T> observer) {
		delegate.removeObserver(observer);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(T value) {
		return delegate.isEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(ObservableValue<T> value) {
		return delegate.isEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isNotEqualTo(T value) {
		return delegate.isNotEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isNotEqualTo(ObservableValue<T> value) {
		return delegate.isNotEqualTo(value);
	}

	@Override
	public ReadOnlyStringState asString() {
		return delegate.asString();
	}

	@Override
	public ReadOnlyBooleanState mapToBoolean(Function<T, Boolean> mapper) {
		return delegate.mapToBoolean(mapper);
	}

	@Override
	public ReadOnlyIntegerState mapToInteger(Function<T, Integer> mapper) {
		return delegate.mapToInteger(mapper);
	}

	@Override
	public ReadOnlyLongState mapToLong(Function<T, Long> mapper) {
		return delegate.mapToLong(mapper);
	}

	@Override
	public ReadOnlyFloatState mapToFloat(Function<T, Float> mapper) {
		return delegate.mapToFloat(mapper);
	}

	@Override
	public ReadOnlyDoubleState mapToDouble(Function<T, Double> mapper) {
		return delegate.mapToDouble(mapper);
	}

	@Override
	public ReadOnlyStringState mapToString(Function<T, String> mapper) {
		return delegate.mapToString(mapper);
	}

	@Override
	public <E> ReadOnlyObjectState<E> mapToObject(Function<T, E> mapper) {
		return delegate.mapToObject(mapper);
	}

}
