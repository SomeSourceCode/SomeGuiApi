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
	public void layoutChildren() {
		final Orientation orientation = getOrientation();
		final int maxLength = orientation == Orientation.HORIZONTAL ? getWidth() : getHeight();

		int length = 0;
		int width = 0;

		int x = 0;
		int y = 0;

		for (Node child : getChildren()) {
			final int childLength = orientation == Orientation.HORIZONTAL ? child.getWidth() : child.getHeight();
			final int childWidth = orientation == Orientation.HORIZONTAL ? child.getHeight() : child.getWidth();
			if ((orientation == Orientation.HORIZONTAL ? x : y) + childLength > maxLength && length > 0) {
				if (orientation == Orientation.HORIZONTAL) {
					x = 0;
					y += width + vGap;
				} else {
					y = 0;
					x += width + hGap;
				}
				length = 0;
			}

			child.relocate(x, y);

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
