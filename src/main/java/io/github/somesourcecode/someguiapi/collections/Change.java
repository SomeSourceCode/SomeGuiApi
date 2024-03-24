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

public class Change<E> {

	private final List<? extends E> addedSubList;
	private final List<? extends E> removedSubList;

	private final boolean wasAdded;
	private final boolean wasRemoved;

	public Change(List<? extends E> addedSubList, List<? extends E> removedSubList) {
		this.wasAdded = addedSubList != null && !addedSubList.isEmpty();
		this.wasRemoved = removedSubList != null && !removedSubList.isEmpty();

		this.addedSubList = wasAdded ? Collections.unmodifiableList(addedSubList) : Collections.emptyList();
		this.removedSubList = wasRemoved ? Collections.unmodifiableList(removedSubList) : Collections.emptyList();
	}

	public List<? extends E> getAddedSubList() {
		return addedSubList;
	}

	public List<? extends E> getRemovedSubList() {
		return removedSubList;
	}

	public boolean wasAdded() {
		return wasAdded;
	}

	public boolean wasRemoved() {
		return wasRemoved;
	}

}
