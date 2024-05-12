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

package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;

/**
 * A layout pane that arranges its children in a vertical column.
 *
 * @since 1.0.0
 */
public class VBox extends Pane {

	private int spacing;

	/**
	 * Constructs a new VBox with a spacing of 0.
	 *
	 * @since 1.0.0
	 */
	public VBox() {
		super();
	}

	/**
	 * Constructs a new VBox with the given spacing.
	 *
	 * @param spacing the vertical spacing between children
	 * @since 1.0.0
	 */
	public VBox(int spacing) {
		super();
		this.spacing = spacing;
	}

	/**
	 * Constructs a new VBox with the given children.
	 *
	 * @param children the children of the VBox
	 * @since 1.0.0
	 */
	public VBox(Node... children) {
		super(children);
	}

	/**
	 * Constructs a new VBox with the given spacing and children.
	 *
	 * @param spacing the vertical spacing between children
	 * @param children the children of the VBox
	 * @since 1.0.0
	 */
	public VBox(int spacing, Node... children) {
		super(children);
		this.spacing = spacing;
	}

	/**
	 * Returns the amount of vertical space between each child node.
	 *
	 * @return the vertical spacing between children
	 * @since 1.0.0
	 */
	public int getSpacing() {
		return spacing;
	}

	/**
	 * Sets the amount of vertical space between each child node.
	 *
	 * @param spacing the vertical spacing between children
	 * @since 1.0.0
	 */
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	@Override
	protected void layoutChildren() {
		final int leftPadding = getPadding().getLeft();

		int y = getPadding().getTop();
		for (Node child : getChildren()) {
			child.relocate(leftPadding + child.getTranslateX(), y + child.getTranslateY());
			y += child.getHeight() + spacing;
		}
	}

}
