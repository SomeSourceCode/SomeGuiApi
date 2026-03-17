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
 * A read-only state of type {@link Object}.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public interface ReadOnlyObjectState<T> extends ReadOnlyState<T> {

	ObjectState<T> asMutableState();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is null, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNull();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is not null, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotNull();

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds the value of this state
	 * if it is not null, or the given value otherwise.
	 *
	 * @param value the value to use if this state is null
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	ReadOnlyObjectState<T> orElse(T value);

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds the value of this state
	 * if it is not null, or the value of the given {@link ObservableValue} otherwise.
	 * <p>
	 * Note that this can still result in a null value if the value of the
	 * given observable is null.
	 *
	 * @param value the observable to use if this state is null
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	ReadOnlyObjectState<T> orElse(ObservableValue<T> value);

}
