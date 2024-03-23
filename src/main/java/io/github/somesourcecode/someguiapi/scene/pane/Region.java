package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.util.Insets;
import io.github.somesourcecode.someguiapi.scene.Parent;

/**
 * The Base class for parent nodes whose size can be freely set.
 * <p>
 * This class handles the size of the node and its children, as well as padding.
 * To create layout panes, you probably want to extend {@link Pane} instead.
 */
public class Region extends Parent {

	private int width;
	private int height;

	private Insets padding;

	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this region.
	 * @param width the width of this region
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this region.
	 * @param height the height of this region
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Resizes this region to the specified width and height.
	 * <p>
	 * This is equivalent to calling {@link #setWidth(int)} and {@link #setHeight(int)}.
	 * @param width the new width of this region
	 * @param height the new height of this region
	 */
	public void resize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Resizes and relocates this region to the specified layoutX, layoutY, width, and height.
	 * <p>
	 * This is equivalent to calling {@link #relocate(int, int)} and {@link #resize(int, int)}.
	 * @param layoutX the new layoutX of this region
	 * @param layoutY the new layoutY of this region
	 * @param width the new width of this region
	 * @param height the new height of this region
	 */
	public void resizeRelocate(int layoutX, int layoutY, int width, int height) {
		relocate(layoutX, layoutY);
		resize(width, height);
	}

	/**
	 * Returns the padding of this region.
	 * @return the padding of this region
	 */
	public Insets getPadding() {
		return padding == null ? Insets.EMPTY : padding;
	}

	/**
	 * Sets the padding of this region.
	 * @param padding the padding of this region
	 */
	public void setPadding(Insets padding) {
		this.padding = padding;
	}

}
