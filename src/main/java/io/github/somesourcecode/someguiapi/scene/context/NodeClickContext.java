package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Node;
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

	private final Node source;

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
	 * @param source the source node
	 */
	public NodeClickContext(Gui gui, Scene scene, ClickType type, int hotBarButton, HumanEntity whoClicked, int slotX, int slotY, Node source) {
		super(gui, scene, type, hotBarButton, whoClicked, slotX, slotY);
		this.source = source;
	}

	/**
	 * Returns the source node of the click. The source
	 * of the event never changes as it points to the
	 * node that was clicked.
	 *
	 * @return the source node
	 * @since 2.1.0
	 */
	public Node getSource() {
		return source;
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
