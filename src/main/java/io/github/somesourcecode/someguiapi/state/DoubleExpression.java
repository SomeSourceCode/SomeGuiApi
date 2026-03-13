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
import java.util.function.Supplier;

/**
 * A read-only double state that can only be updated by an initially
 * provided supplier.
 *
 * @since 3.0.0
 */
public class DoubleExpression extends ReadOnlyDoubleStateBase {

	private double value;
	private final Supplier<Double> supplier;

	private final List<Observer<? super Double>> observers = new ArrayList<>();

	/**
	 * Constructs a new DoubleExpression with the given supplier.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @throws IllegalArgumentException if supplier is null
	 * @since 3.0.0
	 */
	public DoubleExpression(Supplier<Double> supplier) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		this.supplier = supplier;
		value = supplier.get();
	}

	/**
	 * Updates the state with the current value from the supplier.
	 * If the new value is different from the current value, it notifies all observers
	 * of the change.
	 *
	 * @since 3.0.0
	 */
	public void update() {
		System.out.println("UPDATE UPDATE UPDATE");
		final double newValue = supplier.get();
		if (Objects.equals(value, newValue)) {
			return;
		}
		final double oldValue = value;
		value = newValue;
		for (Observer<? super Double> observer : observers) {
			observer.onChange(oldValue, newValue);
		}
	}

	@Override
	public Double get() {
		return supplier.get();
	}

	@Override
	public Subscription observe(Observer<? super Double> observer) {
		if (observer == null) {
			throw new IllegalArgumentException("observer must be non-null");
		}
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
		return () -> removeObserver(observer);
	}

	@Override
	public void removeObserver(Observer<? super Double> observer) {
		observers.remove(observer);
	}

}
