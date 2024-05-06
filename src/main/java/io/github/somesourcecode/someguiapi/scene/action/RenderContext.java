package io.github.somesourcecode.someguiapi.scene.action;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

public class RenderContext {

	private final Gui gui;
	private final Scene scene;

	public RenderContext(Gui gui, Scene scene) {
		this.gui = gui;
		this.scene = scene;
	}

	public Gui getGui() {
		return gui;
	}

	public Scene getScene() {
		return scene;
	}

}
