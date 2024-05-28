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

import io.github.somesourcecode.someguiapi.scene.context.Context;
import io.github.somesourcecode.someguiapi.scene.context.NodeClickContext;
import org.bukkit.Bukkit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;

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
 * <p>
 * <br>
 * <b>Coordinate System</b>
 * <p>
 * The {@code Node} class defines a computer graphics "local" coordinate system. Coordinates are
 * specified in integer pixels, with the origin (0,0) in the top-left corner of the node.
 * The x-coordinate increases to the right, and the y-coordinate increases downwards.
 * <p>
 * <br>
 * <b>Transformations</b>
 * <p>
 * Translation coordinates are specified in integer pixels, and move the node's origin by the
 * specified amount in the x and y directions. The translation is applied after the layout
 * has been computed, but before the node is rendered.
 *
 * @since 1.0.0
 */
public abstract class Node {

	static {
		NodeHelper.setNodeAccessor(new NodeHelper.NodeAccessor() {
			@Override
			public void setScene(Node node, Scene scene) {
				node.setScene(scene);
			}

			@Override
			public void setParent(Node node, Parent parent) {
				node.setParent(parent);
			}
		});
	}

	private Scene scene;
	private Parent parent;

	private String id;

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
	 * Returns the scene that this node is in.
	 * If this node is not in a scene, this method returns null.
	 *
	 * @return the scene that this node is in
	 * @since 2.0.0
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the scene that this node is in.
	 *
	 * @param scene the scene that this node is in
	 * @since 2.0.0
	 */
	private void setScene(Scene scene) {
		this.scene = scene;
		if (this instanceof Parent asParent) {
			for (Node child : asParent.getChildren()) {
				child.setScene(scene);
			}
		}
	}

	/**
	 * Returns the parent of this node.
	 * If this node has no parent, this method returns null.
	 *
	 * @return the parent of this node
	 * @since 1.0.0
	 */
	public Parent getParent() {
		return parent;
	}

	/**
	 * Sets the parent of this node.
	 *
	 * @param parent the parent of this node
	 * @since 1.0.0
	 */
	private void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * Returns the id of this node.
	 *
	 * @return the id of this node
	 * @since 2.0.0
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Sets the id of this node.
	 * While the id should be unique, this uniqueness not enforced.
	 *
	 * @param id the id of this node
	 * @since 2.0.0
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * Finds this {@code Node} or the first sub-node by the given selector.
	 * <p>
	 * For example, to find a node with the id "my-node", the method can be
	 * used like this: {@code scene.lookup("#my-node")}.
	 *
	 * @param selector the selector
	 * @return the first node that matches the selector, null if none is found
	 * @since 2.0.0
	 */
	public Node lookup(String selector) {
		if (selector == null || id == null) {
			return null;
		}
		if (selector.equals("#" + id)) {
			return this;
		}
		return null;
	}

	/**
	 * Finds all nodes that match the given selector.
	 * <p>
	 * For example, to find all nodes with the class "my-class", the method can be
	 * used like this: {@code scene.lookupAll(".my-class")}.
	 *
	 * @param selector the selector
	 * @return a set of nodes that match the selector. This is always non-null and unmodifiable.
	 * @since 2.0.0
	 */
	public Set<Node> lookupAll(String selector) {
		final Set<Node> empty = Collections.emptySet();
		if (selector == null) {
			return empty;
		}
		Set<Node> results = lookupAll(selector, null);
		return results == null ? empty : Collections.unmodifiableSet(results);
	}

	/**
	 * Used by Node and Parent to traverse the scene graph to find
	 * all nodes that match the given selector.
	 *
	 * @param selector the selector
	 * @param results the results
	 * @return a set of nodes that match the selector; null if none is found
	 * @since 2.0.0
	 */
	protected Set<Node> lookupAll(String selector, Set<Node> results) {
		if (selector == null || id == null) {
			return results;
		}

		if (selector.equals("#" + id)) {
			if (results == null) {
				results = new HashSet<>();
			}
			results.add(this);
		}
		return results;
	}

	/**
	 * Requests a layout update for this node's parent.
	 * Layout will be applied on the next layout pass.
	 *
	 * @since 2.0.0
	 */
	public void requestParentLayout() {
		Parent parent = getParent();
		if (parent != null) {
			parent.requestLayout();
		}
	}

	/**
	 * Returns the x coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @return the x coordinate of the node's origin
	 * @since 1.0.0
	 */
	public int getLayoutX() {
		return layoutX;
	}

