package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.entity.HumanEntity;

/**
 * Represents the context of a GUI close event.
 *
 * @since 2.1.0
 */
public class GuiCloseContext extends GuiContext {

	private final HumanEntity player;

	/**
	 * Constructs a new GUI close context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param player the player
	 * @since 2.1.0
	 */
	public GuiCloseContext(Gui gui, Scene scene, HumanEntity player) {
		super(gui, scene);
		this.player = player;
	}

	/**
	 * Returns the human entity whose GUI is being closed.
	 *
	 * @return the human entity
	 */
	public HumanEntity getPlayer() {
		return player;
	}

}
