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

package io.github.somesourcecode.someguiapi.scene.action;

import io.github.somesourcecode.someguiapi.scene.Scene;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

/**
 * Represents the context of a node click.
 * It contains information about the type of click that was performed and the player that clicked.
 */
public class NodeClickContext {

	private final Scene scene;

	private final int slotX;
	private final int slotY;

	private final ClickType type;

	private final HumanEntity whoClicked;

	private final int hotBarButton;

	private boolean consumed;

	/**
	 * Creates a new node click context.
	 * @param scene the scene the click occurred in
	 * @param x the x coordinate of the slot that was clicked
	 * @param y the y coordinate of the slot that was clicked
	 * @param type the type of click that was performed
	 * @param whoClicked the human entity that clicked the node
	 * @param hotBarButton the hot bar button that was clicked
	 */
	public NodeClickContext(Scene scene, int x, int y, ClickType type, HumanEntity whoClicked, int hotBarButton) {
		this.scene = scene;
		this.slotX = x;
		this.slotY = y;
		this.type = type;
		this.whoClicked = whoClicked;
		this.hotBarButton = hotBarButton;
	}

	/**
	 * Returns the scene the click occurred in.
	 * @return the scene the click occurred in
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Returns the x coordinate of the slot that was clicked.
	 * This is the slot's x coordinate in the inventory, and not relative to the nodes bounds.
	 * @return the x coordinate of the slot that was clicked
	 */
	public int getSlotX() {
		return slotX;
	}

	/**
	 * Returns the y coordinate of the slot that was clicked.
	 * This is the slot's y coordinate in the inventory, and not relative to the nodes bounds.
	 * @return the y coordinate of the slot that was clicked
	 */
	public int getSlotY() {
		return slotY;
	}

	/**
	 * Returns the type of click that was performed.
	 * @return the type of click that was performed
	 */
	public ClickType getType() {
		return type;
	}

	/**
	 * Return true if the click was a left click; false otherwise.
	 * This method is equivalent to calling {@code getType().isLeftClick()}.
	 * @return whether the click was a left click
	 */
	public boolean isLeftClick() {
		return type.isLeftClick();
	}

	/**
	 * Return true if the click was a right click; false otherwise.
	 * This method is equivalent to calling {@code getType().isRightClick()}.
	 * @return whether the click was a right click
	 */
	public boolean isRightClick() {
		return type.isRightClick();
	}

	/**
	 * Return true if the click was a shift click; false otherwise.
	 * This method is equivalent to calling {@code getType().isShiftClick()}.
	 * @return whether the click was a shift click
	 */
	public boolean isShiftClick() {
		return type.isShiftClick();
	}

	/**
	 * Return true if the click was a hot bar click; false otherwise.
	 * @return whether the click was a hot bar click
	 */
	public boolean isHotBarClick() {
		return hotBarButton != -1;
	}

	/**
	 * Returns the human entity that clicked the node.
	 * @return the human entity that clicked the node
	 */
	public HumanEntity getWhoClicked() {
		return whoClicked;
	}

	/**
	 * Returns the hot bar button that was clicked.
	 * If the click was not a hot bar click, this method returns -1.
	 * @return the hot bar button that was clicked
	 */
	public int getHotBarButton() {
		return hotBarButton;
	}

	/**
	 * Returns true if the click has been consumed; false otherwise.
	 * @return whether the click has been consumed
	 */
	public boolean isConsumed() {
		return consumed;
	}

	/**
	 * Consumes the click.
	 * Once a click has been consumed, {@link #isConsumed()} will return {@code true}.
	 */
	public void consume() {
		consumed = true;
	}

}
