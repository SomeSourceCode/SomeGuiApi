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

package io.github.somesourcecode.someguiapi.scene.gui.type;

import io.github.somesourcecode.someguiapi.scene.DirtyFlag;
import io.github.somesourcecode.someguiapi.scene.Node;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import io.github.somesourcecode.someguiapi.scene.gui.StaticGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.view.FurnaceView;

/**
 * The base class for furnace GUIs.
 *
 * @since 3.0.0
 */
public abstract class FurnaceGuiBase extends StaticGui {

	/**
	 * The input slot index (top).
	 *
	 * @since 3.0.0
	 */
	public static final int INPUT_SLOT = 0;
	/**
	 * The fuel slot index (bottom).
	 *
	 * @since 3.0.0
	 */
	public static final int FUEL_SLOT = 1;
	/**
	 * The result slot index (right).
	 *
	 * @since 3.0.0
	 */
	public static final int RESULT_SLOT = 2;

	private final InventoryType furnaceInventoryType;

	private int burnProgress = 0;
	private int maxBurnProgress = 100;
	private int cookProgress = 0;
	private int maxCookProgress = 100;

	/**
	 * Initializes the GUI with the specified furnace inventory type.
	 *
	 * @param furnaceInventoryType the inventory type
	 */
	protected FurnaceGuiBase(InventoryType furnaceInventoryType) {
		super(INPUT_SLOT, FUEL_SLOT, RESULT_SLOT);

		this.furnaceInventoryType = furnaceInventoryType;
	}

	/**
	 * Returns the node in the input slot (top).
	 *
	 * @return the node in the input slot
	 */
	public Node getInputSlot() {
		return getSlot(INPUT_SLOT);
	}

	/**
	 * Sets the item in the input slot (top).
	 *
	 * @param node the node to set
	 */
	public void setInputSlot(Node node) {
		setSlot(INPUT_SLOT, node);
	}

	/**
	 * Returns the node in the fuel slot (bottom).
	 *
	 * @return the node in the fuel slot
	 */
	public Node getFuelSlot() {
		return getSlot(FUEL_SLOT);
	}

	/**
	 * Sets the item in the fuel slot (bottom).
	 *
	 * @param node the node to set
	 */
	public void setFuelSlot(Node node) {
		setSlot(FUEL_SLOT, node);
	}

	/**
	 * Returns the node in the result slot (right).
	 *
	 * @return the node in the result slot
	 */
	public Node getResultSlot() {
		return getSlot(RESULT_SLOT);
	}

	/**
	 * Sets the item in the result slot (right).
	 *
	 * @param node the node to set
	 */
	public void setResultSlot(Node node) {
		setSlot(RESULT_SLOT, node);
	}

	/**
	 * Returns the burn progress.
	 * It will be displayed as a portion of the max burn progress.
	 *
	 * @return the burn progress
	 */
	public int getBurnProgress() {
		return burnProgress;
	}

	/**
	 * Sets the burn progress. This must be between 0 and
	 * the max burn progress (inclusive).
	 * It will be displayed as a portion of the max burn progress.
	 *
	 * @param burnProgress the burn progress
	 */
	public void setBurnProgress(int burnProgress) {
		this.burnProgress = burnProgress;
		setDirtyFlag(DirtyFlag.GUI_FURNACE_BURN_TIME);
		update();
	}

	/**
	 * Returns the max burn progress. The burn progress will be
	 * displayed as a portion of this value.
	 *
	 * @return the max burn progress
	 */
	public int getMaxBurnProgress() {
		return maxBurnProgress;
	}

	/**
	 * Sets the max burn progress. The burn progress will be
	 * displayed as a portion of this value.
	 *
	 * @param maxBurnProgress the max burn progress
	 */
	public void setMaxBurnProgress(int maxBurnProgress) {
		this.maxBurnProgress = maxBurnProgress;
		setDirtyFlag(DirtyFlag.GUI_FURNACE_BURN_TIME);
		update();
	}

	private void updateBurnTime() {
		for (HumanEntity viewer : getViewers()) {
			if (!(viewer.getOpenInventory() instanceof FurnaceView furnaceView)) {
				continue;
			}
			furnaceView.setBurnTime(burnProgress, maxBurnProgress);
		}
		clearDirtyFlag(DirtyFlag.GUI_FURNACE_BURN_TIME);
	}

	/**
	 * Returns the cook progress. It will be displayed as a portion of the
	 * max cook progress.
	 *
	 * @return the cook progress
	 */
	public int getCookProgress() {
		return cookProgress;
	}

	/**
	 * Sets the cook progress. This must be between 0 and
	 * the max cook progress (inclusive). It will be displayed as a portion of the
	 * max cook progress.
	 *
	 * @param cookProgress the cook progress
	 */
	public void setCookProgress(int cookProgress) {
		this.cookProgress = cookProgress;
		setDirtyFlag(DirtyFlag.GUI_FURNACE_COOK_TIME);
		update();
	}

	/**
	 * Returns the max cook progress. The cook progress will be
	 * displayed as a portion of this value.
	 *
	 * @return the max cook progress
	 */
	public int getMaxCookProgress() {
		return maxCookProgress;
	}

	/**
	 * Sets the max cook progress. The cook progress will be
	 * displayed as a portion of this value.
	 *
	 * @param maxCookProgress the max cook progress
	 */
	public void setMaxCookProgress(int maxCookProgress) {
		this.maxCookProgress = maxCookProgress;
		setDirtyFlag(DirtyFlag.GUI_FURNACE_COOK_TIME);
		update();
	}

	private void updateCookTime() {
		for (HumanEntity viewer : getViewers()) {
			if (!(viewer.getOpenInventory() instanceof FurnaceView furnaceView)) {
				continue;
			}
			furnaceView.setCookTime(cookProgress, maxCookProgress);
		}
		clearDirtyFlag(DirtyFlag.GUI_FURNACE_COOK_TIME);
	}

	@Override
	public void show(HumanEntity humanEntity) {
		super.show(humanEntity);

		if (isDirty(DirtyFlag.GUI_FURNACE_BURN_TIME)) {
			updateBurnTime();
		}
		if (isDirty(DirtyFlag.GUI_FURNACE_COOK_TIME)) {
			updateCookTime();
		}
	}

	@Override
	public Inventory createInventory() {
		final Inventory inventory = title == null
				? Bukkit.createInventory(null, furnaceInventoryType)
				: Bukkit.createInventory(null, furnaceInventoryType, title);
		Gui.addInventoryMapping(inventory, this);
		return inventory;
	}

}
