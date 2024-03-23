package io.github.somesourcecode.someguiapi;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import io.github.somesourcecode.someguiapi.scene.gui.ChestGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A listener that listens for GUI interactions.
 */
public class GuiListener implements Listener {

	@EventHandler
	public void onGuiClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player player)) {
			return;
		}
		if (event.getClickedInventory() == null || !(event.getClickedInventory().getHolder() instanceof ChestGui gui)) {
			return;
		}
		event.setCancelled(true);

		int slotX = event.getSlot() % 9;
		int slotY = event.getSlot() / 9;

		gui.getScene().fireOnClick(new NodeClickContext(slotX, slotY, event.getClick(), player, event.getHotbarButton()));
	}

}
