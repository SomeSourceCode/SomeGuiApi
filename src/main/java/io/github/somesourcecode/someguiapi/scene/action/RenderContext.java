package io.github.somesourcecode.someguiapi.scene.action;

import io.github.somesourcecode.someguiapi.scene.Scene;

public class RenderContext {

	private final Scene scene;

	public RenderContext(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

}
