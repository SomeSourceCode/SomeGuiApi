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

import java.util.Collections;
import java.util.List;

/**
 * Represents a change in a list. It contains a list of elements that were added and
 * a list of elements that were removed. If no elements were added or removed, the
 * corresponding lists will be empty.
 * @param <E>
 */
public class Change<E> {

	private final List<? extends E> addedSubList;
	private final List<? extends E> removedSubList;

	private final boolean wasAdded;
	private final boolean wasRemoved;

	/**
	 * Constructs a new change with the specified added and removed sublists.
	 * @param addedSubList the added sublist
	 * @param removedSubList the removed sublist
	 */
	public Change(List<? extends E> addedSubList, List<? extends E> removedSubList) {
		this.wasAdded = addedSubList != null && !addedSubList.isEmpty();
		this.wasRemoved = removedSubList != null && !removedSubList.isEmpty();

		this.addedSubList = wasAdded ? Collections.unmodifiableList(addedSubList) : Collections.emptyList();
		this.removedSubList = wasRemoved ? Collections.unmodifiableList(removedSubList) : Collections.emptyList();
	}

	/**
	 * Returns the list of elements that were added.
	 * If no elements were added, the list will be empty.
	 * @return the list of elements that were added
	 */
	public List<? extends E> getAddedSubList() {
		return addedSubList;
	}

	/**
	 * Returns the list of elements that were removed.
	 * If no elements were removed, the list will be empty.
	 * @return the list of elements that were removed
	 */
	public List<? extends E> getRemovedSubList() {
		return removedSubList;
	}

	/**
	 * Returns whether elements were added.
	 * @return whether elements were added
	 */
	public boolean wasAdded() {
		return wasAdded;
	}

	/**
	 * Returns whether elements were removed.
	 * @return whether elements were removed
	 */
	public boolean wasRemoved() {
		return wasRemoved;
	}

}
