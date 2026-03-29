package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

/**
 * A content layer contains the content that is rendered inside a GUI.
 *
 * @param <T> the type of the GUI that this content layer belongs to
 */
public abstract class ContentLayer<T extends Gui> {

	static {
		ContentLayerHelper.setContentLayerAccessor(new ContentLayerHelper.ContentLayerAccessor() {
			@Override
			public <G extends Gui> void setGui(ContentLayer<G> contentLayer, G gui) {
				contentLayer.setGui(gui);
			}
		});
	}

	private T gui;

	/**
	 * Returns the GUI that this content layer belongs to.
	 *
	 * @return the GUI
	 */
	public T getGui() {
		return gui;
	}

	private void setGui(T gui) {
		this.gui = gui;
	}

	/**
	 * Handles a click on the GUI.
	 *
	 * @param area the area of the GUI that was clicked
	 * @param clickType the type of the click
	 * @param hotbarButton the hotbar button that was clicked, or -1 if not applicable
	 * @param whoClicked the human entity that clicked
	 * @param slot the slot that was clicked
	 * @since 3.0.0
	 */
	public abstract void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slot);

}
