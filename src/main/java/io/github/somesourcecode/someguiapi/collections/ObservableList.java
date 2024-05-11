/*
 * Copyright 2024, SomeSourceCode - MIT License
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

package io.github.somesourcecode.someguiapi.collections;

import java.util.List;

/**
 * A list that can be observed for changes.
 * <p>
 * A change will be fired whenever elements are added or removed from the list
 * and can be listened to by adding a {@link ListChangeListener}.
 * @param <E> the type of elements in the list
 * @since 1.0.0
 */
public interface ObservableList<E> extends List<E> {

	/**
	 * Adds a listener that is called whenever the list changes.
	 * @param listener the listener
	 * @since 1.0.0
	 */
	void addListener(ListChangeListener<? super E> listener);

	/**
	 * Removes a listener.
	 * @param listener the listener
	 * @since 1.0.0
	 */
	void removeListener(ListChangeListener<? super E> listener);

	/**
	 * A convenience method to add varargs elements to the list.
	 * @param elements the elements to add
	 * @return true if the list was changed as a result of this call
	 * @since 1.0.0
	 */
	boolean addAll(E... elements);

	/**
	 * A convenience method to remove varargs elements from the list.
	 * @param elements the elements to remove
	 * @return true if the list was changed as a result of this call
	 * @since 1.0.0
	 */
	boolean removeAll(E... elements);

}
