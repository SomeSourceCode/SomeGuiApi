package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;

/**
 * A layout pane that arranges its children in a horizontal row.
 */
public class HBox extends Pane {

	private int spacing;

	/**
	 * Returns the amount of horizontal space between each child node.
	 * @return the horizontal spacing between children
	 */
	public int getSpacing() {
		return spacing;
	}

	/**
	 * Sets the amount of horizontal space between each child node.
	 * @param spacing the horizontal spacing between children
	 */
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
