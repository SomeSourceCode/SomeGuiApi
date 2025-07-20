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
 * A proxy for {@link ReadOnlyNumberState} that forwards all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyNumberStateProxy<T extends Number> extends ReadOnlyStateProxy<T> implements ReadOnlyNumberState<T> {

	private final ReadOnlyNumberState<T> delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyNumberState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyNumberStateProxy(ReadOnlyNumberState<T> delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public int intValue() {
		return delegate.intValue();
	}

	@Override
	public double doubleValue() {
		return delegate.doubleValue();
	}

	@Override
	public long longValue() {
		return delegate.longValue();
	}

	@Override
	public float floatValue() {
		return delegate.floatValue();
	}

	@Override
	public ReadOnlyIntegerState asIntegerState() {
		return delegate.asIntegerState();
	}

	@Override
	public ReadOnlyLongState asLongState() {
		return delegate.asLongState();
	}

	@Override
	public ReadOnlyFloatState asFloatState() {
		return delegate.asFloatState();
	}

	@Override
	public ReadOnlyDoubleState asDoubleState() {
		return delegate.asDoubleState();
	}

	@Override
	public IntegerState asMutableIntegerState() {
		return delegate.asMutableIntegerState();
	}

	@Override
	public LongState asMutableLongState() {
		return delegate.asMutableLongState();
	}

	@Override
	public FloatState asMutableFloatState() {
		return delegate.asMutableFloatState();
	}

	@Override
	public DoubleState asMutableDoubleState() {
		return delegate.asMutableDoubleState();
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(int value, double epsilon) {
		return delegate.isEqualTo(value, epsilon);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(long value, double epsilon) {
		return delegate.isEqualTo(value, epsilon);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(float value, double epsilon) {
		return delegate.isEqualTo(value, epsilon);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(double value, double epsilon) {
		return delegate.isEqualTo(value, epsilon);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(ObservableValue<? extends Number> value, double epsilon) {
		return delegate.isEqualTo(value, epsilon);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(int value) {
		return delegate.isLessThan(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(long value) {
		return delegate.isLessThan(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(float value) {
		return delegate.isLessThan(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(double value) {
		return delegate.isLessThan(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThan(ObservableValue<? extends Number> value) {
		return delegate.isLessThan(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(int value) {
		return delegate.isLessThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(long value) {
		return delegate.isLessThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(float value) {
		return delegate.isLessThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(double value) {
		return delegate.isLessThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isLessThanOrEqualTo(ObservableValue<? extends Number> value) {
		return delegate.isLessThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(int value) {
		return delegate.isGreaterThan(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(long value) {
		return delegate.isGreaterThan(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(float value) {
		return delegate.isGreaterThan(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(double value) {
		return delegate.isGreaterThan(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThan(ObservableValue<? extends Number> value) {
		return delegate.isGreaterThan(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(int value) {
		return delegate.isGreaterThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(long value) {
		return delegate.isGreaterThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(float value) {
		return delegate.isGreaterThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(double value) {
		return delegate.isGreaterThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isGreaterThanOrEqualTo(ObservableValue<? extends Number> value) {
		return delegate.isGreaterThanOrEqualTo(value);
	}

	@Override
	public ReadOnlyBooleanState isBetween(int min, int max) {
		return delegate.isBetween(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetween(long min, long max) {
		return delegate.isBetween(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetween(float min, float max) {
		return delegate.isBetween(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetween(double min, double max) {
		return delegate.isBetween(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetween(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		return delegate.isBetween(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(int min, int max) {
		return delegate.isBetweenExclusive(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(long min, long max) {
		return delegate.isBetweenExclusive(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(float min, float max) {
		return delegate.isBetweenExclusive(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(double min, double max) {
		return delegate.isBetweenExclusive(min, max);
	}

	@Override
	public ReadOnlyBooleanState isBetweenExclusive(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		return delegate.isBetweenExclusive(min, max);
	}

	@Override
	public ReadOnlyBooleanState isZero() {
		return delegate.isZero();
	}

	@Override
	public ReadOnlyBooleanState isNotZero() {
		return delegate.isNotZero();
	}

	@Override
	public ReadOnlyBooleanState isPositive() {
		return delegate.isPositive();
	}

	@Override
	public ReadOnlyBooleanState isNegative() {
		return delegate.isNegative();
	}

	@Override
	public ReadOnlyNumberState<? extends Number> add(ObservableValue<? extends Number> value) {
		return delegate.add(value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> subtract(ObservableValue<? extends Number> value) {
		return delegate.subtract(value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> multiply(ObservableValue<? extends Number> value) {
		return delegate.multiply(value);
	}

	@Override
	public ReadOnlyDoubleState divide(int value) {
		return delegate.divide(value);
	}

	@Override
	public ReadOnlyDoubleState divide(long value) {
		return delegate.divide(value);
	}

	@Override
	public ReadOnlyDoubleState divide(float value) {
		return delegate.divide(value);
	}

	@Override
	public ReadOnlyDoubleState divide(double value) {
		return delegate.divide(value);
	}

	@Override
	public ReadOnlyDoubleState divide(ObservableValue<? extends Number> value) {
		return delegate.divide(value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> min(ObservableValue<? extends Number> value) {
		return delegate.min(value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> max(ObservableValue<? extends Number> value) {
		return delegate.max(value);
	}

	@Override
	public ReadOnlyNumberState<? extends Number> clamp(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max) {
		return delegate.clamp(min, max);
	}

}
