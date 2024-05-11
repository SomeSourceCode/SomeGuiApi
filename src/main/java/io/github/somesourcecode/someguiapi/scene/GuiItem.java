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
import io.github.somesourcecode.someguiapi.scene.lore.Lore;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.function.Consumer;

/**
 * Represents an item in a GUI. Width and height are always 1.
 * <p>
 * GuiItems are used to represent singular items in a GUI and
 * provide handlers for click events.
 * @since 1.0.0
 */
public class GuiItem extends Node {

	private final Pixel pixel;

	/**
	 * Constructs a new GuiItem with an empty pixel.
	 * @since 2.0.0
	 */
	public GuiItem() {
		this.pixel = Pixel.empty();
	}

	/**
	 * Constructs a new GuiItem with the specified material.
	 * @param material the material of the item
	 * @since 1.0.0
	 */
	public GuiItem(Material material) {
		this.pixel = new Pixel(material);
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
	 * Returns the material of this item.
	 * @return the material
	 * @since 1.0.0
	 */
	public Material getMaterial() {
		return pixel.getMaterial();
	}

	/**
	 * Sets the material of this item.
	 * @param material the material
	 * @since 1.0.0
	 */
	public void setMaterial(Material material) {
		pixel.setMaterial(material);
	}

	/**
	 * Returns the title of this item.
	 * @return the title
	 * @since 1.0.0
	 */
	public Component getTitle() {
		return pixel.getTitle();
	}

	/**
	 * Sets the title of this item.
	 * @param title the title
	 * @since 1.0.0
	 */
	public void setTitle(Component title) {
		pixel.setTitle(title);
	}

	/**
	 * Returns the lore of this item.
	 * @return the lore
	 * @since 2.0.0
	 */
	public Lore getLore() {
		return pixel.getLore();
	}

	/**
	 * Sets the lore of this item.
	 * @param lore the lore
	 * @since 2.0.0
	 */
	public void setLore(Lore lore) {
		pixel.setLore(lore);
	}

	/**
	 * Returns the index of this item.
	 * @return the index
	 * @since 1.0.0
	 */
	public int getIndex() {
		return pixel.getIndex();
	}

	/**
	 * Sets the index of this item.
	 * @param index the index
	 * @since 1.0.0
	 */
	public void setIndex(int index) {
		pixel.setIndex(index);
	}

	/**
	 * Returns whether this item should glow.
	 * @return whether this item should glow
	 * @since 1.0.0
	 */
	public boolean isGlow() {
		return pixel.isGlow();
	}

	/**
	 * Sets whether this item should glow.
	 * @param glow whether this item should glow
	 * @since 1.0.0
	 */
	public void setGlow(boolean glow) {
		pixel.setGlow(glow);
	}

	/**
	 * Returns the pixel representation of this item.
	 * @return the rendered ItemStack
	 * @since 2.0.0
	 */
	public Pixel getPixel() {
		return isVisible() ? pixel : null;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <b>NOTE:</b> This method implementation will always return the ItemStack constructed by
	 * {@link #getPixel()} if the given coordinates are within the bounds;
	 * null otherwise.
	 */
	@Override
	public Pixel renderPixelAt(int x, int y) {
		if (!isVisible()) {
			return null;
		}
		boolean isInBounds = x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
		if (!isInBounds) {
			return null;
		}
		return getPixel();
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
	 * Constructs a new empty GuiItem builder with the given pixel.
	 * @param pixel the pixel of the item
	 * @return the builder
	 * @since 2.0.0
	 */
	public static Builder create(Pixel pixel) {
		return new Builder(pixel);
	}

	/**
	 * Constructs a new empty GuiItem builder with the given material.
	 * @param material the material of the item
	 * @return the builder
	 * @since 1.0.0
	 */
	public static Builder create(Material material) {
		return new Builder(material);
	}

	/**
	 * The builder for the GuiItem class. Allows for easy construction of GuiItem objects,
	 * by making use of a fluent API.
	 */
	public static class Builder {

		private int layoutX;
		private int layoutY;

		private Consumer<NodeClickContext> onClick;
		private Consumer<NodeClickContext> onLeftClick;
		private Consumer<NodeClickContext> onRightClick;
		private Consumer<NodeClickContext> onShiftClick;
		private Consumer<NodeClickContext> onHotBarClick;

		private Material material;
		private Component title;
		private Lore lore;
		private int index = 1;
		private boolean glow = false;

		private Builder(Pixel pixel) {
			this.material = pixel.getMaterial();
			this.title = pixel.getTitle();
			this.lore = pixel.getLore();
			this.index = pixel.getIndex();
			this.glow = pixel.isGlow();
		}

		private Builder(Material material) {
			this.material = material;
		}

		/**
		 * Relocates the item to the given coordinates.
		 * @param x the x coordinate
		 * @param y the y coordinate
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder at(int x, int y) {
			layoutX = x;
			layoutY = y;
			return this;
		}

		/**
		 * Relocates the item to the given x coordinate.
		 * @param x the x coordinate
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder atX(int x) {
			layoutX = x;
			return this;
		}

		/**
		 * Relocates the item to the given y coordinate.
		 * @param y the y coordinate
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder atY(int y) {
			layoutY = y;
			return this;
		}

		/**
		 * Sets the material of the item.
		 * @param material the material
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder withMaterial(Material material) {
			this.material = material;
			return this;
		}

		/**
		 * Sets the title of the item.
		 * @param title the title
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder withTitle(Component title) {
			this.title = title;
			return this;
		}

		/**
		 * Sets the lore of the item.
		 * @param lore the lore
		 * @return the builder for method chaining
		 * @since 2.0.0
		 */
		public Builder withLore(Lore lore) {
			this.lore = lore;
			return this;
		}

		/**
		 * Sets the index of the item.
		 * @param index the index
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder withIndex(int index) {
			this.index = index;
			return this;
		}

		/**
		 * Sets whether the item should glow.
		 * @param glow whether the item should glow
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder withGlow(boolean glow) {
			this.glow = glow;
			return this;
		}

		/**
		 * Sets the glow of the item to true.
		 * @return the builder for method chaining
		 * @since 2.0.0
		 */
		public Builder withGlow() {
			return withGlow(true);
		}

		/**
		 * Sets the glow of the item to false.
		 * @return the builder for method chaining
		 * @since 2.0.0
		 */
		public Builder withoutGlow() {
			return withGlow(false);
		}

		/**
		 * Sets the consumer that is called when the node is clicked.
		 * <p>
		 * This consumer will be called when the node is clicked, regardless of the type of click.
		 * @param onClick the consumer that is called when the node is clicked
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder onClick(Consumer<NodeClickContext> onClick) {
			this.onClick = onClick;
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is left-clicked.
		 * @param onLeftClick the consumer that is called when the node is left-clicked
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder onLeftClick(Consumer<NodeClickContext> onLeftClick) {
			this.onLeftClick = onLeftClick;
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is right-clicked.
		 * @param onRightClick the consumer that is called when the node is right-clicked
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder onRightClick(Consumer<NodeClickContext> onRightClick) {
			this.onRightClick = onRightClick;
			return this;
		}

		/**
		 * Sets the consumer that is called when the node is shift-clicked.
		 * @param onShiftClick the consumer that is called when the node is shift-clicked
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder onShiftClick(Consumer<NodeClickContext> onShiftClick) {
			this.onShiftClick = onShiftClick;
			return this;
		}

		/**
		 * Sets the consumer that is called when the node receives a hot bar click.
		 * @param onHotBarClick the consumer that is called when the node receives a hot bar click
		 * @return the builder for method chaining
		 * @since 1.0.0
		 */
		public Builder onHotBarClick(Consumer<NodeClickContext> onHotBarClick) {
			this.onHotBarClick = onHotBarClick;
			return this;
		}

		/**
		 * Builds the GuiItem object.
		 * @return the GuiItem object
		 * @since 1.0.0
		 */
		public GuiItem build() {
			GuiItem item = new GuiItem();
			item.setMaterial(material);
			item.setTitle(title);
			item.setLore(lore);
			item.setIndex(index);
			item.setGlow(glow);
			item.relocate(layoutX, layoutY);
			item.setOnClick(onClick);
			item.setOnLeftClick(onLeftClick);
			item.setOnRightClick(onRightClick);
			item.setOnShiftClick(onShiftClick);
			item.setOnHotBarClick(onHotBarClick);
			return item;
		}

	}

}
