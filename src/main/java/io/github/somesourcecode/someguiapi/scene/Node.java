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

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * The base class for all nodes in the scene graph. A scene graph is a set of tree data structures
 * where every item has zero or one parent, and each item is either
 * a "leaf" with zero sub-items or a "branch" with zero or more sub-items.
 * <p>
 * Each item in the scene graph is called a {@code Node}. Branch nodes are of type {@link Parent}.
 * <p>
 * If a program adds a child node to a Parent and that node is already a child of
 * a different Parent or the root of a Scene, the node is automatically (and silently)
 * removed from its former parent.
 * <p>
 * It is possible to rearrange the scene graph, e.g. to move a node or subtree from one location
 * in the scene graph to another. This is normally done by removing the node from its current location
 * before inserting it at the new location. However, a subtree will be automatically removed
 * if it is added to a new parent, as described above.
 *
 * <h4>Coordinate System</h4>
 * <p>
 * The {@code Node} class defines a computer graphics "local" coordinate system. Coordinates are
 * specified in integer pixels, with the origin (0,0) in the top-left corner of the node.
 * The x-coordinate increases to the right, and the y-coordinate increases downwards.
 *
 * <h4>Transformations</h4>
 * <p>
 * Translation coordinates are specified in integer pixels, and move the node's origin by the
 * specified amount in the x and y directions. The translation is applied after the layout
 * has been computed, but before the node is rendered.
 */
public abstract class Node {

	private Parent parent;

	private int layoutX;
	private int layoutY;

	private int translateX;
	private int translateY;

	private boolean visible = true;
	private boolean clipping = true;

	private Consumer<NodeClickContext> onClick;
	private Consumer<NodeClickContext> onLeftClick;
	private Consumer<NodeClickContext> onRightClick;
	private Consumer<NodeClickContext> onShiftClick;
	private Consumer<NodeClickContext> onHotBarClick;

	/**
	 * Returns the parent of this node.
	 * If this node has no parent, this method returns null.
	 *
	 * @return the parent of this node
	 */
	public Parent getParent() {
		return parent;
	}

	protected final void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * Returns the x coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @return the x coordinate of the node's origin
	 */
	public int getLayoutX() {
		return layoutX;
	}

	/**
	 * Sets the x coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @param layoutX the x coordinate of the node's origin
	 */
	public void setLayoutX(int layoutX) {
		this.layoutX = layoutX;
	}

	/**
	 * Returns the y coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @return the y coordinate of the node's origin
	 */
	public int getLayoutY() {
		return layoutY;
	}

	/**
	 * Sets the y coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @param layoutY the y coordinate of the node's origin
	 */
	public void setLayoutY(int layoutY) {
		this.layoutY = layoutY;
	}

	/**
	 * Moves the node to the specified location.
	 * This is equivalent to setting the layoutX and layoutY properties.
	 *
	 * @param layoutX the x coordinate of the node's origin
	 * @param layoutY the y coordinate of the node's origin
	 */
	public void relocate(int layoutX, int layoutY) {
		setLayoutX(layoutX);
		setLayoutY(layoutY);
	}

	/**
	 * Returns the x translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @return the x translation of the node
	 */
	public int getTranslateX() {
		return translateX;
	}

	/**
	 * Sets the x translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @param translateX the x translation of the node
	 */
	public void setTranslateX(int translateX) {
		this.translateX = translateX;
	}

	/**
	 * Returns the y translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @return the y translation of the node
	 */
	public int getTranslateY() {
		return translateY;
	}

	/**
	 * Sets the y translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @param translateY the y translation of the node
	 */
	public void setTranslateY(int translateY) {
		this.translateY = translateY;
	}

	/**
	 * Returns the width of the node.
	 * @return the width of the node
	 */
	public abstract int getWidth();

	/**
	 * Returns the height of the node.
	 * @return the height of the node
	 */
	public abstract int getHeight();

