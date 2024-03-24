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
import io.github.somesourcecode.someguiapi.util.Orientation;

/**
 * A layout pane that arranges its children in a flow, wrapping at the pane's bounds.
 * <p>
 * A horizontal FlowPane (the default) will lay out nodes in rows, wrapping at the width
 * of the pane. A vertical FlowPane will lay out nodes in columns, wrapping at the height
 * of the pane. If the FlowPane has padding set, the nodes will be laid out within
 * those insets.
 */
public class FlowPane extends Pane {

	private Orientation orientation;

	private int hGap;
	private int vGap;

	public FlowPane() {
		this.orientation = Orientation.HORIZONTAL;
	}

	/**
	 * Returns the orientation of the flow pane.
	 * @return the orientation of the flow pane
	 */
	public Orientation getOrientation() {
		return orientation == null ? Orientation.HORIZONTAL : orientation;
	}

	/**
	 * Sets the orientation of the flow pane.
	 * @param orientation the orientation of the flow pane
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Returns the amount of space between each node in a horizontal FlowPane
	 * or the space between each column in a vertical FlowPane.
	 * @return the horizontal gap between nodes
	 */
	public int getHGap() {
		return hGap;
	}

	/**
	 * Sets the amount of space between each node in a horizontal FlowPane
	 * or the space between each column in a vertical FlowPane.
	 * @param hGap the horizontal gap between nodes
	 */
	public void setHGap(int hGap) {
		this.hGap = hGap;
	}

	/**
	 * Returns the amount of space between each node in a vertical FlowPane
	 * or the space between each row in a horizontal FlowPane.
	 * @return the vertical gap between nodes
	 */
	public int getVGap() {
		return vGap;
	}

	/**
	 * Sets the amount of space between each node in a vertical FlowPane
	 * or the space between each row in a horizontal FlowPane.
	 * @param vGap the vertical gap between nodes
	 */
	public void setVGap(int vGap) {
		this.vGap = vGap;
	}

	@Override
	protected void layoutChildren() {
		final int topPadding = getPadding().getTop();
		final int leftPadding = getPadding().getLeft();
		final int rightPadding = getPadding().getRight();
		final int bottomPadding = getPadding().getBottom();

		final int layoutWidth = getWidth() - leftPadding - rightPadding;
		final int layoutHeight = getHeight() - topPadding - bottomPadding;

		final Orientation orientation = getOrientation();
		final int maxLength = orientation == Orientation.HORIZONTAL ? layoutWidth : layoutHeight;

		int length = 0;
		int width = 0;

		int x = leftPadding;
		int y = topPadding;

		for (Node child : getChildren()) {
			final int childLength = orientation == Orientation.HORIZONTAL ? child.getWidth() : child.getHeight();
			final int childWidth = orientation == Orientation.HORIZONTAL ? child.getHeight() : child.getWidth();
			if ((orientation == Orientation.HORIZONTAL ? x : y) + childLength > maxLength && length > 0) {
				if (orientation == Orientation.HORIZONTAL) {
					x = leftPadding;
					y += width + vGap;
				} else {
					y = topPadding;
					x += width + hGap;
				}
				length = 0;
			}

			child.relocate(x + child.getTranslateX(), y + child.getTranslateY());

			width = Math.max(width, childWidth);
			length += childLength;

			if (orientation == Orientation.HORIZONTAL) {
				x += childLength + hGap;
			} else {
				y += childLength + vGap;
			}
		}
	}

}
