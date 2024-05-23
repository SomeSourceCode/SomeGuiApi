package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The basic context for a GUI.
 *
 * @since 2.1.0
 */
public class GuiContext implements Context {

	private final Gui gui;
	private final Scene scene;

	/**
	 * Constructs a new GUI context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @since 2.1.0
	 */
	public GuiContext(Gui gui, Scene scene) {
		this.gui = gui;
		this.scene = scene;
	}

	/**
	 * Returns the GUI.
	 *
	 * @return the GUI
	 * @since 2.1.0
	 */
	public Gui getGui() {
		return gui;
	}

	/**
	 * Returns the scene.
	 *
	 * @return the scene
	 * @since 2.1.0
	 */
	public Scene getScene() {
		return scene;
	}

}
