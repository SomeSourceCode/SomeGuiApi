package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.scene.Parent;

public class Region extends Parent {

	private int width;
	private int height;

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void resize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void resizeRelocate(int layoutX, int layoutY, int width, int height) {
		relocate(layoutX, layoutY);
		resize(width, height);
	}

}
