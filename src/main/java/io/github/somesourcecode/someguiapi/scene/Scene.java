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
import io.github.somesourcecode.someguiapi.scene.data.ContextDataHolder;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

import java.util.ArrayList;

/**
 * The Scene class is the container for all content in a scene graph.
 * The background of the scene is filled as specified by the background property.
 * <p>
 * For content to be rendered the application must specify a root Node by setting the root property.
 *
 * @since 1.0.0
 */
public class Scene {

	static {
		SceneHelper.setSceneAccessor(new SceneHelper.SceneAccessor() {
			@Override
			public void setGui(Scene scene, Gui gui) {
				scene.setGui(gui);
			}
		});
	}

	private final ContextDataHolder dataHolder = new ContextDataHolder();

	private Gui gui;

	private Parent root;
	private Background background;

	/**
	 * Constructs a new empty scene.
	 *
	 * @since 1.0.0
	 */
	public Scene() {

	}

	/**
	 * Constructs a new scene with the given root.
	 *
	 * @param root the root of the scene
	 * @since 1.0.0
	 */
	public Scene(Parent root) {
		setRoot(root);
	}

	/**
	 * Returns the data holder of this scene.
	 *
	 * @return the data holder of this scene
	 * @since 2.0.0
	 */
	public ContextDataHolder getDataHolder() {
		return dataHolder;
	}

	/**
	 * Returns the GUI that this scene is attached to.
	 *
	 * @return the GUI that this scene is attached to
	 * @since 2.0.0
	 */
	public Gui getGui() {
		return gui;
	}

	/**
	 * Sets the GUI that this scene is attached to.
	 *
	 * @param gui the GUI that this scene is attached to
	 * @since 2.0.0
	 */
	private void setGui(Gui gui) {
		this.gui = gui;
	}

	/**
	 * Fires the onClick event for the node at the given coordinates.
	 * The listeners a called for the clicked node and all of its parents, respectively.
	 *
	 * @param context the click context
	 * @since 1.0.0
	 */
	public void fireOnClick(NodeClickContext context) {
		if (root == null) {
			return;
		}

		int x = context.getSlotX();
		int y = context.getSlotY();

		final int localX = x - root.getLayoutX();
		final int localY = y - root.getLayoutY();

		final ArrayList<Node> nodeBranch = new ArrayList<>();

		Node clickedNode = root.nodeAt(localX, localY);
		if (clickedNode == null) {
			return;
		}
		nodeBranch.add(clickedNode);

		Parent parent = clickedNode.getParent();
		int clickedLocalX = clickedNode.getLayoutX();
		int clickedLocalY = clickedNode.getLayoutY();
		while (parent != null) {
			if (clickedLocalX >= 0 && clickedLocalY >= 0 && clickedLocalX < parent.getWidth() && clickedLocalY < parent.getHeight()) {
				nodeBranch.add(parent);
			}
			clickedLocalX += parent.getLayoutX();
			clickedLocalY += parent.getLayoutY();
			parent = parent.getParent();
		}

		for (Node node : nodeBranch) {
			if (node.getOnClick() != null) {
				node.getOnClick().accept(context);
			}
			if (node.getOnLeftClick() != null && context.isLeftClick()) {
				node.getOnLeftClick().accept(context);
			}
			if (node.getOnRightClick() != null && context.isRightClick()) {
				node.getOnRightClick().accept(context);
			}
			if (node.getOnShiftClick() != null && context.isShiftClick()) {
				node.getOnShiftClick().accept(context);
			}
			if (node.getOnHotBarClick() != null && context.isHotBarClick()) {
				node.getOnHotBarClick().accept(context);
			}
		}
	}

	/**
	 * Returns the root of the scene.
	 *
	 * @return the root of the scene
	 * @since 1.0.0
	 */
	public Parent getRoot() {
		return root;
	}

	/**
	 * Sets the root of the scene.
	 *
	 * @param root the new root of the scene
	 * @since 1.0.0
	 */
	public void setRoot(Parent root) {
		if (this.root == root) {
			return;
		}
		if (this.root != null) {
			NodeHelper.setScene(this.root, null);
		}
		this.root = root;
		if (root != null) {
			NodeHelper.setScene(root, this);
		}
	}

	/**
	 * Looks for any node in the scene that matches the given selector.
	 * If multiple nodes are found, the first one found is returned.
	 * If no node is found, null is returned.
	 *
	 * @param selector the selector
	 * @return the first node on the scene graph that matches the selector,
	 * null if no node matches the selector
	 * @see Node#lookup(String)
	 */
	public Node lookup(String selector) {
		if (root == null) {
			return null;
		}
		return root.lookup(selector);
	}

	/**
	 * Returns the background of the scene.
	 *
	 * @return the background of the scene
	 * @since 1.0.0
	 */
	public Background getBackground() {
		return background;
	}

	/**
	 * Sets the background of the scene.
	 *
	 * @param background the new background of the scene
	 * @since 1.0.0
	 */
	public void setBackground(Background background) {
		this.background = background;
	}

}
