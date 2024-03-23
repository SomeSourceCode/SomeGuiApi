package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.collections.ObservableList;
import io.github.somesourcecode.someguiapi.collections.ObservableListBase;
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

	private Background background;

	/**
	 * Returns the list of children of this parent.
	 * @return the list of children
	 */
	protected ObservableList<Node> getChildren() {
		return children;
	}

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
		});
	}

	/**
	 * Recursively applies the layout to all children and itself.
	 */
	public final void layout() {
		for (Node child : children) {
			if (child instanceof Parent parent) {
				parent.layout();
			}
		}
		layoutChildren();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack pixelAt(int x, int y) {
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

			final ItemStack pixel = child.pixelAt(localX, localY);
			if (pixel != null) {
				return pixel;
			}
		}

		if (isInBounds && background != null) {
			return background.backgroundAt(x, y);
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
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
