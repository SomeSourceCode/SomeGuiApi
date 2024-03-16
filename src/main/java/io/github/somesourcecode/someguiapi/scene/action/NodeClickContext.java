package io.github.somesourcecode.someguiapi.scene.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NodeClickContext {

	private final int slotX;
	private final int slotY;

	private final ClickType type;

	private final Player player;

	private final int hotBarButton;

	private boolean consumed;

	public NodeClickContext(int x, int y, ClickType type, Player player, int hotBarButton) {
		this.slotX = x;
		this.slotY = y;
		this.type = type;
		this.player = player;
		this.hotBarButton = hotBarButton;
	}

	public int getSlotX() {
		return slotX;
	}

	public int getSlotY() {
		return slotY;
	}

	public ClickType getType() {
		return type;
	}

	public boolean isLeftClick() {
		return type.isLeftClick();
	}

	public boolean isRightClick() {
		return type.isRightClick();
	}

	public boolean isShiftClick() {
		return type.isShiftClick();
	}

	public boolean isHotBarClick() {
		return hotBarButton != -1;
	}

	public Player getPlayer() {
		return player;
	}

	public int getHotBarButton() {
		return hotBarButton;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void consume() {
		consumed = true;
	}

}
