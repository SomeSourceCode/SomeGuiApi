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
 * A simple implementation of a mutable {@link LongState}.
 *
 * @see ReadOnlyLongStateWrapper
 * @since 3.0.0
 */
public class SimpleLongState extends ReadOnlyLongStateBase implements LongState {

	private long value;
	private final List<Observer<? super Long>> observers = new ArrayList<>();

	/**
	 * Constructs a new SimpleLongState with the default value of {@code 0}.
	 *
	 * @since 3.0.0
	 */
	public SimpleLongState() {

	}

	/**
	 * Constructs a new SimpleLongState with the specified initial value.
	 *
	 * @param value the initial value of the state
	 * @since 3.0.0
	 */
	public SimpleLongState(long value) {
		set(value);
	}

	@Override
	public void set(Long value) {
		if (isReflecting()) {
			throw new IllegalStateException("Cannot set a value that is reflecting another state");
		}
		doSet(value);
	}

	private void doSet(Long value) {
		value = sanitizeValue(this.value, value);
		if (Objects.equals(this.value, value)) {
			return;
		}
		long oldValue = this.value;
		this.value = value;
		for (Observer<? super Long> observer : observers) {
			observer.onChange(oldValue, value);
		}
	}

	private long sanitizeValue(Long currentValue, Long newValue) {
		final Long sanitizedValue = sanitize(currentValue, newValue);
		return sanitizedValue == null ? 0 : sanitizedValue;
	}

	private final Observer<Long> reflectObserver = (oldValue, newValue) -> {
		doSet(newValue);
	};
	private ObservableValue<? extends Long> reflectedValue;

	@Override
	public void reflect(ObservableValue<? extends Long> other) {
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
	public void mirror(State<Long> other) {
		Mirror.mirror(other, this);
	}

	@Override
	public void detachMirror(State<Long> other) {
		Mirror.detach(other, this);
	}

	@Override
	public Long get() {
		return value;
	}

	@Override
	public Subscription observe(Observer<? super Long> observer) {
		if (observer == null) {
			throw new IllegalArgumentException("observer must be non-null");
		}
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
		return () -> removeObserver(observer);
	}

	@Override
	public void removeObserver(Observer<? super Long> observer) {
		observers.remove(observer);
	}

}
