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
 * The context of a click event in a GUI.
 *
 * @since 2.1.0
 */
public class GuiClickContext extends GuiContext {

	private final GuiArea area;

	private final ClickType type;
	private final int hotBarButton;

	private final HumanEntity whoClicked;

	/**
	 * Constructs a new click context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param area the area of the click
	 * @param type the click type
	 * @param hotBarButton the hot bar button
	 * @param whoClicked the human entity that
	 * @since 2.1.0
	 */
	public GuiClickContext(Gui gui, Scene scene, GuiArea area, ClickType type, int hotBarButton, HumanEntity whoClicked) {
		super(gui, scene);
		this.area = area;
		this.type = type;
		this.hotBarButton = hotBarButton;
		this.whoClicked = whoClicked;
	}

	/**
	 * Returns the area of the click.
	 *
	 * @return the area of the click
	 * @since 2.1.0
	 */
	public GuiArea getArea() {
		return area;
	}

	/**
	 * Returns whether the click was a click
	 * inside the top area of the GUI.
	 *
	 * @return whether the click was a click
	 * @since 2.1.0
	 */
	public boolean isTopClick() {
		return area == GuiArea.TOP;
	}

	/**
	 * Returns whether the click was a click
	 * inside the bottom area of the GUI.
	 *
	 * @return whether the click was a click
	 * @since 2.1.0
	 */
	public boolean isBottomClick() {
		return area == GuiArea.BOTTOM;
	}

	/**
	 * Returns whether the click was a click
	 * outside the area of the GUI.
	 *
	 * @return whether the click was a click
	 * @since 2.1.0
	 */
	public boolean isOutsideClick() {
		return area == GuiArea.OUTSIDE;
	}

	/**
	 * Returns whether the click was a click
	 * inside the area of the GUI.
	 *
	 * @return whether the click was a click
	 * @since 2.1.0
	 */
	public boolean isGuiClick() {
		return area != GuiArea.OUTSIDE;
	}

	/**
	 * Returns the click type.
	 *
	 * @return the click type
	 * @since 2.1.0
	 */
	public ClickType getType() {
		return type;
	}

	/**
	 * Returns whether the click was a left click.
	 *
	 * @return whether the click was a left click
	 * @since 2.1.0
	 */
	public boolean isLeftClick() {
		return type.isLeftClick();
	}

	/**
	 * Returns whether the click was a right click.
	 *
	 * @return whether the click was a right click
	 * @since 2.1.0
	 */
	public boolean isRightClick() {
		return type.isRightClick();
	}

	/**
	 * Returns whether the click was a shift click.
	 *
	 * @return whether the click was a shift click
	 * @since 2.1.0
	 */
	public boolean isShiftClick() {
		return type.isShiftClick();
	}

	/**
	 * Returns whether the click was a hot bar click.
	 *
	 * @return whether the click was a hot bar click
	 * @since 2.1.0
	 */
	public boolean isHotBarClick() {
		return hotBarButton != -1;
	}

	/**
	 * Returns the hot bar button.
	 *
	 * @return the hot bar button, or -1 if
	 * the click was not a hot bar click
	 * @since 2.1.0
	 */
	public int getHotBarButton() {
		return hotBarButton;
	}

	/**
	 * Returns the human entity that clicked the GUI.
	 *
	 * @return the human entity that clicked the GUI
	 * @since 2.1.0
	 */
	public HumanEntity getWhoClicked() {
		return whoClicked;
	}

}
