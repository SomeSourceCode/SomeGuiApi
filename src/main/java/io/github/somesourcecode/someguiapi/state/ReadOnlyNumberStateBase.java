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
 * The base class for all number states.
 *
 * @param <T> the type of the number
 * @since 3.0.0
 */
public abstract class ReadOnlyNumberStateBase<T extends Number> extends ReadOnlyStateBase<T> implements ReadOnlyNumberState<T> {

	@Override
	public int intValue() {
		return get().intValue();
	}

	@Override
	public double doubleValue() {
		return get().doubleValue();
	}

	@Override
	public long longValue() {
		return get().longValue();
	}

	@Override
	public float floatValue() {
		return get().floatValue();
	}

	@Override
	public ReadOnlyIntegerState asIntegerState() {
		if (this instanceof ReadOnlyIntegerState) {
			return (ReadOnlyIntegerState) this;
		}
		return Reflect.createIntegerReflection(this::intValue, this);
	}

	@Override
	public ReadOnlyLongState asLongState() {
		if (this instanceof ReadOnlyLongState) {
			return (ReadOnlyLongState) this;
		}
		return Reflect.createLongReflection(this::longValue, this);
	}

	@Override
	public ReadOnlyFloatState asFloatState() {
		if (this instanceof ReadOnlyFloatState) {
			return (ReadOnlyFloatState) this;
		}
		return Reflect.createFloatReflection(this::floatValue, this);
	}

	@Override
	public ReadOnlyDoubleState asDoubleState() {
		if (this instanceof ReadOnlyDoubleState) {
			return (ReadOnlyDoubleState) this;
		}
		return Reflect.createDoubleReflection(this::doubleValue, this);
	}

	@Override
	public IntegerState asMutableIntegerState() {
		return asIntegerState().asMutableState();
	}

	@Override
	public LongState asMutableLongState() {
		return asLongState().asMutableState();
	}

	@Override
	public FloatState asMutableFloatState() {
		return asFloatState().asMutableState();
	}

	@Override
	public DoubleState asMutableDoubleState() {
		return asDoubleState().asMutableState();
	}

