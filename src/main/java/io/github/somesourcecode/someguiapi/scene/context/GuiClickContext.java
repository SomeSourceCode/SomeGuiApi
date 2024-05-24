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

	private final ClickType type;
	private final int hotBarButton;

	private final HumanEntity whoClicked;

	/**
	 * Constructs a new click context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @param type the click type
	 * @param hotBarButton the hot bar button
	 * @param whoClicked the human entity that
	 * @since 2.1.0
	 */
	public GuiClickContext(Gui gui, Scene scene, ClickType type, int hotBarButton, HumanEntity whoClicked) {
		super(gui, scene);
		this.type = type;
		this.hotBarButton = hotBarButton;
		this.whoClicked = whoClicked;
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