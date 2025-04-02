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
 * A simple implementation of a mutable {@link FloatState}.
 *
 * @since 3.0.0
 */
public class SimpleFloatState extends ReadOnlyFloatStateBase implements FloatState {

	private float value;
	private final List<Observer<? super Float>> observers = new ArrayList<>();

	/**
	 * Constructs a new SimpleFloatState with the default value of {@code 0}.
	 *
	 * @since 3.0.0
	 */
	public SimpleFloatState() {

	}

	/**
	 * Constructs a new SimpleFloatState with the specified initial value.
	 *
	 * @param value the initial value of the state
	 * @since 3.0.0
	 */
	public SimpleFloatState(float value) {
		set(value);
	}

	@Override
	public void set(Float value) {
		if (isReflecting()) {
			throw new IllegalStateException("Cannot set a value that is reflecting another state");
		}
		doSet(value);
	}

	private void doSet(Float value) {
		if (Objects.equals(this.value, value)) {
			return;
		}
		float oldValue = this.value;
		this.value = value == null ? 0 : value;
		for (Observer<? super Float> observer : observers) {
			observer.onChange(oldValue, value);
		}
	}

	private final Observer<Float> reflectObserver = (oldValue, newValue) -> {
		doSet(newValue);
	};
	private ObservableValue<? extends Float> reflectedValue;

	@Override
	public void reflect(ObservableValue<? extends Float> other) {
		if (other == null) {
			detach();
			return;
		}
		if (isReflecting()) {
			throw new IllegalStateException("Already reflecting. Detach first");
		}
		if (other == this) {
			throw new IllegalStateException("Cannot reflect self");
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
	public void mirror(State<Float> other) {
		Mirror.mirror(other, this);
	}

	@Override
	public void detachMirror(State<Float> other) {
		Mirror.detach(other, this);
	}

	@Override
	public Float get() {
		return value;
	}

	@Override
	public void observe(Observer<? super Float> observer) {
		if (observer == null) {
			throw new IllegalArgumentException("observer must be non-null");
		}
		if (observers.contains(observer)) {
			return;
		}
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<? super Float> observer) {
		observers.remove(observer);
	}

}
