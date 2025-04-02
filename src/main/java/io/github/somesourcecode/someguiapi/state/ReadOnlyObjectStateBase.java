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

import java.util.function.Function;

/**
 * The base class for all object states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyObjectStateBase<T> extends ReadOnlyStateBase<T> implements ReadOnlyObjectState<T> {

	@Override
	public Class<? super T> getType() {
		return Object.class;
	}

	@Override
	public ObjectState<T> asMutableState() {
		final ObjectState<T> state = new SimpleObjectState<>(get());
		state.reflect(this);
		return state;
	}

	protected ReadOnlyStringState createMethodForwardStringReflection(Function<T, String> method, ObservableValue<?>... dependencies) {
		return Reflect.createStringReflection(() -> {
			final T thisValue = get();
			return thisValue == null ? null : method.apply(thisValue);
		}, dependencies);
	}

	@Override
	public ReadOnlyBooleanState isNull() {
		return Reflect.createBooleanReflection(() -> get() == null);
	}

	@Override
	public ReadOnlyBooleanState isNotNull() {
		return Reflect.createBooleanReflection(() -> get() != null);
	}

	@Override
	public ReadOnlyObjectState<T> orElse(T value) {
		return Reflect.createObjectReflection(() -> {
			final T thisValue = get();
			return thisValue != null ? thisValue : value;
		}, this);
	}

	@Override
	public ReadOnlyObjectState<T> orElse(ObservableValue<T> value) {
		return Reflect.createObjectReflection(() -> {
			final T thisValue = get();
			return thisValue != null ? thisValue : value.get();
		}, this, value);
	}

}
