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
 * A read-only state of type {@link Float}.
 *
 * @since 3.0.0
 */
public interface ReadOnlyFloatState extends ReadOnlyNumberState<Float> {

	FloatState asMutableState();

	ReadOnlyFloatState add(int value);

	ReadOnlyFloatState add(long value);

	ReadOnlyFloatState add(float value);

	ReadOnlyDoubleState add(double value);

	ReadOnlyFloatState subtract(int value);

	ReadOnlyFloatState subtract(long value);

	ReadOnlyFloatState subtract(float value);

	ReadOnlyDoubleState subtract(double value);

	ReadOnlyFloatState multiply(int value);

	ReadOnlyFloatState multiply(long value);

	ReadOnlyFloatState multiply(float value);

	ReadOnlyDoubleState multiply(double value);

	ReadOnlyFloatState min(int value);

	ReadOnlyFloatState min(long value);

	ReadOnlyFloatState min(float value);

	ReadOnlyDoubleState min(double value);

	ReadOnlyFloatState max(int value);

	ReadOnlyFloatState max(long value);

	ReadOnlyFloatState max(float value);

	ReadOnlyDoubleState max(double value);

	ReadOnlyFloatState clamp(int min, int max);

	ReadOnlyFloatState clamp(long min, long max);

	ReadOnlyFloatState clamp(float min, float max);

	ReadOnlyDoubleState clamp(double min, double max);

	ReadOnlyFloatState negate();

	ReadOnlyFloatState abs();

}
