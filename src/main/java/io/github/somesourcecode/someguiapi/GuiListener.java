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

import io.github.somesourcecode.someguiapi.collections.GuiArea;
import io.github.somesourcecode.someguiapi.scene.context.GuiClickContext;
import io.github.somesourcecode.someguiapi.scene.context.GuiCloseContext;
import io.github.somesourcecode.someguiapi.scene.context.GuiSlotClickContext;
import io.github.somesourcecode.someguiapi.scene.context.NodeClickContext;
import io.github.somesourcecode.someguiapi.scene.gui.ChestGui;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A listener that listens for GUI interactions.
 *
 * @since 1.0.0
 */
public class GuiListener implements Listener {

	@EventHandler
	public void onGuiClick(InventoryClickEvent event) {
		if (!(event.getInventory().getHolder() instanceof ChestGui gui)) {
			return;
		}
		event.setCancelled(true);

		final GuiArea area = event.getClickedInventory() == null ? GuiArea.OUTSIDE : event.getClickedInventory().equals(gui.getInventory()) ? GuiArea.TOP : GuiArea.BOTTOM;
		final GuiClickContext guiClickContext = new GuiClickContext(gui, gui.getScene(), area, event.getClick(), event.getHotbarButton(), event.getWhoClicked());

		gui.fireOnClick(guiClickContext);

		if (area == GuiArea.OUTSIDE) {
			gui.fireOnOutsideClick(guiClickContext);
			return;
		}

		int slotX = event.getRawSlot() % 9;
		int slotY = event.getRawSlot() / 9;

		gui.fireOnGuiClick(new GuiSlotClickContext(gui, gui.getScene(), area, event.getClick(), event.getHotbarButton(), event.getWhoClicked(), slotX, slotY));

		gui.handleClick(area, event.getClick(), event.getHotbarButton(), event.getWhoClicked(), slotX, slotY);
	}

	@EventHandler
	public void onGuiClose(InventoryCloseEvent event) {
		if (!(event.getInventory().getHolder() instanceof Gui gui)) {
			return;
		}

		if (!gui.isUpdating()) {
			GuiCloseContext context = new GuiCloseContext(gui, gui instanceof ChestGui chestGui ? chestGui.getScene() : null, event.getPlayer());
			gui.fireOnClose(context);

			if (context.isCanceled()) {
				Bukkit.getScheduler().runTask(JavaPlugin.getProvidingPlugin(GuiListener.class),
						() -> gui.show(event.getPlayer()));
			}
		}
	}

}
