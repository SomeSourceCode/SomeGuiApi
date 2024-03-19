package io.github.somesourcecode.someguiapi.util;

public class Insets {

	public static final Insets EMPTY = new Insets(0, 0, 0, 0);

	private final int top;
	private final int right;
	private final int bottom;
	private final int left;

	public Insets(int top, int right, int bottom, int left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public Insets(int topBottom, int rightLeft) {
		this.top = topBottom;
		this.right = rightLeft;
		this.bottom = topBottom;
		this.left = rightLeft;
	}

	public Insets(int topRightBottomLeft) {
		this.top = topRightBottomLeft;
		this.right = topRightBottomLeft;
		this.bottom = topRightBottomLeft;
		this.left = topRightBottomLeft;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}

	public int getLeft() {
		return left;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Insets other)) {
			return false;
		}
		return top == other.top && right == other.right && bottom == other.bottom && left == other.left;
	}

}
