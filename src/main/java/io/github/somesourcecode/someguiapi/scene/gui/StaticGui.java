/*
 * Copyright 2026, SomeSourceCode - MIT License
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

import io.github.somesourcecode.someguiapi.scene.ContentWrapper;
import io.github.somesourcecode.someguiapi.scene.DirtyFlag;
import io.github.somesourcecode.someguiapi.scene.Node;
import io.github.somesourcecode.someguiapi.scene.Pixel;
import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.context.GuiRenderContext;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A GUI with a fixed single slot layout, where each slot is set individually.
 */
public abstract class StaticGui extends NamedGui implements InventoryBased {

	private final ContentWrapper contentWrapper;

	/**
	 * Initializes the GUI with the given slots.
	 *
	 * @param slots the slots
	 */
	protected StaticGui(int... slots) {
		contentWrapper = ContentWrapper.create(this)
				.withSlots(slots)
				.build();
		//setDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	/**
	 * Sets the node for the given slot.
	 *
	 * @param slot the slot
	 * @param node the node to set
	 * @throws IllegalArgumentException if the slot is not part of this GUI
	 */
	public void setSlot(int slot, Node node) {
		contentWrapper.setNode(slot, node);
		setDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	public Node getSlot(int slot) {
		return contentWrapper.getNode(slot);
	}

	@Override
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slot) {
		contentWrapper.handleClick(area, clickType, hotbarButton, whoClicked, slot);
	}

	@Override
	public void show(HumanEntity humanEntity) {
		if (!(humanEntity instanceof Player player)) {
			return;
		}

		if (getViewers().contains(player) && !isDirty()) {
			return;
		}

		final ItemStack[] previousContents = inventory == null ? null : inventory.getContents();

		boolean isNewInv = false;
		if (inventory == null || isDirty(DirtyFlag.GUI_TITLE)) {
			inventory = createInventory();
			isNewInv = true;
		}

		if (isDirty(DirtyFlag.GUI_CONTENT) || previousContents == null) {
			render();
		} else if (isNewInv) {
			inventory.setContents(previousContents);
		}

		humanEntity.openInventory(inventory);

		clearDirtyFlag(DirtyFlag.GUI_TITLE);
		clearDirtyFlag(DirtyFlag.GUI_CONTENT);
	}

	private boolean rendering = false;

	protected void render() {
		if (rendering || inventory == null) {
			return;
		}

		rendering = true;

		final GuiRenderContext guiRenderContext = new GuiRenderContext(this, null);
		fireOnRender(guiRenderContext);

		if (guiRenderContext.isCanceled()) {
			rendering = false;
			return;
		}

		final Map<Integer, Pixel> slotToPixel = contentWrapper.renderSlots(guiRenderContext.getRenderOverrides());
		final ItemStack[] contents = new ItemStack[inventory.getSize()];

		for (int slot = 0; slot < contents.length; slot++) {
			Pixel pixel = slotToPixel.get(slot);
			if (pixel != null) {
				contents[slot] = pixel.renderItemStack(guiRenderContext.copyForPixel(0, 0));
			}
		}

		inventory.setContents(contents);

		clearDirtyFlag(DirtyFlag.GUI_CONTENT);
		rendering = false;
	}

	@Override
	public List<HumanEntity> getViewers() {
		return inventory == null ? Collections.emptyList()
				: List.copyOf(inventory.getViewers());
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

}
