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
	public void layoutChildren() {
		int x = 0;
		for (Node child : getChildren()) {
			child.relocate(x, 0);
			x += child.getWidth() + spacing;
		}
	}

}
