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

package io.github.somesourcecode.someguiapi.scene.gui.type;

import io.github.somesourcecode.someguiapi.scene.DirtyFlag;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import io.github.somesourcecode.someguiapi.scene.gui.SceneGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * A GUI represented by a hopper inventory.
 *
 * @since 3.0.0
 */
public class HopperGui extends SceneGui {

	/**
	 * Constructs a new HopperGui with the specified title.
	 *
	 * @param title the title
	 * @since 3.0.0
	 */
	public HopperGui(Component title) {
		this.title = title;

		this.inventory = createInventory();
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	/**
	 * Constructs a new HopperGui with the specified title.
	 *
	 * @param title the title
	 * @since 3.0.0
	 */
	public HopperGui(String title) {
		this(title == null ? null : Component.text(title));
	}

	@Override
	public int getWidth() {
		return 5;
	}

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public Inventory createInventory() {
		Inventory inventory = title == null ? Bukkit.createInventory(null, InventoryType.HOPPER)
				: Bukkit.createInventory(null, InventoryType.HOPPER, title);
		Gui.addInventoryMapping(inventory, this);
		return inventory;
	}

}