	/**
	 * Sets the x coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @param layoutX the x coordinate of the node's origin
	 * @since 1.0.0
	 */
	public void setLayoutX(int layoutX) {
		if (this.layoutX == layoutX) {
			return;
		}
		this.layoutX = layoutX;
		requestParentLayout();
	}

	/**
	 * Returns the y coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @return the y coordinate of the node's origin
	 * @since 1.0.0
	 */
	public int getLayoutY() {
		return layoutY;
	}

	/**
	 * Sets the y coordinate of the node's origin.
	 * The origin is the top-left corner of the node.
	 *
	 * @param layoutY the y coordinate of the node's origin
	 * @since 1.0.0
	 */
	public void setLayoutY(int layoutY) {
		if (this.layoutY == layoutY) {
			return;
		}
		this.layoutY = layoutY;
		requestParentLayout();
	}

	/**
	 * Moves the node to the specified location.
	 * This is equivalent to setting the layoutX and layoutY properties.
	 *
	 * @param layoutX the x coordinate of the node's origin
	 * @param layoutY the y coordinate of the node's origin
	 * @since 1.0.0
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
	 * @since 1.0.0
	 */
	public int getTranslateX() {
		return translateX;
	}

	/**
	 * Sets the x translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @param translateX the x translation of the node
	 * @since 1.0.0
	 */
	public void setTranslateX(int translateX) {
		if (this.translateX == translateX) {
			return;
		}
		this.translateX = translateX;
		requestParentLayout();
	}

	/**
	 * Returns the y translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @return the y translation of the node
	 * @since 1.0.0
	 */
	public int getTranslateY() {
		return translateY;
	}

	/**
	 * Sets the y translation of the node.
	 * The translation is applied after the layout has been computed, but before the node is rendered.
	 *
	 * @param translateY the y translation of the node
	 * @since 1.0.0
	 */
	public void setTranslateY(int translateY) {
		if (this.translateY == translateY) {
			return;
		}
		this.translateY = translateY;
		requestParentLayout();
	}

	/**
	 * Returns the width of the node.
	 *
	 * @return the width of the node
	 * @since 1.0.0
	 */
	public abstract int getWidth();

	/**
	 * Returns the height of the node.
	 *
	 * @return the height of the node
	 * @since 1.0.0
	 */
	public abstract int getHeight();

	/**
	 * Returns whether the node is visible.
	 * If a node is not visible, it will not be rendered, but it
	 * will still be considered for layout calculations.
	 *
	 * @return whether the node is visible
	 * @since 1.0.0
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets whether the node is visible.
	 * If a node is not visible, it will not be rendered, but it
	 * will still be considered for layout calculations.
	 *
	 * @param visible whether the node is visible
	 * @since 1.0.0
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Returns whether the node is clipping its children.
	 * If a node is clipping its children, children that are outside
	 * the bounds of the node will not be rendered.
	 *
	 * @return whether the node is clipping its children
	 * @since 1.0.0
	 */
	public boolean isClipping() {
		return clipping;
	}

	/**
	 * Sets whether the node is clipping its children.
	 * If a node is clipping its children, children that are outside
	 * the bounds of the node will not be rendered.
	 *
	 * @param clipping whether the node is clipping its children
	 * @since 1.0.0
	 */
	public void setClipping(boolean clipping) {
		this.clipping = clipping;
	}

	/**
	 * Returns the consumer that is called when the node is clicked.
	 * <p>
	 * This consumer will be called when the node is clicked, regardless of the type of click.
	 *
	 * @return the consumer that is called when the node is clicked
	 * @since 1.0.0
	 */
	public Consumer<NodeClickContext> getOnClick() {
		return onClick;
	}

