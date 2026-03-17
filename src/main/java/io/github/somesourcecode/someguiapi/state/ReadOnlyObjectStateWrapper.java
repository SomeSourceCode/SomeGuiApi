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
 * A wrapper for a mutable {@link ObjectState} that provides a read-only view.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public class ReadOnlyObjectStateWrapper<T> extends SimpleObjectState<T> implements ReadOnlyStateWrapper<T> {

	private ReadOnlyObjectState<T> readOnlyState;

	/**
	 * Constructs a new ReadOnlyDoubleStateWrapper with the default value of {@code null}.
	 *
	 * @since 3.0.0
	 */
	public ReadOnlyObjectStateWrapper() {

	}

	/**
	 * Constructs a new ReadOnlyDoubleStateWrapper with the specified initial value.
	 *
	 * @param value the initial value of the state
	 * @since 3.0.0
	 */
	public ReadOnlyObjectStateWrapper(T value) {
		super(value);
	}

	@Override
	public ReadOnlyObjectState<T> readOnly() {
		if (readOnlyState == null) {
			readOnlyState = new ReadOnlyObjectStateProxy<>(this);
		}
		return readOnlyState;
	}

}
