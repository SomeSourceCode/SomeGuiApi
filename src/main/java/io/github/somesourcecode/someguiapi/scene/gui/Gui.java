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
import io.github.somesourcecode.someguiapi.scene.context.*;
import io.github.somesourcecode.someguiapi.scene.data.ContextDataHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * The base class for GUIs that can be shown to players.
 *
 * @since 1.0.0
 */
public abstract class Gui {

	static {
		GuiHelper.setGuiAccessor(new GuiHelper.GuiAccessor() {
			@Override
			public void setDirtyFlag(Gui gui, DirtyFlag flag) {
				gui.setDirtyFlag(flag);
			}

			@Override
			public void clearDirtyFlag(Gui gui, DirtyFlag flag) {
				gui.clearDirtyFlag(flag);
			}

			@Override
			public void clearDirtyFlags(Gui gui) {
				gui.clearDirtyFlags();
			}
		});
	}

	protected final ContextDataHolder dataHolder = new ContextDataHolder();

	protected final EnumSet<DirtyFlag> dirtyFlags = EnumSet.noneOf(DirtyFlag.class);

	private Consumer<GuiSlotClickContext> onGuiClick;
	private Consumer<GuiClickContext> onOutsideClick;
	private Consumer<GuiCloseContext> onClose;

	private Consumer<GuiRenderContext> onRender;

	protected Inventory inventory;

	/**
	 * Returns the {@link ContextDataHolder} of this GUI.
	 *
	 * @return the data holder
	 * @since 2.0.0
	 */
	public ContextDataHolder getDataHolder() {
		return dataHolder;
	}

	/**
	 * Returns a set of dirty flags that indicate which
	 * parts of the GUI need to be updated.
	 *
	 * @return the dirty flags
	 * @since 2.0.0
	 */
	public EnumSet<DirtyFlag> getDirtyFlags() {
		return EnumSet.copyOf(dirtyFlags);
	}

	/**
	 * Sets the specified dirty flag.
	 *
	 * @param flag the dirty flag
	 * @since 2.0.0
	 */
	protected void setDirtyFlag(DirtyFlag flag) {
		dirtyFlags.add(flag);
	}

	/**
	 * Clears the specified dirty flag.
	 *
	 * @param flag the dirty flag
	 * @since 2.0.0
	 */
	protected void clearDirtyFlag(DirtyFlag flag) {
		dirtyFlags.remove(flag);
	}

	/**
	 * Clears all dirty flags.
	 * @since 2.0.0
	 */
	protected void clearDirtyFlags() {
		dirtyFlags.clear();
	}

	/**
	 * Returns whether this GUI is dirty.
	 *
	 * @return whether this GUI is dirty
	 * @since 2.0.0
	 */
	public final boolean isDirty() {
		return !dirtyFlags.isEmpty();
	}

	/**
	 * Returns whether the specified dirty flag is set.
	 *
	 * @param flag the dirty flag
	 * @return whether the dirty flag is set
	 * @since 2.0.0
	 */
	public final boolean isDirty(DirtyFlag flag) {
		return dirtyFlags.contains(flag);
	}

	/**
	 * Returns the consumer that is called when the GUI is clicked.
	 *
	 * @return the consumer that is called when the GUI is clicked
	 * @since 2.1.0
	 */
	public Consumer<GuiSlotClickContext> getOnGuiClick() {
		return onGuiClick;
	}

