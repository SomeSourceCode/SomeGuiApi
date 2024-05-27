package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The context for a GUI render cycle.
 *
 * @since 2.0.0
 */
public class RenderContext extends GuiContext {

	protected long renderStart;

	/**
	 * Constructs a new render context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @since 2.1.0
	 */
	public RenderContext(Gui gui, Scene scene) {
		super(gui, scene);
		renderStart = System.currentTimeMillis();
	}

	/**
	 * Returns the time when the render cycle started.
	 *
	 * @return the time when the render cycle started
	 * @since 2.1.0
	 */
	public long getRenderStart() {
		return renderStart;
	}

	/**
	 * Returns the time it took to render the GUI
	 * since the render cycle started.
	 *
	 * @return the time it took to render the GUI
	 * @since 2.1.0
	 */
	public long getRenderTime() {
		return System.currentTimeMillis() - renderStart;
	}

}
