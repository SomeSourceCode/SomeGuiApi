package io.github.somesourcecode.someguiapi;

import io.github.somesourcecode.someguiapi.scene.gui.ChestGui;
import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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

		event.getWhoClicked().sendMessage("You clicked in a GUI!");

		int slotX = event.getSlot() % 9;
		int slotY = event.getSlot() / 9;

		Bukkit.broadcast(Component.text("Slot: " + slotX + ", " + slotY, NamedTextColor.GREEN, TextDecoration.ITALIC));

		gui.getScene().fireOnClick(new NodeClickContext(slotX, slotY, event.getClick(), player, event.getHotbarButton()));
	}

}
