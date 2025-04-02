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
 * A read-only state of type {@link Long}.
 *
 * @since 3.0.0
 */
public interface ReadOnlyLongState extends ReadOnlyNumberState<Long> {

	ReadOnlyLongState add(int value);

	ReadOnlyLongState add(long value);

	ReadOnlyFloatState add(float value);

	ReadOnlyDoubleState add(double value);

	ReadOnlyLongState subtract(int value);

	ReadOnlyLongState subtract(long value);

	ReadOnlyFloatState subtract(float value);

	ReadOnlyDoubleState subtract(double value);

	ReadOnlyLongState multiply(int value);

	ReadOnlyLongState multiply(long value);

	ReadOnlyFloatState multiply(float value);

	ReadOnlyDoubleState multiply(double value);

	ReadOnlyLongState min(int value);

	ReadOnlyLongState min(long value);

	ReadOnlyFloatState min(float value);

	ReadOnlyDoubleState min(double value);

	ReadOnlyLongState max(int value);

	ReadOnlyLongState max(long value);

	ReadOnlyFloatState max(float value);

	ReadOnlyDoubleState max(double value);

	ReadOnlyLongState clamp(int min, int max);

	ReadOnlyLongState clamp(long min, long max);

	ReadOnlyFloatState clamp(float min, float max);

	ReadOnlyDoubleState clamp(double min, double max);

	ReadOnlyLongState negate();

	ReadOnlyLongState abs();

}
