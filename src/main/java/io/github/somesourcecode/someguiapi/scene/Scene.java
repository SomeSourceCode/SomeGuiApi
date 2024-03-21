package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;

import java.util.ArrayList;

/**
 * The Scene class is the container for all content in a scene graph.
 * The background of the scene is filled as specified by the background property.
 * <p>
 * For content to be rendered the application must specify a root Node by setting the root property.
 */
public class Scene {

	private Parent root;
	private Background background;

	/**
	 * Constructs a new empty scene.
	 */
	public Scene() {

	}

	/**
	 * Constructs a new scene with the given root.
	 * @param root the root of the scene
	 */
	public Scene(Parent root) {
		this.root = root;
	}

	/**
	 * Fires the onClick event for the node at the given coordinates.
	 * The listeners a called for the clicked node and all of its parents, respectively.
	 * @param context the click context
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
	 * @return the root of the scene
	 */
	public Parent getRoot() {
		return root;
	}

	/**
	 * Sets the root of the scene.
	 * @param root the new root of the scene
	 */
	public void setRoot(Parent root) {
		this.root = root;
	}

	/**
	 * Returns the background of the scene.
	 * @return the background of the scene
	 */
	public Background getBackground() {
		return background;
	}

	/**
	 * Sets the background of the scene.
	 * @param background the new background of the scene
	 */
	public void setBackground(Background background) {
		this.background = background;
	}

}
