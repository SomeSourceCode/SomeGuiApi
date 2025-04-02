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
 * A proxy for {@link ReadOnlyBooleanState} that forwards all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @see ReadOnlyBooleanStateWrapper
 * @since 3.0.0
 */
public class ReadOnlyBooleanStateProxy extends ReadOnlyStateProxy<Boolean> implements ReadOnlyBooleanState {

	private final ReadOnlyBooleanState delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyBooleanState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyBooleanStateProxy(ReadOnlyBooleanState delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public BooleanState asMutableState() {
		return delegate.asMutableState();
	}

	@Override
	public ReadOnlyBooleanState and(boolean value) {
		return delegate.and(value);
	}

	@Override
	public ReadOnlyBooleanState and(ObservableValue<Boolean> value) {
		return delegate.and(value);
	}

	@Override
	public ReadOnlyBooleanState nand(boolean value) {
		return delegate.nand(value);
	}

	@Override
	public ReadOnlyBooleanState nand(ObservableValue<Boolean> value) {
		return delegate.nand(value);
	}

	@Override
	public ReadOnlyBooleanState or(boolean value) {
		return delegate.or(value);
	}

	@Override
	public ReadOnlyBooleanState or(ObservableValue<Boolean> value) {
		return delegate.or(value);
	}

	@Override
	public ReadOnlyBooleanState nor(boolean value) {
		return delegate.nor(value);
	}

	@Override
	public ReadOnlyBooleanState nor(ObservableValue<Boolean> value) {
		return delegate.nor(value);
	}

	@Override
	public ReadOnlyBooleanState implies(boolean value) {
		return delegate.implies(value);
	}

	@Override
	public ReadOnlyBooleanState implies(ObservableValue<Boolean> value) {
		return delegate.implies(value);
	}

	@Override
	public ReadOnlyBooleanState negate() {
		return delegate.negate();
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(boolean trueValue, boolean falseValue) {
		return delegate.selectBoolean(trueValue, falseValue);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, boolean falseValue) {
		return delegate.selectBoolean(trueValue, falseValue);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(boolean trueValue, ObservableValue<Boolean> falseValue) {
		return delegate.selectBoolean(trueValue, falseValue);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, ObservableValue<Boolean> falseValue) {
		return delegate.selectBoolean(trueValue, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(int trueValue, int falseValue) {
		return delegate.selectInteger(trueValue, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, int falseValue) {
		return delegate.selectInteger(trueValue, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(int trueValue, ObservableValue<Integer> falseValue) {
		return delegate.selectInteger(trueValue, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, ObservableValue<Integer> falseValue) {
		return delegate.selectInteger(trueValue, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(long trueValue, long falseValue) {
		return delegate.selectLong(trueValue, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, long falseValue) {
		return delegate.selectLong(trueValue, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(long trueValue, ObservableValue<Long> falseValue) {
		return delegate.selectLong(trueValue, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, ObservableValue<Long> falseValue) {
		return delegate.selectLong(trueValue, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(float trueValue, float falseValue) {
		return delegate.selectFloat(trueValue, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, float falseValue) {
		return delegate.selectFloat(trueValue, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(float trueValue, ObservableValue<Float> falseValue) {
		return delegate.selectFloat(trueValue, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, ObservableValue<Float> falseValue) {
		return delegate.selectFloat(trueValue, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(double trueValue, double falseValue) {
		return delegate.selectDouble(trueValue, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, double falseValue) {
		return delegate.selectDouble(trueValue, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(double trueValue, ObservableValue<Double> falseValue) {
		return delegate.selectDouble(trueValue, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, ObservableValue<Double> falseValue) {
		return delegate.selectDouble(trueValue, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(String trueValue, String falseValue) {
		return delegate.selectString(trueValue, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(ObservableValue<String> trueValue, String falseValue) {
		return delegate.selectString(trueValue, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(String trueValue, ObservableValue<String> falseValue) {
		return delegate.selectString(trueValue, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(ObservableValue<String> trueValue, ObservableValue<String> falseValue) {
		return delegate.selectString(trueValue, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(T trueValue, T falseValue) {
		return delegate.selectObject(trueValue, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, T falseValue) {
		return delegate.selectObject(trueValue, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(T trueValue, ObservableValue<T> falseValue) {
		return delegate.selectObject(trueValue, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, ObservableValue<T> falseValue) {
		return delegate.selectObject(trueValue, falseValue);
	}

}
