package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public abstract class Node {

	private Parent parent;

	private int layoutX;
	private int layoutY;

	private boolean visible = true;
	private boolean clipping = true;

	private Consumer<NodeClickContext> onClick;

	public Parent getParent() {
		return parent;
	}

	protected final void setParent(Parent parent) {
		this.parent = parent;
	}

	public int getLayoutX() {
		return layoutX;
	}

	public void setLayoutX(int layoutX) {
		this.layoutX = layoutX;
	}

	public int getLayoutY() {
		return layoutY;
	}

	public void setLayoutY(int layoutY) {
		this.layoutY = layoutY;
	}

	public void relocate(int layoutX, int layoutY) {
		setLayoutX(layoutX);
		setLayoutY(layoutY);
	}

	public abstract int getWidth();

	public abstract int getHeight();

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isClipping() {
		return clipping;
	}

	public void setClipping(boolean clipping) {
		this.clipping = clipping;
	}

	public Consumer<NodeClickContext> getOnClick() {
		return onClick;
	}

	public void setOnClick(Consumer<NodeClickContext> onClick) {
		this.onClick = onClick;
	}

	public abstract ItemStack pixelAt(int x, int y);

	public abstract Node nodeAt(int x, int y);

}
