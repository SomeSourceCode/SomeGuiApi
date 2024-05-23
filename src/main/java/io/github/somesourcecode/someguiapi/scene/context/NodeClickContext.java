package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

/**
 * Represents the context of a node click in a GUI.
 *
 * @since 2.0.0
 */
public class NodeClickContext extends GuiSlotClickContext implements Consumable {

	private boolean consumed;

	/**
	 * Constructs a new node click context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param type the click type
	 * @param hotBarButton the hotbar button
	 * @param whoClicked the player who clicked
	 * @param slotX the slot X
	 * @param slotY the slot Y
	 */
	public NodeClickContext(Gui gui, Scene scene, ClickType type, int hotBarButton, HumanEntity whoClicked, int slotX, int slotY) {
		super(gui, scene, type, hotBarButton, whoClicked, slotX, slotY);
	}

	@Override
	public boolean isConsumed() {
		return consumed;
	}

	@Override
	public void consume() {
		this.consumed = true;
	}

}
