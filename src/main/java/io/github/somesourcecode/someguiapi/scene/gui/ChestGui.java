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
import io.github.somesourcecode.someguiapi.scene.Parent;
import io.github.somesourcecode.someguiapi.scene.Pixel;
import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a GUI that can be shown to players.
 *
 * @since 1.0.0
 */
public class ChestGui extends Gui implements InventoryHolder {

	private Component title;
	private int rows;

	private Scene scene;

	/**
	 * Constructs a new ChestGui with the specified title and number of rows.
	 *
	 * @param title the title
	 * @param rows the number of rows
	 * @since 1.0.0
	 */
	public ChestGui(Component title, int rows) {
		this.title = title;
		this.rows = rows;

		this.inventory = Bukkit.createInventory(this, rows * 9, title);
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	@Override
	public void show(HumanEntity humanEntity) {
		if (getViewers().contains(humanEntity) && !isDirty()) {
			return;
		}

		ItemStack[] contents = Arrays.copyOf(inventory.getContents(), rows * 9);

		if (isDirty(DirtyFlag.GUI_TITLE) || isDirty(DirtyFlag.GUI_ROWS)) {
			inventory = Bukkit.createInventory(this, rows * 9, title);
		}

		if (!isDirty(DirtyFlag.GUI_CONTENT) && inventory.getSize() <= rows) {
			inventory.setContents(contents);
		} else {
			render();
		}

		clearDirtyFlags();
		humanEntity.openInventory(inventory);
	}

	private boolean rendering = false;

	private void render() {
		if (rendering) {
			return;
		}

		rendering = true;

		if (scene == null || (scene.getRoot() == null && scene.getBackground() == null)) {
			inventory.clear();
			rendering = false;
			return;
		}

		RenderContext renderContext = new RenderContext(this, scene);
		if (scene.getRoot() == null && scene.getBackground() != null) {
			for (int i = 0; i < inventory.getSize(); i++) {
				Pixel pixel = scene.getBackground().backgroundAt(i % 9, i / 9);
				inventory.setItem(i, pixel == null ? null : pixel.renderItemStack(renderContext));
			}
		}

		ItemStack[][] pixels = new ItemStack[9][rows];
		Parent root = scene.getRoot();
		root.layout();

		final int rootLayoutX = root.getLayoutX();
		final int rootLayoutY = root.getLayoutY();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				Pixel pixel = root.renderPixelAt(x - rootLayoutX, y - rootLayoutY);
				if (pixel == null) {
					pixel = scene.getBackground().backgroundAt(x, y);
				}
				pixels[x][y] = pixel == null ? null : pixel.renderItemStack(renderContext);
			}
		}

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				inventory.setItem(x + y * 9, pixels[x][y]);
			}
		}

		clearDirtyFlag(DirtyFlag.GUI_CONTENT);
		rendering = false;
	}

	/**
	 * Requests a render of this GUI. This will happen automatically
	 * when layout changes. However, this method must be called whenever
	 * a change occurs that can't be picked up by the system, e.g.
	 * the change of a lore.
	 * Note that the display won't be updated until {@link #update()}
	 * is called.
	 *
	 * @see #requestRender(boolean)
	 * @since 2.0.0
	 */
	public void requestRender() {
		requestRender(false);
	}

	/**
	 * Requests a render of this GUI. This will happen automatically
	 * when layout changes. However, this method must be called whenever
	 * a change occurs that can't be picked up by the system, e.g.
	 * the change of a lore.
	 *
	 * @param update whether to update the GUI after requesting the render
	 * @see #requestRender()
	 * @since 2.0.0
	 */
	public void requestRender(boolean update) {
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
		if (update) {
			update();
		}
	}

	/**
	 * Returns the {@link Scene} of this GUI.
	 *
	 * @return the scene
	 * @since 1.0.0
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the {@link Scene} of this GUI.
	 *
	 * @param scene the scene
	 * @since 1.0.0
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
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
		update();
	}

	/**
	 * Returns the title of this GUI.
	 *
	 * @return the title
	 * @since 1.0.0
	 */
	public Component getTitle() {
		return title;
	}

	/**
	 * Sets the title of this GUI.
	 *
	 * @param title the title
	 * @since 1.0.0
	 */
	public void setTitle(Component title) {
		if (this.title.equals(title)) {
			return;
		}
		this.title = title;
		setDirtyFlag(DirtyFlag.GUI_TITLE);
		update();
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
	 * @param rows the number of rows
	 * @since 1.0.0
	 */
	public void setRows(int rows) {
		if (this.rows == rows) {
			return;
		}
		this.rows = rows;
		setDirtyFlag(DirtyFlag.GUI_ROWS);
		update();
	}

	@Override
	public @NotNull Inventory getInventory() {
		return inventory;
	}

	@Override
	public List<HumanEntity> getViewers() {
		return new ArrayList<>(inventory.getViewers());
	}

}
