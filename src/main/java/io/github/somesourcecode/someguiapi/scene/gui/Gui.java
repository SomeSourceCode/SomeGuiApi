package io.github.somesourcecode.someguiapi.scene.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * The base class for GUIs that can be shown to players.
 */
public abstract class Gui {

	protected Inventory inventory;

	/**
	 * Shows this GUI to the specified player.
	 * @param player the player
	 */
	public abstract void show(Player player);

	/**
	 * Returns a list of players that are currently viewing this GUI.
	 */
	public abstract List<Player> getViewers();

	/**
	 * Updates the GUI for all viewers.
	 */
	public void update() {
		for (Player viewer : getViewers()) {
			show(viewer);
		}
	}

}
