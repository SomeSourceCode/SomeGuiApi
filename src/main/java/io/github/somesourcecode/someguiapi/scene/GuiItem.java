/*
 * Copyright 2024, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.action.NodeClickContext;
import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import io.github.somesourcecode.someguiapi.scene.lore.Lore;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

/**
 * Represents an item in a GUI. Width and height are always 1.
 * <p>
 * GuiItems are used to represent singular items in a GUI and
 * provide handlers for click events.
 */
public class GuiItem extends Node {

	private Material material;

	private int index;
	private boolean glow;

	private Component title;
	private Lore lore;

	/**
	 * Constructs a new GuiItem with the specified material.
	 * @param material the material of the item
	 */
	public GuiItem(Material material) {
		this.material = material;
	}

	/**
	 * Constructs a new GuiItem with the specified material and title.
	 * @param material the material of the item
	 * @param title the title of the item
	 */
	public GuiItem(Material material, Component title) {
		this.material = material;
		this.title = title;
	}

	/**
	 * Constructs a new GuiItem with the specified material, title, and lore.
	 * @param material the material of the item
	 * @param title the title of the item
	 * @param lore the lore of the item
	 */
	public GuiItem(Material material, Component title, Lore lore) {
		this.material = material;
		this.title = title;
		this.lore = lore;
	}

	/**
	 * Returns the material of this item.
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material of this item.
	 * @param material the new material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Returns the index of this item.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index of this item.
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Returns whether this item should have a glow effect.
	 * The glow effect is the effect applied to enchanted items.
	 * @return whether this item should glow
	 */
	public boolean isGlow() {
		return glow;
	}

	/**
	 * Sets whether this item should have a glow effect.
	 * The glow effect is the effect applied to enchanted items.
	 * @param glow whether this item should glow
	 */
	public void setGlow(boolean glow) {
		this.glow = glow;
	}

	/**
	 * Returns the title of this item.
	 * @return the title
	 */
	public Component getTitle() {
		return title;
	}

	/**
	 * Sets the title of this item.
	 * @param title the new title
	 */
	public void setTitle(Component title) {
		this.title = title;
	}

	/**
	 * Sets the title of this item.
	 * This method is a shorthand for {@link #setTitle(Component)}.
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = Component.text(title);
	}

	/**
	 * Returns the lore of this item.
	 * @return the lore
	 */
	public Lore getLore() {
		return lore;
	}

	/**
	 * Sets the lore of this item.
	 * @param lore the new lore
	 */
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

	/**
	 * Renders this item as an ItemStack, regardless of the coordinates.
	 * @param renderContext the render context
	 * @return the rendered ItemStack
	 */
	public ItemStack renderPixel(RenderContext renderContext) {
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
			meta.lore(lore.generateLines(renderContext));
		}

		item.setItemMeta(meta);

		return item;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <b>NOTE:</b> This method implementation will always return the ItemStack constructed by
	 * {@link #renderPixel(RenderContext)} ()} if the given coordinates are within the bounds;
	 * null otherwise.
	 */
	@Override
	public ItemStack renderPixelAt(int x, int y, RenderContext renderContext) {
		if (!isVisible()) {
			return null;
		}
		boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
		if (!isInBounds) {
			return null;
		}
		return renderPixel(renderContext);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <b>NOTE:</b>  This method implementation will always return this item if the given coordinates
	 * are within the bounds of this item; null otherwise.
	 */
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

	/**
	 * Constructs a new empty GuiItem builder with the given material.
	 * @param material the material of the item
	 * @return the builder
	 */
	public static Builder create(Material material) {
		return new Builder(material);
	}

	/**
	 * The builder for the GuiItem class. Allows for easy construction of GuiItem objects,
	 * by making use of a fluent API.
	 */
	public static class Builder {

		private final GuiItem item;

		private Builder(Material material) {
			this.item = new GuiItem(material);
		}

		/**
		 * Relocates the item to the given coordinates.
		 * @param x the x coordinate
		 * @param y the y coordinate
		 * @return the builder for method chaining
		 */
		public Builder at(int x, int y) {
			item.relocate(x, y);
			return this;
		}

		/**
		 * Relocates the item to the given x coordinate.
		 * @param x the x coordinate
		 * @return the builder for method chaining
		 */
		public Builder atX(int x) {
			item.setLayoutX(x);
			return this;
		}

		/**
		 * Relocates the item to the given y coordinate.
		 * @param y the y coordinate
		 * @return the builder for method chaining
		 */
		public Builder atY(int y) {
			item.setLayoutY(y);
			return this;
		}

		/**
		 * Sets the material of the item.
		 * @param material the material
		 * @return the builder for method chaining
		 */
		public Builder withMaterial(Material material) {
			item.setMaterial(material);
			return this;
		}

		/**
		 * Sets the index of the item.
		 * @param index the index
		 * @return the builder for method chaining
		 */
		public Builder withIndex(int index) {
			item.setIndex(index);
			return this;
		}

		/**
		 * Sets whether the item should have a glow effect.
		 * @param glow whether the item should glow
		 * @return the builder for method chaining
		 */
		public Builder withGlow(boolean glow) {
			item.setGlow(glow);
			return this;
		}

		/**
		 * Sets the title of the item.
		 * @param title the title
		 * @return the builder for method chaining
		 */
		public Builder withTitle(Component title) {
			item.setTitle(title);
			return this;
		}

		/**
		 * Sets the title of the item.
		 * This method is a shorthand for {@link #withTitle(Component)}.
		 * @param title the title
		 * @return the builder for method chaining
		 */
		public Builder withTitle(String title) {
			item.setTitle(title);
			return this;
		}

		/**
		 * Sets the lore of the item.
		 * @param lore the lore
		 * @return the builder for method chaining
		 */
		public Builder withLore(Lore lore) {
			item.setLore(lore);
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is clicked.
		 * <p>
		 * This consumer will be called when the node is clicked, regardless of the type of click.
		 * @param onClick the consumer that is called when the node is clicked
		 * @return the builder for method chaining
		 */
		public Builder onClick(Consumer<NodeClickContext> onClick) {
			item.setOnClick(onClick);
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is left-clicked.
		 * @param onLeftClick the consumer that is called when the node is left-clicked
		 * @return the builder for method chaining
		 */
		public Builder onLeftClick(Consumer<NodeClickContext> onLeftClick) {
			item.setOnLeftClick(onLeftClick);
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is right-clicked.
		 * @param onRightClick the consumer that is called when the node is right-clicked
		 * @return the builder for method chaining
		 */
		public Builder onRightClick(Consumer<NodeClickContext> onRightClick) {
			item.setOnRightClick(onRightClick);
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is shift-clicked.
		 * @param onShiftClick the consumer that is called when the node is shift-clicked
		 * @return the builder for method chaining
		 */
		public Builder onShiftClick(Consumer<NodeClickContext> onShiftClick) {
			item.setOnShiftClick(onShiftClick);
			return this;
		}

		/**
		 * Sets the consumer that is called when the node receives a hot bar click.
		 * @param onHotBarClick the consumer that is called when the node receives a hot bar click
		 * @return the builder for method chaining
		 */
		public Builder onHotBarClick(Consumer<NodeClickContext> onHotBarClick) {
			item.setOnHotBarClick(onHotBarClick);
			return this;
		}

		/**
		 * Builds the GuiItem object.
		 * @return the GuiItem object
		 */
		public GuiItem build() {
			return item;
		}

	}

}
