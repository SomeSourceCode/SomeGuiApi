package io.github.somesourcecode.someguiapi.scene;

import org.bukkit.inventory.ItemStack;

public interface Background {

	 ItemStack backgroundAt(int layoutX, int layoutY);

	 static Background fill(GuiItem item) {
		return (layoutX, layoutY) -> item.asItemStack();
	 }

	 static Background checkerboard(GuiItem primary, GuiItem secondary) {
		 return (layoutX, layoutY) -> (layoutX + layoutY) % 2 == 0 ? primary.asItemStack() : secondary.asItemStack();
	 }

}
