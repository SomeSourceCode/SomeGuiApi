package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;

public class VBox extends Pane {

	private int spacing;

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	@Override
	public void layoutChildren() {
		final int leftPadding = getPadding().getLeft();

		int y = getPadding().getTop();
		for (Node child : getChildren()) {
			child.relocate(leftPadding, y);
			y += child.getHeight() + spacing;
		}
	}

}
