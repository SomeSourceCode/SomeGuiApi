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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of a mutable {@link ObjectState}.
 *
 * @param <T> the type of the state's value
 * @see ReadOnlyObjectStateWrapper
 * @since 3.0.0
 */
public class SimpleObjectState<T> extends ReadOnlyObjectStateBase<T> implements ObjectState<T> {

	private T value;

	private final List<Observer<? super T>> observers = new ArrayList<>();

	/**
	 * Constructs a new SimpleObjectState with the default value of {@code null}.
	 *
	 * @since 3.0.0
	 */
	public SimpleObjectState() {

	}

	/**
	 * Constructs a new SimpleObjectState with the specified initial value.
	 *
	 * @param value the initial value of the state
	 * @since 3.0.0
	 */
	public SimpleObjectState(T value) {
		this.value = value;
	}

	@Override
	public void set(T value) {
		if (this.value == value) {
			return;
		}
		T oldValue = this.value;
		this.value = value;
		for (Observer<? super T> observer : observers) {
			observer.onChange(oldValue, value);
		}
	}

	private final Observer<T> reflectObserver = (T oldValue, T newValue) -> {
		set(newValue);
	};
	private ObservableValue<? extends T> reflectedValue;

	@Override
	public void reflect(ObservableValue<? extends T> other) {
		if (other == null) {
			throw new IllegalArgumentException("Cannot reflect null");
		}
		if (other.equals(this)) {
			throw new IllegalArgumentException("Cannot reflect self");
		}
		if (other == reflectedValue) {
			return;
		}
		if (isReflecting()) {
			detach();
		}
		set(other.get());
		this.reflectedValue = other;
		other.observe(reflectObserver);
	}

	@Override
	public boolean isReflecting() {
		return reflectedValue != null;
	}

	@Override
	public void detach() {
		if (!isReflecting()) {
			return;
		}
		reflectedValue.removeObserver(reflectObserver);
		reflectedValue = null;
	}

	@Override
	public void mirror(State<T> other) {
		Mirror.mirror(other, this);
	}

	@Override
	public void detachMirror(State<T> other) {
		Mirror.detach(other, this);
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void observe(Observer<? super T> observer) {
		if (observer == null) {
			throw new IllegalArgumentException("observer must be non-null");
		}
		if (observers.contains(observer)) {
			return;
		}
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<? super T> observer) {
		observers.remove(observer);
	}

}
