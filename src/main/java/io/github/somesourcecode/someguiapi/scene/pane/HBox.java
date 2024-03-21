package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;

public class HBox extends Pane {

	private int spacing;

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	@Override
	protected void layoutChildren() {
		final int topPadding = getPadding().getTop();

		int x = getPadding().getLeft();
		for (Node child : getChildren()) {
			child.relocate(x + child.getTranslateX(), topPadding + child.getTranslateY());
			x += child.getWidth() + spacing;
		}
	}

}
