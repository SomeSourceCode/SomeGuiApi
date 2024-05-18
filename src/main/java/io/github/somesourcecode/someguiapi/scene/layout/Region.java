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

package io.github.somesourcecode.someguiapi.scene.layout;

import io.github.somesourcecode.someguiapi.scene.util.Insets;
import io.github.somesourcecode.someguiapi.scene.Parent;

/**
 * The Base class for parent nodes whose size can be freely set.
 * <p>
 * This class handles the size of the node and its children, as well as padding.
 * To create layout panes, you probably want to extend {@link Pane} instead.
 *
 * @since 1.0.0
 */
public class Region extends Parent {

	private int width;
	private int height;

	private Insets padding;

	/**
	 * Constructs a new region with a width and height of 0.
	 *
	 * @since 1.0.0
	 */
	public Region() {
		super();
	}

	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this region.
	 *
	 * @param width the width of this region
	 * @since 1.0.0
	 */
	public void setWidth(int width) {
		if (this.width == width) {
			return;
		}
		this.width = width;
		requestLayout();
	}

	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this region.
	 *
	 * @param height the height of this region
	 * @since 1.0.0
	 */
	public void setHeight(int height) {
		if (this.height == height) {
			return;
		}
		this.height = height;
		requestLayout();
	}

	/**
	 * Resizes this region to the specified width and height.
	 * <p>
	 * This is equivalent to calling {@link #setWidth(int)} and {@link #setHeight(int)}.
	 *
	 * @param width the new width of this region
	 * @param height the new height of this region
	 * @since 1.0.0
	 */
	public void resize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Resizes and relocates this region to the specified layoutX, layoutY, width, and height.
	 * <p>
	 * This is equivalent to calling {@link #relocate(int, int)} and {@link #resize(int, int)}.
	 *
	 * @param layoutX the new layoutX of this region
	 * @param layoutY the new layoutY of this region
	 * @param width the new width of this region
	 * @param height the new height of this region
	 * @since 1.0.0
	 */
	public void resizeRelocate(int layoutX, int layoutY, int width, int height) {
		relocate(layoutX, layoutY);
		resize(width, height);
	}

	/**
	 * Returns the padding of this region.
	 *
	 * @return the padding of this region
	 * @since 1.0.0
	 */
	public Insets getPadding() {
		return padding == null ? Insets.EMPTY : padding;
	}

	/**
	 * Sets the padding of this region.
	 *
	 * @param padding the padding of this region
	 * @since 1.0.0
	 */
	public void setPadding(Insets padding) {
		if ((this.padding == null && padding == null) || (this.padding != null && this.padding.equals(padding))) {
			return;
		}
		this.padding = padding;
		requestLayout();
	}

}
