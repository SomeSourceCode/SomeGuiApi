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
 * A proxy for {@link ReadOnlyObjectState} that forwards all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @param <T> the type of the state's value
 * @see ReadOnlyObjectStateWrapper
 * @since 3.0.0
 */
public class ReadOnlyObjectStateProxy<T> extends ReadOnlyStateProxy<T> implements ReadOnlyObjectState<T> {

	private final ReadOnlyObjectState<T> delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyObjectState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyObjectStateProxy(ReadOnlyObjectState<T> delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public ObjectState<T> asMutableState() {
		return delegate.asMutableState();
	}

	@Override
	public ReadOnlyBooleanState isNull() {
		return delegate.isNull();
	}

	@Override
	public ReadOnlyBooleanState isNotNull() {
		return delegate.isNotNull();
	}

	@Override
	public ReadOnlyObjectState<T> orElse(T value) {
		return delegate.orElse(value);
	}

	@Override
	public ReadOnlyObjectState<T> orElse(ObservableValue<T> value) {
		return delegate.orElse(value);
	}

}
