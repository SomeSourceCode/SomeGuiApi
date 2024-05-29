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

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A decorator for an {@link ObservableList} that allows to veto changes to the list.
 * <p>
 * This class works as a wrapper around any {@link ObservableList} and fires
 * change events whenever elements are added or removed.
 *
 * @param <E> the type of elements in the list
 * @since 2.1.0
 */
public abstract class VetoableListDecorator<E> implements ObservableList<E> {

	private final ObservableList<E> baseList;

	public VetoableListDecorator(ObservableList<E> baseList) {
		this.baseList = baseList;
	}

	@Override
	public void addListener(ListChangeListener<? super E> listener) {
		baseList.addListener(listener);
	}

	@Override
	public void removeListener(ListChangeListener<? super E> listener) {
		baseList.removeListener(listener);
	}

	/**
	 * Called whenever a change is proposed to the list.
	 * <p>
	 * {@code toBeAdded} contains the elements that are proposed to be added to the list.
	 * {@code toBeRemoved} contains the indices of the elements that are proposed to be removed from the list.
	 * They are listed in pairs of two, where the first index is the start index (inclusive) and the
	 * second index is the end index (exclusive).
	 *
	 * @param toBeAdded the elements to be added
	 * @param toBeRemoved the indices of the elements to be removed
	 * @throws IllegalStateException if the change is vetoed
	 * @since 2.1.0
	 */
	protected abstract void onProposedChange(List<E> toBeAdded, int... toBeRemoved);

	private void removeFromList(Collection<?> collection, boolean complement) {
		int[] toBeRemoved = new int[0];
		int pointer = 0;
		for (int i = 0; i < size(); i++) {
			final E element = get(i);
			if ((collection.contains(element) ^ complement)) {
				if (pointer % 2 == 0) {
					if (toBeRemoved.length == pointer) {
						toBeRemoved = Arrays.copyOf(toBeRemoved, toBeRemoved.length + 2);
					}
					toBeRemoved[pointer] = i;
					pointer++;
				}
			} else {
				if (pointer % 2 == 1) {
					toBeRemoved[pointer] = i;
					pointer++;
				}
			}
		}
		if (pointer % 2 == 1) {
			toBeRemoved[pointer] = size();
		}
		onProposedChange(Collections.emptyList(), toBeRemoved);
	}

	@Override
	public int size() {
		return baseList.size();
	}

	@Override
	public boolean isEmpty() {
		return baseList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return baseList.contains(o);
	}

	@NotNull
	@Override
	public Iterator<E> iterator() {
		return baseList.iterator();
	}

	@NotNull
	@Override
	public Object @NotNull [] toArray() {
		return baseList.toArray();
	}

	@NotNull
	@Override
	public <T> T @NotNull [] toArray(@NotNull T[] a) {
		return baseList.toArray(a);
	}

	@Override
	public boolean add(E e) {
		onProposedChange(Collections.singletonList(e));
		return baseList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		onProposedChange(Collections.emptyList(), indexOf(o));
		return baseList.remove(o);
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> c) {
		return new HashSet<>(baseList).containsAll(c);
	}

	@Override
	public boolean addAll(E... elements) {
		return addAll(Arrays.asList(elements));
	}

	@Override
	public boolean addAll(@NotNull Collection<? extends E> c) {
		onProposedChange(List.copyOf(c));
		return baseList.addAll(c);
	}

	@Override
	public boolean addAll(int index, @NotNull Collection<? extends E> c) {
		onProposedChange(List.copyOf(c));
		return baseList.addAll(index, c);
	}

	@Override
	public boolean removeAll(E... elements) {
		return removeAll(Arrays.asList(elements));
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> c) {
		removeFromList(c, false);
		return baseList.removeAll(c);
	}

	@Override
	public boolean retainAll(E... elements) {
		return retainAll(Arrays.asList(elements));
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		removeFromList(c, true);
		return baseList.retainAll(c);
	}

	@Override
	public void clear() {
		onProposedChange(Collections.emptyList(), 0, size());
		baseList.clear();
	}

	@Override
	public E get(int index) {
		return baseList.get(index);
	}

	@Override
	public E set(int index, E element) {
		onProposedChange(Collections.singletonList(element), index, index + 1);
		return baseList.set(index, element);
	}

	@Override
	public void add(int index, E element) {
		onProposedChange(Collections.singletonList(element));
		baseList.add(index, element);
	}

	@Override
	public E remove(int index) {
		onProposedChange(Collections.emptyList(), index, index + 1);
		return baseList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return baseList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return baseList.lastIndexOf(o);
	}

	@NotNull
	@Override
	public ListIterator<E> listIterator() {
		return baseList.listIterator();
	}

	@NotNull
	@Override
	public ListIterator<E> listIterator(int index) {
		return baseList.listIterator(index);
	}

	@NotNull
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return baseList.subList(fromIndex, toIndex);
	}

}