	@Override
	public ReadOnlyBooleanState isLessThan(int value) {
		return Reflect.createBooleanReflection(() -> doubleValue() < value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(long value) {
		return Reflect.createBooleanReflection(() -> doubleValue() < value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(float value) {
		return Reflect.createBooleanReflection(() -> doubleValue() < value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(double value) {
		return Reflect.createBooleanReflection(() -> doubleValue() < value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(ObservableValue<? extends Number> value) {
		return Reflect.createBooleanReflection(() -> doubleValue() < value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(int value) {
		return Reflect.createBooleanReflection(() -> doubleValue() <= value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(long value) {
		return Reflect.createBooleanReflection(() -> doubleValue() <= value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(float value) {
		return Reflect.createBooleanReflection(() -> doubleValue() <= value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(double value) {
		return Reflect.createBooleanReflection(() -> doubleValue() <= value, this);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(ObservableValue<? extends Number> value) {
		return Reflect.createBooleanReflection(() -> doubleValue() <= value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(int value) {
		return Reflect.createBooleanReflection(() -> doubleValue() > value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(long value) {
		return Reflect.createBooleanReflection(() -> doubleValue() > value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(float value) {
		return Reflect.createBooleanReflection(() -> doubleValue() > value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(double value) {
		return Reflect.createBooleanReflection(() -> doubleValue() > value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(ObservableValue<? extends Number> value) {
		return Reflect.createBooleanReflection(() -> doubleValue() > value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(int value) {
		return Reflect.createBooleanReflection(() -> doubleValue() >= value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(long value) {
		return Reflect.createBooleanReflection(() -> doubleValue() >= value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(ObservableValue<? extends Number> value) {
		return Reflect.createBooleanReflection(() -> doubleValue() >= value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(double value) {
		return Reflect.createBooleanReflection(() -> doubleValue() >= value, this);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(float value) {
		return Reflect.createBooleanReflection(() -> doubleValue() >= value, this);
	}

	@Override
	public ReadOnlyBooleanState isBetween(int min, int max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue >= min && thisValue <= max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetween(long min, long max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue >= min && thisValue <= max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetween(float min, float max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue >= min && thisValue <= max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetween(double min, double max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue >= min && thisValue <= max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetween(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue >= min.get().doubleValue() && thisValue <= max.get().doubleValue();
		}, this, min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(int min, int max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue > min && thisValue < max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(long min, long max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue > min && thisValue < max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(float min, float max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue > min && thisValue < max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(double min, double max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue > min && thisValue < max;
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		return Reflect.createBooleanReflection(() -> {
			final double thisValue = doubleValue();
			return thisValue > min.get().doubleValue() && thisValue < max.get().doubleValue();
		}, this, min, max);
	}

	@Override
	public ReadOnlyBooleanState isZero() {
		return Reflect.createBooleanReflection(() -> doubleValue() == 0, this);
	}

	@Override
	public ReadOnlyBooleanState isNotZero() {
		return Reflect.createBooleanReflection(() -> doubleValue() != 0, this);
	}

	@Override
	public ReadOnlyBooleanState isPositive() {
		return Reflect.createBooleanReflection(() -> doubleValue() >= 0, this);
	}

	@Override
	public ReadOnlyBooleanState isNegative() {
		return Reflect.createBooleanReflection(() -> doubleValue() < 0, this);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> add(ObservableValue<? extends Number> value) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, value);
		return Reflect.createNumberReflection(commonType, () -> doubleValue() + value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> subtract(ObservableValue<? extends Number> value) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, value);
		return Reflect.createNumberReflection(commonType, () -> doubleValue() - value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> multiply(ObservableValue<? extends Number> value) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, value);
		return Reflect.createNumberReflection(commonType, () -> doubleValue() * value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> min(ObservableValue<? extends Number> value) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, value);
		return Reflect.createNumberReflection(commonType, () -> Math.min(doubleValue(), value.get().doubleValue()), this, value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> max(ObservableValue<? extends Number> value) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, value);
		return Reflect.createNumberReflection(commonType, () -> Math.max(doubleValue(), value.get().doubleValue()), this, value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> clamp(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		@SuppressWarnings("unchecked")
		final Class<? extends Number> commonType = NumberState.findCommonNumberType(this, min, max);
		return Reflect.createNumberReflection(commonType, () -> Math.clamp(doubleValue(), min.get().doubleValue(), max.get().doubleValue()), this, min, max);
	}

	@Override
	public ReadOnlyDoubleState divide(int value) {
		return divide((double) value);
	}

	@Override
	public ReadOnlyDoubleState divide(long value) {
		return divide((double) value);
	}

	@Override
	public ReadOnlyDoubleState divide(float value) {
		return divide((double) value);
	}

	@Override
	public ReadOnlyDoubleState divide(double value) {
		return Reflect.createDoubleReflection(() -> doubleValue() / value, this);
	}

	@Override
	public ReadOnlyDoubleState divide(ObservableValue<? extends Number> value) {
		return Reflect.createDoubleReflection(() -> doubleValue() / value.get().doubleValue(), this, value);
	}

	@Override
	public ReadOnlyIntegerState round() {
		return Reflect.createIntegerReflection(() -> Math.round(floatValue()), this);
	}

	@Override
	public ReadOnlyIntegerState roundUp() {
		return Reflect.createIntegerReflection(() -> {
			final double thisValue = doubleValue();
			return (int) (thisValue < 0 ? Math.floor(thisValue) : Math.ceil(thisValue));
		}, this);
	}

	@Override
	public ReadOnlyIntegerState roundDown() {
		return Reflect.createIntegerReflection(() -> {
			final double thisValue = doubleValue();
			return (int) (thisValue < 0 ? Math.ceil(thisValue) : Math.floor(thisValue));
		}, this);
	}

	@Override
	public ReadOnlyIntegerState floor() {
		return Reflect.createIntegerReflection(() -> (int) Math.floor(doubleValue()), this);
	}

	@Override
	public ReadOnlyIntegerState ceiling() {
		return Reflect.createIntegerReflection(() -> (int) Math.ceil(doubleValue()), this);
	}

}
