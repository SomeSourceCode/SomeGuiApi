package io.github.somesourcecode.someguiapi.scene.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class Gui {

	protected Inventory inventory;

	public abstract void show(Player player);

	public abstract List<Player> getViewers();

	public void update() {
		for (Player viewer : getViewers()) {
			show(viewer);
		}
	}

}