	/**
	 * Returns whether the node is visible.
	 * If a node is not visible, it will not be rendered, but it
	 * will still be considered for layout calculations.
	 * @return whether the node is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets whether the node is visible.
	 * If a node is not visible, it will not be rendered, but it
	 * will still be considered for layout calculations.
	 * @param visible whether the node is visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Returns whether the node is clipping its children.
	 * If a node is clipping its children, children that are outside
	 * the bounds of the node will not be rendered.
	 * @return whether the node is clipping its children
	 */
	public boolean isClipping() {
		return clipping;
	}

	/**
	 * Sets whether the node is clipping its children.
	 * If a node is clipping its children, children that are outside
	 * the bounds of the node will not be rendered.
	 * @param clipping whether the node is clipping its children
	 */
	public void setClipping(boolean clipping) {
		this.clipping = clipping;
	}

	/**
	 * Returns the consumer that is called when the node is clicked.
	 * <p>
	 * This consumer will be called when the node is clicked, regardless of the type of click.
	 * @return the consumer that is called when the node is clicked
	 */
	public Consumer<NodeClickContext> getOnClick() {
		return onClick;
	}

	/**
	 * Sets the consumer that is called when the node is clicked.
	 * <p>
	 * This consumer will be called when the node is clicked, regardless of the type of click.
	 * @param onClick the consumer that is called when the node is clicked
	 */
	public void setOnClick(Consumer<NodeClickContext> onClick) {
		this.onClick = onClick;
	}

	/**
	 * Returns the consumer that is called when the node is left-clicked.
	 * @return the consumer that is called when the node is left-clicked
	 */
	public Consumer<NodeClickContext> getOnLeftClick() {
		return onLeftClick;
	}

	/**
	 * Sets the consumer that is called when the node is left-clicked.
	 * @param onLeftClick the consumer that is called when the node is left-clicked
	 */
	public void setOnLeftClick(Consumer<NodeClickContext> onLeftClick) {
		this.onLeftClick = onLeftClick;
	}

	/**
	 * Returns the consumer that is called when the node is right-clicked.
	 * @return the consumer that is called when the node is right-clicked
	 */
	public Consumer<NodeClickContext> getOnRightClick() {
		return onRightClick;
	}

	/**
	 * Sets the consumer that is called when the node is right-clicked.
	 * @param onRightClick the consumer that is called when the node is right-clicked
	 */
	public void setOnRightClick(Consumer<NodeClickContext> onRightClick) {
		this.onRightClick = onRightClick;
	}

	/**
	 * Returns the consumer that is called when the node is shift-clicked.
	 * @return the consumer that is called when the node is shift-clicked
	 */
	public Consumer<NodeClickContext> getOnShiftClick() {
		return onShiftClick;
	}

	/**
	 * Sets the consumer that is called when the node is shift-clicked.
	 * @param onShiftClick the consumer that is called when the node is shift-clicked
	 */
	public void setOnShiftClick(Consumer<NodeClickContext> onShiftClick) {
		this.onShiftClick = onShiftClick;
	}

	/**
	 * Returns the consumer that is called when the node receives a hot bar click.
	 * @return the consumer that is called when the node receives a hot bar click
	 */
	public Consumer<NodeClickContext> getOnHotBarClick() {
		return onHotBarClick;
	}

	/**
	 * Sets the consumer that is called when the node receives a hot bar click.
	 * @param onHotBarClick the consumer that is called when the node receives a hot bar click
	 */
	public void setOnHotBarClick(Consumer<NodeClickContext> onHotBarClick) {
		this.onHotBarClick = onHotBarClick;
	}

	/**
	 * Returns an {@link ItemStack} representing the pixel at the given coordinates.
	 * The coordinates are relative to this parent's bounds.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the ItemStack at the given coordinates, or null if there is no pixel at the given coordinates
	 */
	public abstract ItemStack pixelAt(int x, int y);

	/**
	 * Returns the node at the given coordinates.
	 * The coordinates are relative to this parent's bounds.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the node at the given coordinates, or null if there is no node at the given coordinates
	 */
	public abstract Node nodeAt(int x, int y);

}
