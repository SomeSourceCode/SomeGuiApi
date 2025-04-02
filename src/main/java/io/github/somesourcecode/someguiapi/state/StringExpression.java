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
 * A read-only string state that can only be updated by an initially
 * provided supplier.
 *
 * @since 3.0.0
 */
public class StringExpression extends ReadOnlyStringStateBase {

	private String value;
	private final Supplier<String> supplier;

	private final List<Observer<? super String>> observers = new ArrayList<>();

	/**
	 * Constructs a new StringExpression with the given supplier.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @throws IllegalArgumentException if supplier is null
	 * @since 3.0.0
	 */
	public StringExpression(Supplier<String> supplier) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		this.supplier = supplier;
		value = supplier.get();
	}

	public void update() {
		final String newValue = supplier.get();
		if (Objects.equals(value, newValue)) {
			return;
		}
		final String oldValue = value;
		value = newValue;
		for (Observer<? super String> observer : observers) {
			observer.onChange(oldValue, newValue);
		}
	}

	@Override
	public String get() {
		return supplier.get();
	}

	@Override
	public void observe(Observer<? super String> observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<? super String> observer) {
		observers.remove(observer);
	}

}
