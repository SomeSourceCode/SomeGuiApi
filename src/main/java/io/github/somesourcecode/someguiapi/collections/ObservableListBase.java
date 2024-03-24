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
 * A base implementation of an {@link ObservableList}.
 * <p>
 * This class works as a wrapper around any {@link List} and fires
 * change events whenever elements are added or removed.
 * @param <E> the type of elements in the list
 */
public class ObservableListBase<E> implements ObservableList<E> {

	private final List<E> baseList;
	private final List<ListChangeListener<? super E>> listeners = new ArrayList<>();

	/**
	 * Constructs a new observable list with the specified base list.
	 * @param baseList the base list
	 */
	public ObservableListBase(List<E> baseList) {
		this.baseList = baseList;
	}

	@Override
	public void addListener(ListChangeListener<? super E> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ListChangeListener<? super E> listener) {
		listeners.remove(listener);
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

	@Override
	public @NotNull Iterator<E> iterator() {
		return baseList.iterator();
	}

	@Override
	public Object @NotNull [] toArray() {
		return baseList.toArray();
	}

	@Override
	public <T> T @NotNull [] toArray(T @NotNull [] a) {
		return baseList.toArray(a);
	}

	@Override
	public boolean add(E e) {
		baseList.add(e);
		Change<E> change = new Change<>(Collections.singletonList(e), null);
		fireChange(change);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		boolean result = baseList.remove(o);
		if (result) {
			Change<E> change = new Change<>(null, Collections.singletonList((E) o));
			fireChange(change);
		}
		return result;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return new HashSet<>(baseList).containsAll(c);
	}

	@Override
	public boolean addAll(@NotNull Collection<? extends E> c) {
		boolean result = baseList.addAll(c);

		if (result) {
			Change<E> change = new Change<>(new ArrayList<>(c), null);
			fireChange(change);
		}

		return result;
	}

	@Override
	public boolean addAll(int index, @NotNull Collection<? extends E> c) {
		boolean result = baseList.addAll(index, c);

		if (result) {
			Change<E> change = new Change<>(new ArrayList<>(c), null);
			fireChange(change);
		}

		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		List<E> originalList = new ArrayList<>(baseList);
		boolean result = baseList.removeAll(c);

		if (result) {
			List<E> removedElements = new ArrayList<>();
			for (E element : originalList) {
				if (!baseList.contains(element)) {
					removedElements.add(element);
				}
			}
			Change<E> change = new Change<>(null, removedElements);
			fireChange(change);
		}

		return result;
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		List<E> originalList = new ArrayList<>(baseList);
		boolean result = baseList.retainAll(c);

		if (result) {
			List<E> removedElements = new ArrayList<>();
			for (E element : originalList) {
				if (!baseList.contains(element)) {
					removedElements.add(element);
				}
			}
			Change<E> change = new Change<>(null, removedElements);
			fireChange(change);
		}

		return result;
	}

	@Override
	public void clear() {
		List<E> removedElements = new ArrayList<>(baseList);
		baseList.clear();
		Change<E> change = new Change<>(null, removedElements);
		fireChange(change);
	}

	@Override
	public E get(int index) {
		return baseList.get(index);
	}

	@Override
	public E set(int index, E element) {
		E baseElement = baseList.set(index, element);
		if (baseElement != element) {
			Change<E> change = new Change<>(Collections.singletonList(element), Collections.singletonList(baseElement));
			fireChange(change);
		}
		return baseElement;
	}

	@Override
	public void add(int index, E element) {
		baseList.add(index, element);
		Change<E> change = new Change<>(Collections.singletonList(element), null);
		fireChange(change);
	}

	@Override
	public E remove(int index) {
		E removedElement = baseList.remove(index);
		Change<E> change = new Change<>(null, Collections.singletonList(removedElement));
		fireChange(change);
		return removedElement;
	}

	@Override
	public int indexOf(Object o) {
		return baseList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return baseList.lastIndexOf(o);
	}

	@Override
	public @NotNull ListIterator<E> listIterator() {
		return baseList.listIterator();
	}

	@Override
	public @NotNull ListIterator<E> listIterator(int index) {
		return baseList.listIterator(index);
	}

	@Override
	public @NotNull List<E> subList(int fromIndex, int toIndex) {
		return baseList.subList(fromIndex, toIndex);
	}

	private void fireChange(Change<E> change) {
		for (ListChangeListener<? super E> listener : listeners) {
			listener.onChanged(change);
		}
	}

}