	/**
	 * Sets the consumer that is called when the GUI is clicked.
	 *
	 * @param onGuiClick the consumer that is called when the GUI is clicked
	 * @since 2.1.0
	 */
	public void setOnGuiClick(Consumer<GuiSlotClickContext> onGuiClick) {
		this.onGuiClick = onGuiClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnGuiClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnGuiClick(GuiSlotClickContext context) {
		fireCallback(onGuiClick, context, "onGuiClick");
	}

	/**
	 * Returns the consumer that is called when the GUI is clicked outside the GUI.
	 *
	 * @return the consumer that is called when the GUI is clicked outside the GUI
	 * @since 2.1.0
	 */
	public Consumer<GuiClickContext> getOnOutsideClick() {
		return onOutsideClick;
	}

	/**
	 * Sets the consumer that is called when the GUI is clicked outside the GUI.
	 *
	 * @param onOutsideClick the consumer that is called when the GUI is clicked outside the GUI
	 * @since 2.1.0
	 */
	public void setOnOutsideClick(Consumer<GuiClickContext> onOutsideClick) {
		this.onOutsideClick = onOutsideClick;
	}

	/**
	 * Fires the consumer, set by {@link #setOnOutsideClick(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnOutsideClick(GuiClickContext context) {
		fireCallback(onOutsideClick, context, "onOutsideClick");
	}

	/**
	 * Returns the consumer that is called when the GUI is closed.
	 *
	 * @return the consumer that is called when the GUI is closed
	 * @since 2.1.0
	 */
	public Consumer<GuiCloseContext> getOnClose() {
		return onClose;
	}

	/**
	 * Sets the consumer that is called when the GUI is closed.
	 *
	 * @param onClose the consumer that is called when the GUI is closed
	 * @since 2.1.0
	 */
	public void setOnClose(Consumer<GuiCloseContext> onClose) {
		this.onClose = onClose;
	}

	/**
	 * Fires the consumer, set by {@link #setOnClose(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnClose(GuiCloseContext context) {
		fireCallback(onClose, context, "onClose");
	}

	/**
	 * Returns the consumer that is called when the GUI is rendered.
	 *
	 * @return the consumer that is called when the GUI is rendered
	 * @since 2.1.0
	 */
	public Consumer<GuiRenderContext> getOnRender() {
		return onRender;
	}

	/**
	 * Sets the consumer that is called when the GUI is rendered.
	 *
	 * @param onRender the consumer that is called when the GUI is rendered
	 * @since 2.1.0
	 */
	public void setOnRender(Consumer<GuiRenderContext> onRender) {
		this.onRender = onRender;
	}

	/**
	 * Fires the consumer, set by {@link #setOnRender(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnRender(GuiRenderContext context) {
		fireCallback(onRender, context, "onRender");
	}

	/**
	 * Shows this GUI to the specified human entity.
	 *
	 * @param humanEntity the human entity
	 * @since 1.0.0
	 */
	public abstract void show(HumanEntity humanEntity);

	/**
	 * Returns a list of human entities that are currently viewing this GUI.
	 *
	 * @return a list of human entities viewing this GUI
	 * @since 1.0.0
	 */
	public abstract List<HumanEntity> getViewers();

	private boolean updating = false;

	/**
	 * Returns whether this GUI is currently updating.
	 *
	 * @return whether this GUI is updating
	 */
	public boolean isUpdating() {
		return updating;
	}

	/**
	 * Updates the GUI for all viewers.
	 *
	 * @since 1.0.0
	 */
	public void update() {
		if (updating) {
			return;
		}
		updating = true;
		for (HumanEntity viewer : getViewers()) {
			ItemStack cursor = viewer.getItemOnCursor();
			viewer.setItemOnCursor(null);

			show(viewer);

			viewer.setItemOnCursor(cursor);
		}
		updating = false;
	}

	/**
	 * Calls the given callback with the specified context.
	 * If the callback throws an exception, the exception is caught and logged.
	 *
	 * @param callback the callback
	 * @param context the context
	 * @param name the name of the callback
	 * @param <T> the type of the context
	 */
	protected <T extends Context> void fireCallback(Consumer<? super T> callback, T context, String name) {
		if (callback == null) {
			return;
		}

		try {
			callback.accept(context);
		} catch (Exception e) {
			String errorMessage = "An error occurred while calling '" + name + "''";
			if (context instanceof GuiSlotClickContext slotClickContext) {
				errorMessage += " for slot (" + slotClickContext.getSlotX() + ", " + slotClickContext.getSlotY() + ")";
			}
			Bukkit.getLogger().log(Level.SEVERE, errorMessage, e);
		}
	}

}
