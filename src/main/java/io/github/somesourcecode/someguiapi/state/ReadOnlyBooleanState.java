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

/**
 * A read-only state of type {@link Boolean}.
 *
 * @since 3.0.0
 */
public interface ReadOnlyBooleanState extends ReadOnlyState<Boolean> {

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if both the value of this state
	 * and the given value are {@code true}, {@code false} otherwise.
	 *
	 * @param value the value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState and(boolean value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if both the value of this state
	 * and the one of the given {@link ObservableValue} are {@code true}, {@code false} otherwise.
	 *
	 * @param value the observable
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState and(ObservableValue<Boolean> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if, of the value of this state
	 * and the given value, at most one is {@code true}, {@code false} otherwise. This is the negation of
	 * {@link #and(boolean)}.
	 *
	 * @param value the value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState nand(boolean value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if, of the value of this state
	 * and the one of the given {@link ObservableValue}, at most one is {@code true}, {@code false} otherwise.
	 * This is the negation of {@link #and(ObservableValue)}.
	 *
	 * @param value the observable
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState nand(ObservableValue<Boolean> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if, of the value of this state
	 * and the given value, at least one is {@code true}, {@code false} otherwise.
	 * <p>
	 * For an exclusive or, use {@link #isNotEqualTo(Object)}.
	 *
	 * @param value the value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState or(boolean value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if, of the value of this state
	 * and the one of the given {@link ObservableValue}, at least one is {@code true}, {@code false} otherwise.
	 * <p>
	 * For an exclusive or, use {@link #isNotEqualTo(ObservableValue)}.
	 *
	 * @param value the observable
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState or(ObservableValue<Boolean> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if both the value of this state
	 * and the given value are {@code false}, {@code false} otherwise. This is the negation of
	 * {@link #or(boolean)}.
	 *
	 * @param value the value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState nor(boolean value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if both the value of this state
	 * and the one of the given {@link ObservableValue} are {@code false}, {@code false} otherwise
	 * This is the negation of {@link #or(ObservableValue)}.
	 *
	 * @param value the observable
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState nor(ObservableValue<Boolean> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} unless this state
	 * is {@code true} and the given value is {@code false}.
	 * <p>
	 * This represents logical implication ({@code A → B}), which is equivalent to
	 * {@code !A || B}.
	 *
	 * @param value the value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState implies(boolean value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} unless this state
	 * is {@code true} and the one of the given {@link ObservableValue} is {@code false}.
	 * <p>
	 * This represents logical implication ({@code A → B}), which is equivalent to
	 * {@code !A || B}.
	 *
	 * @param value the observable
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState implies(ObservableValue<Boolean> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is the negation of this state.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState negate();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState selectBoolean(boolean trueValue, boolean falseValue);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, boolean falseValue);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState selectBoolean(boolean trueValue, ObservableValue<Boolean> falseValue);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, ObservableValue<Boolean> falseValue);

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState selectInteger(int trueValue, int falseValue);

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, int falseValue);

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState selectInteger(int trueValue, ObservableValue<Integer> falseValue);

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, ObservableValue<Integer> falseValue);

	/**
	 * Creates a new {@link ReadOnlyLongState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyLongState}
	 * @since 3.0.0
	 */
	ReadOnlyLongState selectLong(long trueValue, long falseValue);

	/**
	 * Creates a new {@link ReadOnlyLongState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyLongState}
	 * @since 3.0.0
	 */
	ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, long falseValue);

	/**
	 * Creates a new {@link ReadOnlyLongState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyLongState}
	 * @since 3.0.0
	 */
	ReadOnlyLongState selectLong(long trueValue, ObservableValue<Long> falseValue);

	/**
	 * Creates a new {@link ReadOnlyLongState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyLongState}
	 * @since 3.0.0
	 */
	ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, ObservableValue<Long> falseValue);

	/**
	 * Creates a new {@link ReadOnlyFloatState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyFloatState}
	 * @since 3.0.0
	 */
	ReadOnlyFloatState selectFloat(float trueValue, float falseValue);

	/**
	 * Creates a new {@link ReadOnlyFloatState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyFloatState}
	 * @since 3.0.0
	 */
	ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, float falseValue);

	/**
	 * Creates a new {@link ReadOnlyFloatState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyFloatState}
	 * @since 3.0.0
	 */
	ReadOnlyFloatState selectFloat(float trueValue, ObservableValue<Float> falseValue);

	/**
	 * Creates a new {@link ReadOnlyFloatState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyFloatState}
	 * @since 3.0.0
	 */
	ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, ObservableValue<Float> falseValue);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState selectDouble(double trueValue, double falseValue);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, double falseValue);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState selectDouble(double trueValue, ObservableValue<Double> falseValue);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, ObservableValue<Double> falseValue);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState selectString(String trueValue, String falseValue);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState selectString(ObservableValue<String> trueValue, String falseValue);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState selectString(String trueValue, ObservableValue<String> falseValue);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState selectString(ObservableValue<String> trueValue, ObservableValue<String> falseValue);

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the value to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	<T> ReadOnlyObjectState<T> selectObject(T trueValue, T falseValue);

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	<T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, T falseValue);

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the value to use if this state is {@code false}
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	<T> ReadOnlyObjectState<T> selectObject(T trueValue, ObservableValue<T> falseValue);

	/**
	 * Creates a new {@link ReadOnlyObjectState} that holds the value of {@code trueValue}
	 * if this state is {@code true}, the value of {@code falseValue} otherwise.
	 *
	 * @param trueValue the observable to use if this state is {@code true}
	 * @param falseValue the observable to use if this state is {@code false}
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	<T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, ObservableValue<T> falseValue);

}
