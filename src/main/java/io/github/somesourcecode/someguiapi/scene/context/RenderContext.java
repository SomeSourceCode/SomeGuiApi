package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The context for a GUI render cycle.
 *
 * @since 2.0.0
 */
public class RenderContext extends GuiContext {

	/**
	 * Constructs a new render context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @since 2.1.0
	 */
	public RenderContext(Gui gui, Scene scene) {
		super(gui, scene);
	}

}
