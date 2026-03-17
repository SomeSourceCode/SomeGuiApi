/*
 * Copyright 2026, SomeSourceCode - MIT License
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
 * Represents a subscription to an observable state.
 * <p>
 * This interface provides a mechanism to manually detach an observer from a state
 * when it is no longer needed. While detaching is not strictly required if the observer
 * and the state share the same lifecycle (e.g., a node observing its own state), it is
 * crucial when a short-lived component observes a long-lived or global state, in order
 * to prevent memory leaks.
 *
 * @since 3.0.0
 */
@FunctionalInterface
public interface Subscription {

	/**
	 * Cancels the subscription.
	 * <p>
	 * After this method is called, the associated observer will no longer receive
	 * updates from the state it was subscribed to.
	 *
	 * @since 3.0.0
	 */
	void cancel();

}
