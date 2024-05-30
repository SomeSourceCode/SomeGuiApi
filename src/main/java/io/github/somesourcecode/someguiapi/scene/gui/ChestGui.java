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

import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.*;
import io.github.somesourcecode.someguiapi.scene.context.GuiRenderContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	public  Inventory createInventory() {
		return title == null ? Bukkit.createInventory(this, rows * 9)
				: Bukkit.createInventory(this, rows * 9, title);
	}

	@Override
	public void show(HumanEntity humanEntity) {
		if (getViewers().contains(humanEntity) && !isDirty()) {
			return;
		}

		final int oldRows = inventory.getSize() / 9;
		ItemStack[] contents = Arrays.copyOf(inventory.getContents(), rows * 9);

		if (isDirty(DirtyFlag.GUI_TITLE) || isDirty(DirtyFlag.GUI_ROWS)) {
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

		GuiRenderContext guiRenderContext = new GuiRenderContext(this, scene);
		fireOnRender(guiRenderContext);
		if (scene != null) {
			scene.fireOnRender(guiRenderContext);
		}

		if (guiRenderContext.isCanceled()) {
			rendering = false;
			return;
		}

		final HashMap<Integer, Pixel> renderOverrides = guiRenderContext.getRenderOverrides();

		if (scene.getRoot() == null) {
			if (scene.getBackground() != null) {
				for (int slot = 0; slot < inventory.getSize(); slot++) {
					final int slotX = slot % 9;
					final int slotY = slot / 9;

					Pixel pixel = renderOverrides.getOrDefault(slot, scene.getBackground().backgroundAt(slotX, slotY));
					inventory.setItem(slot, pixel == null ? null : pixel.renderItemStack(guiRenderContext.copyForPixel(slotX, slotY)));
				}
			}
			return;
		}

		ItemStack[][] pixels = new ItemStack[9][rows];
		Parent root = scene.getRoot();
		root.layout();

		final int rootLayoutX = root.getLayoutX();
		final int rootLayoutY = root.getLayoutY();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				Pixel pixel = renderOverrides.getOrDefault(x + 9 * y, root.renderPixelAt(x - rootLayoutX, y - rootLayoutY));
				if (pixel == null && scene.getBackground() != null) {
					pixel = scene.getBackground().backgroundAt(x, y);
				}
				pixels[x][y] = pixel == null ? null : pixel.renderItemStack(guiRenderContext.copyForPixel(x, y));
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
	 * Fires the onClick event for the node at the given coordinates.
	 * The listeners a called for the clicked node and all of its parents, respectively.
	 *
	 * @param area the area of the click
	 * @param clickType the click type
	 * @param hotbarButton the hot bar button
	 * @param whoClicked the human entity that clicked
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @since 2.1.0
	 */
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slotX, int slotY) {
		if (scene == null) {
			return;
		}
		scene.handleClick(area, clickType, hotbarButton, whoClicked, slotX, slotY);
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
			SceneHelper.setGui(this.scene, null);
		}
		this.scene = scene;
		if (scene != null) {
			SceneHelper.setGui(scene, this);
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
	 * Sets the title of this GUI.
	 *
	 * @param title the title
	 * @since 2.1.0
	 */
	public void setTitle(String title) {
		setTitle(title == null ? null : Component.text(title));
	}

	/**
	 * Resets the title of this GUI. This has the same
	 * effect as setting the title to null.
	 *
	 * @since 2.1.0
	 */
	public void resetTitle() {
		setTitle((Component) null);
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

	@Override
	public @NotNull Inventory getInventory() {
		return inventory;
	}

	@Override
	public List<HumanEntity> getViewers() {
		return new ArrayList<>(inventory.getViewers());
	}

}
