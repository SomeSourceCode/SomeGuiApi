package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The context for a pixel render cycle.
 *
 * @since 2.1.0
 */
public class PixelRenderContext extends RenderContext {

	private final int slotX;
	private final int slotY;

	/**
	 * Constructs a new pixel render context.
	 *
	 * @param gui   the GUI
	 * @param scene the scene
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @since 2.1.0
	 */
	public PixelRenderContext(Gui gui, Scene scene, int slotX, int slotY) {
		super(gui, scene);
		this.slotX = slotX;
		this.slotY = slotY;
	}

	/**
	 * Returns the x coordinate of the slot.
	 * This is relative to the top-left corner of the GUI.
	 *
	 * @return the x coordinate of the slot
	 * @since 2.1.0
	 */
	public int getSlotX() {
		return slotX;
	}

	/**
	 * Returns the y coordinate of the slot.
	 * This is relative to the top-left corner of the GUI.
	 *
	 * @return the y coordinate of the slot
	 * @since 2.1.0
	 */
	public int getSlotY() {
		return slotY;
	}

}
