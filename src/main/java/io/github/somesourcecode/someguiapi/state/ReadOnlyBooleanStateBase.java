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
 * The base class for all boolean states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyBooleanStateBase extends ReadOnlyStateBase<Boolean> implements ReadOnlyBooleanState {

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

	@Override
	public BooleanState asMutableState() {
		final BooleanState state =  new SimpleBooleanState(get());
		state.reflect(this);
		return state;
	}

	@Override
	public ReadOnlyBooleanState and(boolean value) {
		return Reflect.createBooleanReflection(() -> get() && value, this);
	}

	@Override
	public ReadOnlyBooleanState and(ObservableValue<Boolean> value) {
		return Reflect.createBooleanReflection(() -> get() && value.get(), this, value);
	}

	@Override
	public ReadOnlyBooleanState nand(boolean value) {
		return Reflect.createBooleanReflection(() -> !(get() && value), this);
	}

	@Override
	public ReadOnlyBooleanState nand(ObservableValue<Boolean> value) {
		return Reflect.createBooleanReflection(() -> !(get() && value.get()), this, value);
	}

	@Override
	public ReadOnlyBooleanState or(boolean value) {
		return Reflect.createBooleanReflection(() -> get() || value, this);
	}

	@Override
	public ReadOnlyBooleanState or(ObservableValue<Boolean> value) {
		return Reflect.createBooleanReflection(() -> get() || value.get(), this, value);
	}

	@Override
	public ReadOnlyBooleanState nor(boolean value) {
		return Reflect.createBooleanReflection(() -> !(get() || value), this);
	}

	@Override
	public ReadOnlyBooleanState nor(ObservableValue<Boolean> value) {
		return Reflect.createBooleanReflection(() -> !(get() || value.get()), this, value);
	}

	@Override
	public ReadOnlyBooleanState implies(boolean value) {
		return Reflect.createBooleanReflection(() -> !get() || value, this);
	}

	@Override
	public ReadOnlyBooleanState implies(ObservableValue<Boolean> value) {
		return Reflect.createBooleanReflection(() -> !get() || value.get(), this, value);
	}

	@Override
	public ReadOnlyBooleanState negate() {
		return Reflect.createBooleanReflection(() -> !get(), this);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(boolean trueValue, boolean falseValue) {
		if (trueValue == falseValue) {
			return Reflect.createBooleanReflection(() -> trueValue);
		}
		return Reflect.createBooleanReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, boolean falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createBooleanReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(boolean trueValue, ObservableValue<Boolean> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createBooleanReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyBooleanState selectBoolean(ObservableValue<Boolean> trueValue, ObservableValue<Boolean> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createBooleanReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(int trueValue, int falseValue) {
		if (trueValue == falseValue) {
			return Reflect.createIntegerReflection(() -> trueValue);
		}
		return Reflect.createIntegerReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, int falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createIntegerReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(int trueValue, ObservableValue<Integer> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createIntegerReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyIntegerState selectInteger(ObservableValue<Integer> trueValue, ObservableValue<Integer> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createIntegerReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(long trueValue, long falseValue) {
		if (trueValue == falseValue) {
			return Reflect.createLongReflection(() -> trueValue);
		}
		return Reflect.createLongReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, long falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createLongReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyLongState selectLong(long trueValue, ObservableValue<Long> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createLongReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyLongState selectLong(ObservableValue<Long> trueValue, ObservableValue<Long> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createLongReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(float trueValue, float falseValue) {
		if (trueValue == falseValue) {
			return Reflect.createFloatReflection(() -> trueValue);
		}
		return Reflect.createFloatReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, float falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createFloatReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(float trueValue, ObservableValue<Float> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createFloatReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyFloatState selectFloat(ObservableValue<Float> trueValue, ObservableValue<Float> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createFloatReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(double trueValue, double falseValue) {
		if (trueValue == falseValue) {
			return Reflect.createDoubleReflection(() -> trueValue);
		}
		return Reflect.createDoubleReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, double falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createDoubleReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(double trueValue, ObservableValue<Double> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createDoubleReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyDoubleState selectDouble(ObservableValue<Double> trueValue, ObservableValue<Double> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createDoubleReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(String trueValue, String falseValue) {
		return Reflect.createStringReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public ReadOnlyStringState selectString(ObservableValue<String> trueValue, String falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createStringReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public ReadOnlyStringState selectString(String trueValue, ObservableValue<String> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createStringReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public ReadOnlyStringState selectString(ObservableValue<String> trueValue, ObservableValue<String> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createStringReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(T trueValue, T falseValue) {
		return Reflect.createObjectReflection(() -> get() ? trueValue : falseValue, this);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, T falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		return Reflect.createObjectReflection(() -> get() ? trueValue.get() : falseValue, this, trueValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(T trueValue, ObservableValue<T> falseValue) {
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createObjectReflection(() -> get() ? trueValue : falseValue.get(), this, falseValue);
	}

	@Override
	public <T> ReadOnlyObjectState<T> selectObject(ObservableValue<T> trueValue, ObservableValue<T> falseValue) {
		if (trueValue == null) {
			throw new IllegalArgumentException("trueValue must be non-null");
		}
		if (falseValue == null) {
			throw new IllegalArgumentException("falseValue must be non-null");
		}
		return Reflect.createObjectReflection(() -> get() ? trueValue.get() : falseValue.get(), this, trueValue, falseValue);
	}

}
