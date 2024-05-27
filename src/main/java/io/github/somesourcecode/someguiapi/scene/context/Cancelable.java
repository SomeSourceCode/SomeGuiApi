/*
 * Copyright 2024, SomeSourceCode - MIT License
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

package io.github.somesourcecode.someguiapi.scene.context;

/**
 * A cancelable event.
 *
 * @since 2.1.0
 */
public interface Cancelable {

	/**
	 * Returns the cancellation state of the event.
	 *
	 * @return whether the event is canceled
	 * @since 2.1.0
	 */
	boolean isCanceled();

	/**
	 * Sets the cancellation state of the event.
	 *
	 * @param canceled the cancellation state
	 * @since 2.1.0
	 */
	void setCanceled(boolean canceled);

	/**
	 * Cancels the event. This is a shorthand for {@code setCanceled(true)}.
	 *
	 * @see #setCanceled(boolean)
	 * @since 2.1.0
	 */
	default void cancel() {
		setCanceled(true);
	}

}