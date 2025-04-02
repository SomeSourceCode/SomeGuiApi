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
 * An observable value that can be observed for changes
 * using an {@link Observer}.
 *
 * @param <T>
 * @since 3.0.0
 */
public interface ObservableValue<T> {

	/**
	 * Returns the type this value can be safely assumed to be.
	 * This is not necessarily the same as the type of the value itself.
	 *
	 * @return the type
	 * @since 3.0.0
	 */
	Class<? super T> getType();

	/**
	 * Returns the current value.
	 *
	 * @return the current value
	 * @since 3.0.0
	 */
	T get();

	/**
	 * Registers an {@link Observer} to be notified of changes to this value.
	 *
	 * @param observer the observer to register
	 * @throws IllegalArgumentException if observer is null
	 * @since 3.0.0
	 */
	void observe(Observer<? super T> observer);

	/**
	 * Unregisters an {@link Observer} from this value.
	 *
	 * @param observer the observer to unregister
	 * @since 3.0.0
	 */
	void removeObserver(Observer<? super T> observer);

}
