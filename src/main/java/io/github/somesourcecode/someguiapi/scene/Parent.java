package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.collections.ObservableList;
import io.github.somesourcecode.someguiapi.collections.ObservableListBase;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class Parent extends Node {

	private final ObservableList<Node> children = new ObservableListBase<>(new ArrayList<>());

	private Background background;

	protected ObservableList<Node> getChildren() {
		return children;
	}

	public Parent() {
		children.addListener(change -> {
			if (change.wasAdded()) {
				for (Node child : change.getAddedSubList()) {
					if (child.getParent() != null) {
						throw new IllegalStateException("Node is already in the scene graph. A node may only be added to one parent.");
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

	public final void layout() {
		for (Node child : children) {
			if (child instanceof Parent parent) {
				parent.layout();
			}
		}
		layoutChildren();
	}

	public void layoutChildren() {

	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

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
