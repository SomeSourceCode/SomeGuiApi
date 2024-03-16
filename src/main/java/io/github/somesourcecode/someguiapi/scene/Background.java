package io.github.somesourcecode.someguiapi.scene;

import org.bukkit.inventory.ItemStack;

public interface Background {

	 ItemStack backgroundAt(int layoutX, int layoutY);

	 static Background staticBackground(GuiItem item) {
		return (layoutX, layoutY) -> item.asItemStack();
	 }

}
