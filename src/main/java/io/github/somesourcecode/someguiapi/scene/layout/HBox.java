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

import io.github.somesourcecode.someguiapi.scene.Node;
import io.github.somesourcecode.someguiapi.state.IntegerState;
import io.github.somesourcecode.someguiapi.state.SimpleIntegerState;

/**
 * A layout pane that arranges its children in a horizontal row.
 *
 * @since 1.0.0
 */
public class HBox extends Pane {

	private IntegerState spacingState;

	/**
	 * Constructs a new HBox with a spacing of 0.
	 *
	 * @since 1.0.0
	 */
	public HBox() {
		super();
	}

	/**
	 * Constructs a new HBox with the given spacing.
	 *
	 * @param spacing the horizontal spacing between children
	 * @since 1.0.0
	 */
	public HBox(int spacing) {
		super();
		setSpacing(spacing);
	}

	/**
	 * Constructs a new HBox with the given children.
	 *
	 * @param children the children of the HBox
	 * @since 1.0.0
	 */
	public HBox(Node... children) {
		super(children);
	}

	/**
	 * Constructs a new HBox with the given spacing and children.
	 *
	 * @param spacing the horizontal spacing between children
	 * @param children the children of the HBox
	 * @since 1.0.0
	 */
	public HBox(int spacing, Node... children) {
		super(children);
		setSpacing(spacing);
	}

	/**
	 * Returns the state holding the amount of horizontal space between each child node.
	 *
	 * @return the state holding the amount of horizontal space between each child node.
	 * @since 3.0.0
	 */
	public IntegerState spacingState() {
		if (spacingState == null) {
			spacingState = new SimpleIntegerState(0);
			spacingState.observe(this::requestLayout);
		}
		return spacingState;
	}

	/**
	 * Returns the amount of horizontal space between each child node.
	 *
	 * @return the horizontal spacing between children
	 * @since 1.0.0
	 */
	public int getSpacing() {
		return spacingState == null ? 0 : spacingState.get();
	}

	/**
	 * Sets the amount of horizontal space between each child node.
	 *
	 * @param spacing the horizontal spacing between children
	 * @since 1.0.0
	 */
	public void setSpacing(int spacing) {
		spacingState().set(spacing);
	}

	@Override
	protected void layoutChildren() {
		final int topPadding = getPadding().getTop();
		final int spacing = getSpacing();

		int x = getPadding().getLeft();
		for (Node child : getChildren()) {
			child.relocate(x + child.getTranslateX(), topPadding + child.getTranslateY());
			x += child.getWidth() + spacing;
		}
	}

}
