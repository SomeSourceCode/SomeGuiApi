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

package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

/**
 * Represents a context of a slot click in a GUI.
 *
 * @since 2.1.0
 */
public class GuiSlotClickContext extends GuiClickContext {

	private final int slotX;
	private final int slotY;

	/**
	 * Constructs a new slot click context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param area the area of the click
	 * @param type the click type
	 * @param hotBarButton the hot bar button
	 * @param whoClicked the human entity that
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @since 2.1.0
	 */
	public GuiSlotClickContext(Gui gui, Scene scene, GuiArea area, ClickType type, int hotBarButton, HumanEntity whoClicked, int slotX, int slotY) {
		super(gui, scene, area, type, hotBarButton, whoClicked);
		this.slotX = slotX;
		this.slotY = slotY;
	}

	/**
	 * Returns the x coordinate of the slot.
	 * This is relative to the top-left corner of the GUI.
	 *
	 * @return the x coordinate of the slot
	 * @since 2.1.0
	 */
	public int getSlotX() {
		return slotX;
	}

	/**
	 * Returns the y coordinate of the slot.
	 * This is relative to the top-left corner of the GUI.
	 *
	 * @return the y coordinate of the slot
	 * @since 2.1.0
	 */
	public int getSlotY() {
		return slotY;
	}

}
