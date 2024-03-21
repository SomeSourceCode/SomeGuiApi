package io.github.somesourcecode.someguiapi.scene;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a background for a node or scene.
 */
public interface Background {

	/**
	 * Returns the item stack that should be displayed at the given layout coordinates.
	 * @param layoutX the x coordinate of the layout
	 * @param layoutY the y coordinate of the layout
	 * @return the item stack that should be displayed at the given layout coordinates
	 */
	 ItemStack backgroundAt(int layoutX, int layoutY);

	/**
	 * Creates a background that fills the background with the given item.
	 * @param item the item to fill the background with
	 * @return the background
	 */
	 static Background fill(GuiItem item) {
		return (layoutX, layoutY) -> item.asItemStack();
	 }

	/**
	 * Creates a checkerboard background with the given primary and secondary items.
	 * @param primary the primary item
	 * @param secondary the secondary item
	 * @return the background
	 */
	 static Background checkerboard(GuiItem primary, GuiItem secondary) {
		 return (layoutX, layoutY) -> (layoutX + layoutY) % 2 == 0 ? primary.asItemStack() : secondary.asItemStack();
	 }

}
