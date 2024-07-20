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

import io.github.somesourcecode.someguiapi.scene.*;
import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.context.GuiRenderContext;
import io.github.somesourcecode.someguiapi.scene.gui.type.ChestGui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A GUI containing a {@link Scene}.
 *
 * @since 3.0.0
 */
public abstract class SceneGui extends NamedGui implements InventoryBased {

	private Scene scene;

	/**
	 * Returns the slot offset of this GUI, which
	 * is the amount of slots that are not part of the
	 * {@link Scene}.
	 * <p>
	 * This returns 0 by default and should be overridden
	 * by subclasses if necessary.
	 *
	 * @return the slot offset
	 */
	protected int getSlotOffset() {
		return 0;
	}

	/**
	 * Returns the width of this GUI.
	 * This won't change during the lifetime of the GUI.
	 *
	 * @return the width
	 * @since 3.0.0
	 */
	public abstract int getWidth();

	/**
	 * Returns the height of this GUI.
	 * This won't change during the lifetime of the GUI,
	 * unless this is a {@link ChestGui}.
	 *
	 * @return the height
	 * @since 3.0.0
	 */
	public abstract int getHeight();

	/**
	 * Returns the {@link Scene} of this GUI.
	 *
	 * @return the scene
	 * @since 3.0.0
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the {@link Scene} of this GUI.
	 *
	 * @param scene the scene
	 * @since 3.0.0
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

	@Override
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slot) {
		if (scene == null) {
			return;
		}
		slot -= getSlotOffset();
		int slotX = slot % getWidth();
		int slotY = slot / getHeight();
		scene.handleClick(area, clickType, hotbarButton, whoClicked, slotX, slotY);
	}

	@Override
	public void show(HumanEntity humanEntity) {
		if (getViewers().contains(humanEntity) && !isDirty()) {
			return;
		}

		ItemStack[] contents = inventory.getContents();

		if (inventory == null || isDirty(DirtyFlag.GUI_TITLE)) {
			inventory = createInventory();
		}

		if (!isDirty(DirtyFlag.GUI_CONTENT)) {
			inventory.setContents(contents);
		} else {
			render();
		}

		clearDirtyFlags();
		humanEntity.openInventory(inventory);
	}

	private boolean rendering = false;

	/**
	 * Renders the GUI.
	 */
	protected void render() {
		if (rendering || inventory == null) {
			return;
		}

		rendering = true;
		Scene scene = getScene();

		if (scene == null || (scene.getRoot() == null && scene.getBackground() == null)) {
			inventory.clear();
			rendering = false;
			return;
		}

		GuiRenderContext guiRenderContext = new GuiRenderContext(this, scene);
		fireOnRender(guiRenderContext);
		scene.fireOnRender(guiRenderContext);

		if (guiRenderContext.isCanceled()) {
			rendering = false;
			return;
		}

		final int slotOffset = getSlotOffset();
		final int width = getWidth();
		final int height = getHeight();

		final HashMap<Integer, Pixel> renderOverrides = guiRenderContext.getRenderOverrides();

		if (scene.getRoot() == null) {
			if (scene.getBackground() != null) {
				for (int slot = 0; slot < inventory.getSize(); slot++) {
					final int slotX = slot % width;
					final int slotY = slot / width;

					Pixel pixel = renderOverrides.getOrDefault(slot, scene.getBackground().backgroundAt(slotX, slotY));
					inventory.setItem(slot + slotOffset, pixel == null ? null : pixel.renderItemStack(guiRenderContext.copyForPixel(slotX, slotY)));
				}
			}
			return;
		}

		ItemStack[][] pixels = new ItemStack[width][height];
		Parent root = scene.getRoot();
		root.layout();

		final int rootLayoutX = root.getLayoutX();
		final int rootLayoutY = root.getLayoutY();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Pixel pixel = renderOverrides.getOrDefault(width * y + x, root.renderPixelAt(x - rootLayoutX, y - rootLayoutY));
				if (pixel == null && scene.getBackground() != null) {
					pixel = scene.getBackground().backgroundAt(x, y);
				}
				pixels[x][y] = pixel == null ? null : pixel.renderItemStack(guiRenderContext.copyForPixel(x, y));
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				inventory.setItem(width * y + x + slotOffset, pixels[x][y]);
			}
		}

		doAdditionalRendering(guiRenderContext);

		clearDirtyFlag(DirtyFlag.GUI_CONTENT);
		rendering = false;
	}

	/**
	 * Performs any additional rendering.
	 * This method is to be overridden by subclasses, if necessary.
	 * @param guiRenderContext the GUI render context
	 */
	protected void doAdditionalRendering(GuiRenderContext guiRenderContext) {

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
	 * @since 3.0.0
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
	 * @since 3.0.0
	 */
	public void requestRender(boolean update) {
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
		if (update) {
			update();
		}
	}

	@Override
	public List<HumanEntity> getViewers() {
		return new ArrayList<>(inventory.getViewers());
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

}
