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

package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.collections.ObservableList;
import io.github.somesourcecode.someguiapi.collections.ObservableListBase;
import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * The base class for all nodes that can have children.
 * <p>
 * This class handles all hierarchy-related operations, such as adding and removing child nodes,
 * layout and rendering.
 */
public abstract class Parent extends Node {

	private final ObservableList<Node> children = new ObservableListBase<>(new ArrayList<>());

	private boolean needsLayout = true;

	private Background background;

	/**
	 * Constructs a new parent node.
	 */
	protected Parent() {
		children.addListener(change -> {
			if (change.wasAdded()) {
				for (Node child : change.getAddedSubList()) {
					Parent oldParent = child.getParent();
					if (oldParent == this) {
						continue;
					}
					if (oldParent != null) {
						oldParent.getChildren().remove(child);
					}
					child.setParent(this);
				}
			}
			if (change.wasRemoved()) {
				for (Node child : change.getRemovedSubList()) {
					if (child.getParent() == this) {
						child.setParent(null);
					}
				}
			}
			requestLayout();
		});
	}

	/**
	 * Returns the list of children of this parent.
	 * @return the list of children
	 */
	protected ObservableList<Node> getChildren() {
		return children;
	}

	/**
	 * Returns whether this parent is the root of a scene.
	 * @return whether this parent is the root of a scene
	 */
	public boolean isSceneRoot() {
		return getParent() == null && getScene() != null;
	}

	/**
	 * Requests a layout update for this parent.
	 * Layout will be applied on the next layout pass.
	 */
	public void requestLayout() {
		needsLayout = true;
		if (isSceneRoot()) {
			getScene().getGui().setDirtyFlag(DirtyFlag.GUI_CONTENT);
		}
		requestParentLayout();
	}

	/**
	 * Returns whether this parent needs a layout update.
	 * @return whether this parent needs a layout update
	 * @see #requestLayout()
	 */
	public boolean needsLayout() {
		return needsLayout;
	}

	private boolean performingLayout = false;

	/**
	 * Recursively applies the layout to all children and itself.
	 */
	public final void layout() {
		if (!needsLayout || performingLayout) {
			return;
		}
		needsLayout = false;

		performingLayout = true;
		layoutChildren();

		for (Node child : children) {
			if (child instanceof Parent parent) {
				parent.layout();
			}
		}
		performingLayout = false;
	}

	protected void layoutChildren() {

	}

	/**
	 * Returns the background of this parent.
	 * @return the background
	 */
	public Background getBackground() {
		return background;
	}

	/**
	 * Sets the background of this parent. It will be rendered behind all children.
	 * @param background the new background
	 */
	public void setBackground(Background background) {
		this.background = background;
	}

	@Override
	public ItemStack renderPixelAt(int x, int y, RenderContext renderContext) {
		final boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();

		if (children.isEmpty()) {
			return null;
		}

		if (!isInBounds && isClipping()) {
			return null;
		}

		for (int i = children.size() - 1; i >= 0; i--) {
			Node child = children.get(i);
			if (!child.isVisible()) {
				continue;
			}

			final int childX = child.getLayoutX();
			final int childY = child.getLayoutY();

			final int localX = x - childX;
			final int localY = y - childY;

			final ItemStack pixel = child.renderPixelAt(localX, localY, renderContext);
			if (pixel != null) {
				return pixel;
			}
		}

		if (isInBounds && background != null) {
			return background.backgroundAt(renderContext, x, y);
		}

		return null;
	}

	@Override
	public Node nodeAt(int x, int y) {
		final boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();

		if (!isInBounds && isClipping()) {
			return null;
		}

		if (children.isEmpty()) {
			return isInBounds ? this : null;
		}

		for (int i = children.size() - 1; i >= 0; i--) {
			Node child = children.get(i);
			final int childX = child.getLayoutX();
			final int childY = child.getLayoutY();

			final int localX = x - childX;
			final int localY = y - childY;

			final Node node = child.nodeAt(localX, localY);
			if (node != null) {
				return node;
			}
		}

		return this;
	}

}
