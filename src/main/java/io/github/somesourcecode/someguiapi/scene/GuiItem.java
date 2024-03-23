package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class GuiItem extends Node {

	private Material material;

	private int index;
	private boolean glow;

	private Component title;
	private Lore lore;

	public ItemStack asItemStack() {
		if (material == null) {
			throw new IllegalStateException("Material is not set");
		}
		if (material.isAir()) {
			throw new IllegalStateException("Material cannot be AIR");
		}
		ItemStack item = new ItemStack(material);

		if (index >= 1 && index <= 64) {
			item.setAmount(index);
		}

		if (glow) {
			item.addUnsafeEnchantment(Enchantment.LUCK, 1);
			item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}

		ItemMeta meta = item.getItemMeta();

		if (title != null) {
			meta.displayName(title);
		}
		if (lore != null) {
			meta.lore(lore.getLines());
		}

		item.setItemMeta(meta);

		return item;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isGlow() {
		return glow;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}

	public Component getTitle() {
		return title;
	}

	public void setTitle(Component title) {
		this.title = title;
	}

	public void setTitle(String title) {
		this.title = Component.text(title);
	}

	public Lore getLore() {
		return lore;
	}

	public void setLore(Lore lore) {
		this.lore = lore;
	}

	@Override
	public int getWidth() {
		return 1;
	}

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public boolean isClipping() {
		return true;
	}

	@Override
	public ItemStack pixelAt(int x, int y) {
		if (!isVisible()) {
			return null;
		}
		boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
		if (!isInBounds) {
			return null;
		}
		return asItemStack();
	}

	@Override
	public Node nodeAt(int x, int y) {
		boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
		return isInBounds ? this : null;
	}

	/* *************************************************************************
	 *                                                                         *
	 *                              Builder                                    *
	 *                                                                         *
	 **************************************************************************/

	public static Builder create(Material material) {
		return new Builder().withMaterial(material);
	}

	public static class Builder {

		private final GuiItem item;

		private Builder() {
			this.item = new GuiItem();
		}

		public Builder at(int x, int y) {
			item.relocate(x, y);
			return this;
		}

		public Builder atX(int x) {
			item.setLayoutX(x);
			return this;
		}

		public Builder atY(int y) {
			item.setLayoutY(y);
			return this;
		}

		public Builder withMaterial(Material material) {
			item.setMaterial(material);
			return this;
		}

		public Builder withIndex(int index) {
			item.setIndex(index);
			return this;
		}

		public Builder withGlow(boolean glow) {
			item.setGlow(glow);
			return this;
		}

		public Builder withTitle(Component title) {
			item.setTitle(title);
			return this;
		}

		public Builder withTitle(String title) {
			item.setTitle(title);
			return this;
		}

		public Builder withLore(Lore lore) {
			item.setLore(lore);
			return this;
		}

		public Builder onClick(Consumer<NodeClickContext> onClick) {
			item.setOnClick(onClick);
			return this;
		}

		public Builder onLeftClick(Consumer<NodeClickContext> onLeftClick) {
			item.setOnLeftClick(onLeftClick);
			return this;
		}

		public Builder onRightClick(Consumer<NodeClickContext> onRightClick) {
			item.setOnRightClick(onRightClick);
			return this;
		}

		public Builder onShiftClick(Consumer<NodeClickContext> onShiftClick) {
			item.setOnShiftClick(onShiftClick);
			return this;
		}

		public Builder onHotBarClick(Consumer<NodeClickContext> onHotBarClick) {
			item.setOnHotBarClick(onHotBarClick);
			return this;
		}

		public GuiItem build() {
			return item;
		}

	}

}
