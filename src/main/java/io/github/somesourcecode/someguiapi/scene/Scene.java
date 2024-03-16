package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;

import java.util.ArrayList;

public class Scene {

	private Parent root;
	private Background background;

	public Scene() {

	}

	public Scene(Parent root) {
		this.root = root;
	}

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

	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

}
