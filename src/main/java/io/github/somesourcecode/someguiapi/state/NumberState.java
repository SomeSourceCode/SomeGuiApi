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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The basis for all mutable number states.
 * Those include {@link IntegerState}, {@link LongState}, {@link FloatState}, and {@link DoubleState}.
 *
 * @param <T> the number type of the state's value
 */
public interface NumberState<T extends Number> extends ReadOnlyNumberState<T>, State<T> {

	/**
	 * A list of all supported number types sorted by their hierarchy (from most to least specific).
	 */
	List<Class<? extends Number>> TYPES = List.of(Double.class, Float.class, Long.class, Integer.class);

	/**
	 * Finds the common number type of the given {@link ObservableValue}s.
	 * For example, if the given values are {@link IntegerState} and {@link LongState},
	 * the common type will be {@link Long}.
	 *
	 * @param values the observable values to find the common type for
	 * @return the common number type
	 * @since 3.0.0
	 */
	@SuppressWarnings({"unchecked"})
	static Class<? extends Number> findCommonNumberType(ObservableValue<? extends Number>... values) {
		return (Class<? extends Number>) Arrays.stream(values)
				.map(ObservableValue::getType)
				.filter(TYPES::contains)
				.min(Comparator.comparingInt(TYPES::indexOf))
				.orElseThrow(() -> new IllegalArgumentException("No valid number type found"));
	}

}
