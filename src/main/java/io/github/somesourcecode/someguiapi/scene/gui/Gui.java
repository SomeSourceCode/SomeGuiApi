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
import io.github.somesourcecode.someguiapi.scene.data.ContextDataHolder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

import java.util.EnumSet;
import java.util.List;

/**
 * The base class for GUIs that can be shown to players.
 * @since 1.0.0
 */
public abstract class Gui {

	protected final ContextDataHolder dataHolder = new ContextDataHolder();

	protected final EnumSet<DirtyFlag> dirtyFlags = EnumSet.noneOf(DirtyFlag.class);

	protected Inventory inventory;

	/**
	 * Returns the {@link ContextDataHolder} of this GUI.
	 * @return the data holder
	 * @since 2.0.0
	 */
	public ContextDataHolder getDataHolder() {
		return dataHolder;
	}

	public EnumSet<DirtyFlag> getDirtyFlags() {
		return EnumSet.copyOf(dirtyFlags);
	}

	public final void setDirtyFlag(DirtyFlag flag) {
		dirtyFlags.add(flag);
	}

	public final void clearDirtyFlag(DirtyFlag flag) {
		dirtyFlags.remove(flag);
	}

	public final void clearDirtyFlags() {
		dirtyFlags.clear();
	}

	public final boolean isDirty() {
		return !dirtyFlags.isEmpty();
	}

	public final boolean isDirty(DirtyFlag flag) {
		return dirtyFlags.contains(flag);
	}

	/**
	 * Shows this GUI to the specified human entity.
	 * @param humanEntity the human entity
	 * @since 1.0.0
	 */
	public abstract void show(HumanEntity humanEntity);

	/**
	 * Returns a list of human entities that are currently viewing this GUI.
	 * @return a list of human entities viewing this GUI
	 * @since 1.0.0
	 */
	public abstract List<HumanEntity> getViewers();

	/**
	 * Updates the GUI for all viewers.
	 * @since 1.0.0
	 */
	public void update() {
		for (HumanEntity viewer : getViewers()) {
			show(viewer);
		}
	}

}