	/**
	 * Sets the consumer that is called when the node is clicked.
	 * <p>
	 * This consumer will be called when the node is clicked, regardless of the type of click.
	 *
	 * @param onClick the consumer that is called when the node is clicked
	 * @since 1.0.0
	 */
	public void setOnClick(Consumer<NodeClickContext> onClick) {
		this.onClick = onClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnClick(NodeClickContext context) {
		fireCallback(onClick, context, "onClick");
	}

	/**
	 * Returns the consumer that is called when the node is left-clicked.
	 *
	 * @return the consumer that is called when the node is left-clicked
	 * @since 1.0.0
	 */
	public Consumer<NodeClickContext> getOnLeftClick() {
		return onLeftClick;
	}

	/**
	 * Sets the consumer that is called when the node is left-clicked.
	 *
	 * @param onLeftClick the consumer that is called when the node is left-clicked
	 * @since 1.0.0
	 */
	public void setOnLeftClick(Consumer<NodeClickContext> onLeftClick) {
		this.onLeftClick = onLeftClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnLeftClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnLeftClick(NodeClickContext context) {
		fireCallback(onLeftClick, context, "onLeftClick");
	}

	/**
	 * Returns the consumer that is called when the node is right-clicked.
	 *
	 * @return the consumer that is called when the node is right-clicked
	 * @since 1.0.0
	 */
	public Consumer<NodeClickContext> getOnRightClick() {
		return onRightClick;
	}

	/**
	 * Sets the consumer that is called when the node is right-clicked.
	 *
	 * @param onRightClick the consumer that is called when the node is right-clicked
	 * @since 1.0.0
	 */
	public void setOnRightClick(Consumer<NodeClickContext> onRightClick) {
		this.onRightClick = onRightClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnRightClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnRightClick(NodeClickContext context) {
		fireCallback(onRightClick, context, "onRightClick");
	}

	/**
	 * Returns the consumer that is called when the node is shift-clicked.
	 *
	 * @return the consumer that is called when the node is shift-clicked
	 * @since 1.0.0
	 */
	public Consumer<NodeClickContext> getOnShiftClick() {
		return onShiftClick;
	}

	/**
	 * Sets the consumer that is called when the node is shift-clicked.
	 *
	 * @param onShiftClick the consumer that is called when the node is shift-clicked
	 * @since 1.0.0
	 */
	public void setOnShiftClick(Consumer<NodeClickContext> onShiftClick) {
		this.onShiftClick = onShiftClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnShiftClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnShiftClick(NodeClickContext context) {
		fireCallback(onShiftClick, context, "onShiftClick");
	}

	/**
	 * Returns the consumer that is called when the node receives a hot bar click.
	 *
	 * @return the consumer that is called when the node receives a hot bar click
	 * @since 1.0.0
	 */
	public Consumer<NodeClickContext> getOnHotBarClick() {
		return onHotBarClick;
	}

	/**
	 * Sets the consumer that is called when the node receives a hot bar click.
	 *
	 * @param onHotBarClick the consumer that is called when the node receives a hot bar click
	 * @since 1.0.0
	 */
	public void setOnHotBarClick(Consumer<NodeClickContext> onHotBarClick) {
		this.onHotBarClick = onHotBarClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnHotBarClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnHotBarClick(NodeClickContext context) {
		fireCallback(onHotBarClick, context, "onHotBarClick");
	}

	/**
	 * Returns a {@link Pixel} that should be rendered at the given coordinates.
	 * The coordinates are relative to this parent's bounds.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the pixel at the given coordinates, or null if there is no pixel at the given coordinates
	 * @since 2.0.0
	 */
	public abstract Pixel renderPixelAt(int x, int y);

	/**
	 * Returns the node at the given coordinates.
	 * The coordinates are relative to this parent's bounds.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the node at the given coordinates, or null if there is no node at the given coordinates
	 * @since 1.0.0
	 */
	public abstract Node nodeAt(int x, int y);

	/**
	 * Calls the given callback with the specified context.
	 * If the callback throws an exception, the exception is caught and logged.
	 *
	 * @param callback the callback
	 * @param context the context
	 * @param name the name of the callback
	 * @param <T> the type of the context
	 */
	protected <T extends Context> void fireCallback(Consumer<? super T> callback, T context, String name) {
		if (callback == null) {
			return;
		}

		try {
			callback.accept(context);
		} catch (Exception e) {
			String errorMessage = "An error occurred while calling '" + name + "''";
			Bukkit.getLogger().log(Level.SEVERE, errorMessage, e);
		}
	}

	/**
	 * Returns a string representation of this node.
	 *
	 * @return a string representation of this node
	 * @since 2.1.0
	 */
	@Override
	public String toString() {
		String simpleName = getClass().getSimpleName();
		boolean hasId = id != null && !id.isEmpty();

		StringBuilder builder = new StringBuilder(simpleName);
		if (hasId) {
			builder.append("[id=").append(id).append("]");
		} else {
			builder.append("@").append(Integer.toHexString(hashCode()));
		}

		return builder.toString();
	}

}
