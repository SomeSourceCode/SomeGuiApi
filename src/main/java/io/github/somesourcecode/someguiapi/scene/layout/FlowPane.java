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
import io.github.somesourcecode.someguiapi.scene.util.Orientation;
import io.github.somesourcecode.someguiapi.state.IntegerState;
import io.github.somesourcecode.someguiapi.state.ObjectState;
import io.github.somesourcecode.someguiapi.state.SimpleIntegerState;
import io.github.somesourcecode.someguiapi.state.SimpleObjectState;

/**
 * A layout pane that arranges its children in a flow, wrapping at the pane's bounds.
 * <p>
 * A horizontal FlowPane (the default) will lay out nodes in rows, wrapping at the width
 * of the pane. A vertical FlowPane will lay out nodes in columns, wrapping at the height
 * of the pane. If the FlowPane has padding set, the nodes will be laid out within
 * those insets.
 *
 * @since 1.0.0
 */
public class FlowPane extends Pane {

	private ObjectState<Orientation> orientationState;

	private IntegerState hGapState;
	private IntegerState vGapState;

	/**
	 * Constructs a new FlowPane with horizontal orientation.
	 *
	 * @since 1.0.0
	 */
	public FlowPane() {
		super();
	}

	/**
	 * Constructs a new FlowPane with the given orientation.
	 *
	 * @param orientation the orientation of the flow pane
	 * @since 1.0.0
	 */
	public FlowPane(Orientation orientation) {
		super();
		setOrientation(orientation);
	}

	/**
	 * Constructs a new FlowPane with the given horizontal and vertical gaps.
	 *
	 * @param hGap the horizontal gap between nodes
	 * @param vGap the vertical gap between nodes
	 * @since 1.0.0
	 */
	public FlowPane(int hGap, int vGap) {
		super();
		setHGap(hGap);
		setVGap(vGap);
	}

	/**
	 * Constructs a new FlowPane with the given orientation, horizontal and vertical gaps.
	 *
	 * @param orientation the orientation of the flow pane
	 * @param hGap the horizontal gap between nodes
	 * @param vGap the vertical gap between nodes
	 * @since 1.0.0
	 */
	public FlowPane(Orientation orientation, int hGap, int vGap) {
		super();
		setOrientation(orientation);
		setHGap(hGap);
		setVGap(vGap);
	}

	/**
	 * Constructs a new FlowPane with the given children.
	 *
	 * @param children the children of the flow pane
	 * @since 1.0.0
	 */
	public FlowPane(Node... children) {
		super(children);
	}

	/**
	 * Constructs a new FlowPane with the given orientation and children.
	 *
	 * @param orientation the orientation of the flow pane
	 * @param children the children of the flow pane
	 * @since 1.0.0
	 */
	public FlowPane(Orientation orientation, Node... children) {
		super(children);
		setOrientation(orientation);
	}

	/**
	 * Constructs a new FlowPane with the given horizontal and vertical gaps and children.
	 *
	 * @param hGap the horizontal gap between nodes
	 * @param vGap the vertical gap between nodes
	 * @param children the children of the flow pane
	 * @since 1.0.0
	 */
	public FlowPane(int hGap, int vGap, Node... children) {
		super(children);
		setHGap(hGap);
		setVGap(vGap);
	}

	/**
	 * Constructs a new FlowPane with the given orientation, horizontal and vertical gaps, and children.
	 *
	 * @param orientation the orientation of the flow pane
	 * @param hGap the horizontal gap between nodes
	 * @param vGap the vertical gap between nodes
	 * @param children the children of the flow pane
	 * @since 1.0.0
	 */
	public FlowPane(Orientation orientation, int hGap, int vGap, Node... children) {
		super(children);
		setOrientation(orientation);
		setHGap(hGap);
		setVGap(vGap);
	}

