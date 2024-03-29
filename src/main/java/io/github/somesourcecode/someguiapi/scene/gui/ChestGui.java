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

import io.github.somesourcecode.someguiapi.scene.Parent;
import io.github.somesourcecode.someguiapi.scene.Scene;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a GUI that can be shown to players.
 */
public class ChestGui extends Gui implements InventoryHolder {

	private final List<Player> viewers = new ArrayList<>();

	private Component title;
	private int rows;

	private Scene scene;

	/**
	 * Constructs a new ChestGui with the specified title and number of rows.
	 * @param title the title
	 * @param rows the number of rows
	 */
	public ChestGui(Component title, int rows) {
		this.title = title;
		this.rows = rows;

		this.inventory = Bukkit.createInventory(this, rows * 9, title);
	}

	@Override
	public void show(Player player) {
		if (viewers.contains(player)) {
			return;
		}
		player.openInventory(inventory);
		if (scene == null) {
			return;
		}
		if (scene.getRoot() != null) {
			scene.getRoot().layout();
		}
		render();
	}

	private void render() {
		if (scene == null || (scene.getRoot() == null && scene.getBackground() == null)) {
			inventory.clear();
			return;
		}
		if (scene.getRoot() == null) {
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, scene.getBackground().backgroundAt(i % 9, i / 9));
			}
		}

		ItemStack[][] pixels = new ItemStack[9][rows];
		Parent root = scene.getRoot();

		final int rootLayoutX = root.getLayoutX();
		final int rootLayoutY = root.getLayoutY();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				ItemStack pixel = root.pixelAt(x - rootLayoutX, y - rootLayoutY);
				pixels[x][y] = pixel == null ? (scene.getBackground() == null ? null : scene.getBackground().backgroundAt(x, y)) : pixel;
			}
		}

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				inventory.setItem(x + y * 9, pixels[x][y]);
			}
		}
	}

	/**
	 * Returns the {@link Scene} of this GUI.
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the {@link Scene} of this GUI.
	 * @param scene the scene
	 */
	public void setScene(Scene scene) {
		if (this.scene == scene) {
			return;
		}
		if (this.scene != null) {
			this.scene.setGui(null);
		}
		this.scene = scene;
		if (scene != null) {
			scene.setGui(this);
		}
	}

	/**
	 * Returns the title of this GUI.
	 * @return the title
	 */
	public Component getTitle() {
		return title;
	}

	/**
	 * Sets the title of this GUI.
	 * @param title the title
	 */
	public void setTitle(Component title) {
		if (this.title.equals(title)) {
			return;
		}
		this.title = title;
		inventory = Bukkit.createInventory(this, rows * 9, title);
		update();
	}

	/**
	 * Returns the number of rows of this GUI.
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the number of rows of this GUI.
	 * @param rows the number of rows
	 */
	public void setRows(int rows) {
		if (this.rows == rows) {
			return;
		}
		this.rows = rows;
		inventory = Bukkit.createInventory(this, rows * 9, title);
		update();
	}

	@Override
	public @NotNull Inventory getInventory() {
		return inventory;
	}

	@Override
	public List<Player> getViewers() {
		return viewers;
	}

}
