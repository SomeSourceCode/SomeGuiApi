package io.github.somesourcecode.someguiapi.scene.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * Represents the context of a node click.
 * It contains information about the type of click that was performed and the player that clicked.
 */
public class NodeClickContext {

	private final int slotX;
	private final int slotY;

	private final ClickType type;

	private final Player player;

	private final int hotBarButton;

	private boolean consumed;

	/**
	 * Creates a new node click context.
	 * @param x the x coordinate of the slot that was clicked
	 * @param y the y coordinate of the slot that was clicked
	 * @param type the type of click that was performed
	 * @param player the player that clicked the node
	 * @param hotBarButton the hot bar button that was clicked
	 */
	public NodeClickContext(int x, int y, ClickType type, Player player, int hotBarButton) {
		this.slotX = x;
		this.slotY = y;
		this.type = type;
		this.player = player;
		this.hotBarButton = hotBarButton;
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
	 * Returns the player that clicked the node.
	 * @return the player that clicked the node
	 */
	public Player getPlayer() {
		return player;
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
