package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;

/**
 * A layout pane that arranges its children in a vertical column.
 */
public class VBox extends Pane {

	private int spacing;

	/**
	 * Returns the amount of vertical space between each child node.
	 * @return the vertical spacing between children
	 */
	public int getSpacing() {
		return spacing;
	}

	/**
	 * Sets the amount of vertical space between each child node.
	 * @param spacing the vertical spacing between children
	 */
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	@Override
	protected void layoutChildren() {
		final int leftPadding = getPadding().getLeft();

		int y = getPadding().getTop();
		for (Node child : getChildren()) {
			child.relocate(leftPadding + child.getTranslateX(), y + child.getTranslateY());
			y += child.getHeight() + spacing;
		}
	}

}
