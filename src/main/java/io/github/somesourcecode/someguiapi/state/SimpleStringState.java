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
import java.util.Objects;

/**
 * A simple implementation of a mutable {@link StringState}.
 *
 * @since 3.0.0
 */
public class SimpleStringState extends ReadOnlyStringStateBase implements StringState {

	private String value;

	private final List<Observer<? super String>> observers = new ArrayList<>();

	/**
	 * Constructs a new SimpleStringState with the default value of {@code null}.
	 *
	 * @since 3.0.0
	 */
	public SimpleStringState() {

	}

	/**
	 * Constructs a new SimpleStringState with the specified initial value.
	 *
	 * @param value the initial value of the state
	 * @since 3.0.0
	 */
	public SimpleStringState(String value) {
		this.value = value;
	}

	@Override
	public void set(String value) {
		if (Objects.equals(this.value, value)) {
			return;
		}
		String oldValue = this.value;
		this.value = value;
		for (Observer<? super String> observer : observers) {
			observer.onChange(oldValue, value);
		}
	}

	private final Observer<String> reflectObserver = (oldValue, newValue) -> {
		set(newValue);
	};
	private ObservableValue<? extends String> reflectedValue;

	@Override
	public void reflect(ObservableValue<? extends String> other) {
		if (other == null) {
			detach();
			return;
		}
		if (isReflecting()) {
			throw new IllegalStateException("Already reflecting");
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
	public void mirror(State<String> other) {
		Mirror.mirror(other, this);
	}

	@Override
	public void detachMirror(State<String> other) {
		Mirror.detach(other, this);
	}

	@Override
	public String get() {
		return value;
	}

	@Override
	public void observe(Observer<? super String> observer) {
		if (observer == null) {
			throw new IllegalArgumentException("observer must be non-null");
		}
		if (observers.contains(observer)) {
			return;
		}
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<? super String> observer) {
		observers.remove(observer);
	}

}
