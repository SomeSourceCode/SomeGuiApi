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
	private final Node target;

	private boolean consumed;

	/**
	 * Constructs a new node click context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param area the area of the click
	 * @param type the click type
	 * @param hotBarButton the hotbar button
	 * @param whoClicked the player who clicked
	 * @param slotX the slot X
	 * @param slotY the slot Y
	 * @param source the source node
	 * @param target the target node
	 */
	public NodeClickContext(Gui gui, Scene scene, GuiArea area, ClickType type, int hotBarButton, HumanEntity whoClicked, int slotX, int slotY, Node source, Node target) {
		super(gui, scene, area, type, hotBarButton, whoClicked, slotX, slotY);
		this.source = source;
		this.target = target;
	}

	/**
	 * Returns the source node of the click. The source
	 * of the event never changes as it points to the
	 * node that was clicked.
	 * <p>
	 * Given a button is clicked that is inside
	 * a container and this code:
	 * <pre><code>
	 * container.setOnClick(context -> {
	 *     context.getSource(); // Returns the button
	 *     context.getTarget(); // whereas this returns the container
     * })
	 * </code></pre>
	 *
	 * @return the source node
	 * @see #getTarget()
	 * @since 2.1.0
	 */
	public Node getSource() {
		return source;
	}

	/**
	 * Returns the target node of the click. The target
	 * of the event will point to the node that is currently
	 * processing the event.
	 * <p>
	 * Given a button is clicked that is inside
	 * a container and this code:
	 * <pre><code>
	 * container.setOnClick(context -> {
	 *     context.getTarget(); // Returns the container
	 *     context.getSource(); // whereas this returns the button
	 * })
	 * </code></pre>
	 * This method is useful if there are multiple nodes
	 * using the same event handler.
	 *
	 * @return the target node
	 * @see #getSource()
	 * @since 2.1.0
	 */
	public Node getTarget() {
		return target;
	}

	@Override
	public boolean isConsumed() {
		return consumed;
	}

	@Override
	public void consume() {
		this.consumed = true;
	}

	/**
	 * Copies this context for the given target node.
	 *
	 * @param target the new target node
	 * @return the new context
	 */
	public NodeClickContext copyFor(Node target) {
		NodeClickContext newContext = new NodeClickContext(getGui(), getScene(), getArea(), getType(), getHotBarButton(), getWhoClicked(), getSlotX(), getSlotY(), getSource(), target);
		newContext.consumed = consumed;
		return newContext;
	}

}
