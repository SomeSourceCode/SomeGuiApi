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

package io.github.somesourcecode.someguiapi.scene.gui;

import io.github.somesourcecode.someguiapi.scene.DirtyFlag;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Represents a GUI that can be shown to players.
 *
 * @since 1.0.0
 */
public class ChestGui extends SceneGui {

	private int rows;

	/**
	 * Constructs a new ChestGui with the specified title and number of rows.
	 *
	 * @param title the title
	 * @param rows the number of rows (in range 1-6)
	 * @since 1.0.0
	 */
	public ChestGui(Component title, int rows) {
		if (rows < 1 || rows > 6) {
			throw new IllegalArgumentException("Rows must be between 1 and 6");
		}

		this.title = title;
		this.rows = rows;

		this.inventory = createInventory();
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	/**
	 * Constructs a new ChestGui with the specified title and number of rows.
	 *
	 * @param title the title
	 * @param rows the number of rows (in range 1-6)
	 * @since 2.1.0
	 */
	public ChestGui(String title, int rows) {
		this(title == null ? null : Component.text(title), rows);
	}

	@Override
	public int getWidth() {
		return 9;
	}

	@Override
	public int getHeight() {
		return rows;
	}

	@Override
	public Inventory createInventory() {
		Inventory inventory = getTitle() == null ? Bukkit.createInventory(null, rows * 9)
				: Bukkit.createInventory(null, rows * 9, getTitle());
		Gui.addInventoryMapping(inventory, this);
		return inventory;
	}

	@Override
	public void show(HumanEntity humanEntity) {
		if (getViewers().contains(humanEntity) && !isDirty()) {
			return;
		}

		final int oldRows = inventory.getSize() / 9;
		ItemStack[] contents = Arrays.copyOf(inventory.getContents(), rows * 9);

		if (inventory == null || isDirty(DirtyFlag.GUI_TITLE) || isDirty(DirtyFlag.GUI_ROWS)) {
			inventory = createInventory();
		}

		if (!isDirty(DirtyFlag.GUI_CONTENT) && rows <= oldRows) {
			inventory.setContents(contents);
		} else {
			render();
		}

		clearDirtyFlags();
		humanEntity.openInventory(inventory);
	}

	/**
	 * Returns the number of rows of this GUI.
	 *
	 * @return the number of rows
	 * @since 1.0.0
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the number of rows of this GUI.
	 *
	 * @param rows the number of rows (in range 1-6)
	 * @since 1.0.0
	 */
	public void setRows(int rows) {
		if (rows < 1 || rows > 6) {
			throw new IllegalArgumentException("Rows must be between 1 and 6");
		}
		if (this.rows == rows) {
			return;
		}
		this.rows = rows;
		setDirtyFlag(DirtyFlag.GUI_ROWS);
		update();
	}

}