	/**
	 * Returns the state holding the orientation of the flow pane.
	 *
	 * @return the state holding the orientation of the flow pane
	 * @since 3.0.0
	 */
	public ObjectState<Orientation> orientationState() {
		if (orientationState == null) {
			orientationState = new SimpleObjectState<>(Orientation.HORIZONTAL);
			orientationState.observe(this::requestLayout);
		}
		return orientationState;
	}

	/**
	 * Returns the orientation of the flow pane.
	 *
	 * @return the orientation of the flow pane
	 * @since 1.0.0
	 */
	public Orientation getOrientation() {
		if (orientationState == null) {
			return Orientation.HORIZONTAL;
		}
		final Orientation orientation = orientationState.get();
		return orientation == null ? Orientation.HORIZONTAL : orientation;
	}

	/**
	 * Sets the orientation of the flow pane.
	 *
	 * @param orientation the orientation of the flow pane
	 * @since 1.0.0
	 */
	public void setOrientation(Orientation orientation) {
		orientationState().set(orientation);
	}

	/**
	 * Returns the state holding the amount of space between each node in a horizontal FlowPane
	 * or the space between each column in a vertical FlowPane.
	 *
	 * @return the state holding the horizontal gap between nodes
	 * @since 3.0.0
	 */
	public IntegerState hGapState() {
		if (hGapState == null) {
			hGapState = new SimpleIntegerState(0);
			hGapState.observe(this::requestLayout);
		}
		return hGapState;
	}

	/**
	 * Returns the amount of space between each node in a horizontal FlowPane
	 * or the space between each column in a vertical FlowPane.
	 *
	 * @return the horizontal gap between nodes
	 * @since 1.0.0
	 */
	public int getHGap() {
		return hGapState == null ? 0 : hGapState.get();
	}

	/**
	 * Sets the amount of space between each node in a horizontal FlowPane
	 * or the space between each column in a vertical FlowPane.
	 *
	 * @param hGap the horizontal gap between nodes
	 * @since 1.0.0
	 */
	public void setHGap(int hGap) {
		hGapState().set(hGap);
	}

	/**
	 * Returns the state holding the amount of space between each node in a vertical FlowPane
	 * or the space between each row in a horizontal FlowPane.
	 *
	 * @return the state holding the vertical gap between nodes
	 * @since 3.0.0
	 */
	public IntegerState vGapState() {
		if (vGapState == null) {
			vGapState = new SimpleIntegerState(0);
			vGapState.observe(this::requestLayout);
		}
		return vGapState;
	}

	/**
	 * Returns the amount of space between each node in a vertical FlowPane
	 * or the space between each row in a horizontal FlowPane.
	 *
	 * @return the vertical gap between nodes
	 * @since 1.0.0
	 */
	public int getVGap() {
		return vGapState == null ? 0 : vGapState.get();
	}

	/**
	 * Sets the amount of space between each node in a vertical FlowPane
	 * or the space between each row in a horizontal FlowPane.
	 *
	 * @param vGap the vertical gap between nodes
	 * @since 1.0.0
	 */
	public void setVGap(int vGap) {
		vGapState().set(vGap);
	}

	@Override
	protected void layoutChildren() {
		final int topPadding = getPadding().getTop();
		final int leftPadding = getPadding().getLeft();
		final int rightPadding = getPadding().getRight();
		final int bottomPadding = getPadding().getBottom();

		final int layoutWidth = getWidth() - leftPadding - rightPadding;
		final int layoutHeight = getHeight() - topPadding - bottomPadding;

		final int hGap = getHGap();
		final int vGap = getVGap();

		final Orientation orientation = getOrientation();
		final int maxLength = orientation == Orientation.HORIZONTAL ? layoutWidth : layoutHeight;

		int length = 0;
		int width = 0;

		int x = leftPadding;
		int y = topPadding;

		for (Node child : getChildren()) {
			final int childLength = orientation == Orientation.HORIZONTAL ? child.getWidth() : child.getHeight();
			final int childWidth = orientation == Orientation.HORIZONTAL ? child.getHeight() : child.getWidth();
			if (length + childLength > maxLength && length > 0) {
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
