package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Node;
import io.github.somesourcecode.someguiapi.util.Orientation;

public class FlowPane extends Pane {

	private Orientation orientation;

	private int hGap;
	private int vGap;

	public FlowPane() {
		this.orientation = Orientation.HORIZONTAL;
	}

	public Orientation getOrientation() {
		return orientation == null ? Orientation.HORIZONTAL : orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getHGap() {
		return hGap;
	}

	public void setHGap(int hGap) {
		this.hGap = hGap;
	}

	public int getVGap() {
		return vGap;
	}

	public void setVGap(int vGap) {
		this.vGap = vGap;
	}

	@Override
	protected void layoutChildren() {
		final int topPadding = getPadding().getTop();
		final int leftPadding = getPadding().getLeft();
		final int rightPadding = getPadding().getRight();
		final int bottomPadding = getPadding().getBottom();

		final int layoutWidth = getWidth() - leftPadding - rightPadding;
		final int layoutHeight = getHeight() - topPadding - bottomPadding;

		final Orientation orientation = getOrientation();
		final int maxLength = orientation == Orientation.HORIZONTAL ? layoutWidth : layoutHeight;

		int length = 0;
		int width = 0;

		int x = leftPadding;
		int y = topPadding;

		for (Node child : getChildren()) {
			final int childLength = orientation == Orientation.HORIZONTAL ? child.getWidth() : child.getHeight();
			final int childWidth = orientation == Orientation.HORIZONTAL ? child.getHeight() : child.getWidth();
			if ((orientation == Orientation.HORIZONTAL ? x : y) + childLength > maxLength && length > 0) {
				if (orientation == Orientation.HORIZONTAL) {
					x = leftPadding;
					y += width + vGap;
				} else {
					y = topPadding;
					x += width + hGap;
				}
				length = 0;
			}

			child.relocate(x + child.getTranslateX(), y + child.getTranslateY());

			width = Math.max(width, childWidth);
			length += childLength;

			if (orientation == Orientation.HORIZONTAL) {
				x += childLength + hGap;
			} else {
				y += childLength + vGap;
			}
		}
	}

}
