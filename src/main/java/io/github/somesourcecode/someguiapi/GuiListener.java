/*
 * Copyright 2024, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

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

		if (gui.getScene() == null) {
			return;
		}

		int slotX = event.getSlot() % 9;
		int slotY = event.getSlot() / 9;

		gui.getScene().fireOnClick(new NodeClickContext(gui.getScene(), slotX, slotY, event.getClick(), player, event.getHotbarButton()));
	}

}
