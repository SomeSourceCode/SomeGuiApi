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
 * The basis for all mutable states.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public interface State<T> extends ReadOnlyState<T> {

	/**
	 * Sets the value of this state.
	 *
	 * @param value the new value
	 * @throws IllegalStateException if this state is currently reflecting another observable
	 * @since 3.0.0
	 */
	void set(T value);

	/**
	 * Reflects the value of the given {@link ObservableValue} into this state.
	 * This means that the value of this state will always be updated
	 * according to the value of the given observable.
	 * <p>
	 * By reflecting a value, it cannot be set directly anymore until
	 * {@link #detach()} is called.
	 *
	 * @param other the observable to reflect
	 * @throws IllegalArgumentException if the given observable is the same as this state
	 * @since 3.0.0
	 */
	void reflect(ObservableValue<? extends T> other);

	/**
	 * Returns whether this state is currently reflecting another observable.
	 *
	 * @return {@code true} if this state is reflecting another observable, {@code false} otherwise
	 * @since 3.0.0
	 */
	boolean isReflecting();

	/**
	 * Detaches this state from the observable it is reflecting. If
	 * this state is not reflecting another observable, this method
	 * does nothing.
	 *
	 * @since 3.0.0
	 */
	void detach();

	/**
	 * Creates a two-directional binding (mirror) between this state and
	 * the given state. When one of the states is changed, the other state
	 * is updated to reflect the change.
	 * <p>
	 * If this state currently has a different value than the given state,
	 * the value of this state will be updated to the value of the given state.
	 *
	 * @param other the state to mirror
	 * @throws IllegalArgumentException if the given state is null or either state is
	 *                                  currently reflecting another observable
	 * @since 3.0.0
	 */
	void mirror(State<T> other);

	/**
	 * Detaches this state from the given state. This will remove the mirror
	 * between the two states, and the states will no longer be updated when one of
	 * them changes.
	 * <p>
	 * If this state is not mirroring the given state, this method does nothing.
	 *
	 * @param other the state to detach from
	 * @since  3.0.0
	 */
	void detachMirror(State<T> other);

}
