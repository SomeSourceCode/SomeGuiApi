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
 * A read-only boolean state that always returns a constant value.
 *
 * @since 3.0.0
 */
public class BooleanConstant extends ReadOnlyBooleanStateBase {

	private final boolean value;

	/**
	 * Constructs a new BooleanConstant with the specified value.
	 *
	 * @param value the constant boolean value to be returned by this state
	 */
	public BooleanConstant(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean get() {
		return value;
	}

	@Override
	public Subscription observe(Observer<? super Boolean> observer) {
		// no-op
		return () -> {};
	}

	@Override
	public void removeObserver(Observer<? super Boolean> observer) {
		// no-op
	}

}
