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

package io.github.somesourcecode.someguiapi.util;

/**
 * Represents the distance from the edges of a container to its content.
 * @since 1.0.0
 */
public class Insets {

	/**
	 * Empty insets. An {@code Insets} instance with all insets set to 0.
	 * @since 1.0.0
	 */
	public static final Insets EMPTY = new Insets(0, 0, 0, 0);

	private final int top;
	private final int right;
	private final int bottom;
	private final int left;

	/**
	 * Constructs a new Insets instance with four different offsets.
	 * @param top the top inset
	 * @param right the right inset
	 * @param bottom the bottom inset
	 * @param left the left inset
	 * @since 1.0.0
	 */
	public Insets(int top, int right, int bottom, int left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	/**
	 * Constructs a new Insets instance with same value for top and bottom, and for right and left.
	 * @param topBottom the top and bottom insets
	 * @param rightLeft the right and left insets
	 * @since 1.0.0
	 */
	public Insets(int topBottom, int rightLeft) {
		this.top = topBottom;
		this.right = rightLeft;
		this.bottom = topBottom;
		this.left = rightLeft;
	}

	/**
	 * Constructs a new Insets instance with same value for all four offsets.
	 * @param topRightBottomLeft the top, right, bottom, and left insets
	 * @since 1.0.0
	 */
	public Insets(int topRightBottomLeft) {
		this.top = topRightBottomLeft;
		this.right = topRightBottomLeft;
		this.bottom = topRightBottomLeft;
		this.left = topRightBottomLeft;
	}

	/**
	 * Returns the top inset.
	 * @return the top inset
	 * @since 1.0.0
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Returns the right inset.
	 * @return the right inset
	 * @since 1.0.0
	 */
	public int getRight() {
		return right;
	}

	/**
	 * Returns the bottom inset.
	 * @return the bottom inset
	 * @since 1.0.0
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * Returns the left inset.
	 * @return the left inset
	 * @since 1.0.0
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return true if this object is the same as the obj argument; false otherwise.
	 * @since 1.0.0
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Insets other)) {
			return false;
		}
		return top == other.top && right == other.right && bottom == other.bottom && left == other.left;
	}

}
