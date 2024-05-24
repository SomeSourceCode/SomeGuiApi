package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.entity.HumanEntity;

/**
 * Represents the context of a GUI close event.
 *
 * @since 2.1.0
 */
public class GuiCloseContext extends GuiContext implements Cancelable {

	private final HumanEntity player;

	private boolean canceled;

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

	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

}
