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

import java.util.Objects;
import java.util.function.Function;

/**
 * The base class for all states.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public abstract class ReadOnlyStateBase<T> implements ReadOnlyState<T> {

	@Override
	public ReadOnlyBooleanState isEqualTo(T value) {
		return Reflect.createBooleanReflection(() -> Objects.equals(this.get(), value), this);
	}

	@Override
	public ReadOnlyBooleanState isEqualTo(ObservableValue<T> value) {
		if (value == null) {
			throw new NullPointerException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> Objects.equals(this.get(), value.get()), this, value);
	}

	@Override
	public ReadOnlyBooleanState isNotEqualTo(T value) {
		return Reflect.createBooleanReflection(() -> !Objects.equals(this.get(), value), this);
	}

	@Override
	public ReadOnlyBooleanState isNotEqualTo(ObservableValue<T> value) {
		if (value == null) {
			throw new NullPointerException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> !Objects.equals(this.get(), value.get()), this, value);
	}

	@Override
	public ReadOnlyStringState asString() {
		return Reflect.createStringReflection(() -> String.valueOf(get()), this);
	}

	@Override
	public ReadOnlyBooleanState mapToBoolean(Function<T, Boolean> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createBooleanReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public ReadOnlyIntegerState mapToInteger(Function<T, Integer> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createIntegerReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public ReadOnlyLongState mapToLong(Function<T, Long> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createLongReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public ReadOnlyFloatState mapToFloat(Function<T, Float> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createFloatReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public ReadOnlyDoubleState mapToDouble(Function<T, Double> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createDoubleReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public ReadOnlyStringState mapToString(Function<T, String> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createStringReflection(() -> mapper.apply(this.get()), this);
	}

	@Override
	public <E> ReadOnlyObjectState<E> mapToObject(Function<T, E> mapper) {
		if (mapper == null) {
			throw new NullPointerException("mapper must be non-null");
		}
		return Reflect.createObjectReflection(() -> mapper.apply(this.get()), this);
	}

}
