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

import io.github.somesourcecode.someguiapi.scene.context.RenderContext;
import io.github.somesourcecode.someguiapi.scene.lore.Lore;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * An instance of Pixel contains all the information needed to render a single item in a GUI.
 *
 * @since 2.0.0
 */
public class Pixel {

	/**
	 * Creates an empty pixel. Empty pixels will be rendered as air.
	 *
	 * @return the empty pixel
	 * @since 2.0.0
	 */
	public static Pixel empty() {
		return new Pixel(Material.AIR);
	}

	/**
	 * Creates a pixel with the specified material and empty title and lore.
	 *
	 * @param material the material
	 * @return the blank pixel
	 * @since 2.0.0
	 */
	public static Pixel blank(Material material) {
		return new Pixel(material, Component.empty());
	}

	/**
	 * Creates a pixel with the specified material.
	 *
	 * @param material the material
	 * @return the pixel
	 * @since 2.0.0
	 */
	public static Pixel of(Material material) {
		return new Pixel(material);
	}

	private Material material;

	private Component title;
	private Lore lore;

	private int index = 1;
	private boolean glow = false;

	/**
	 * Constructs a new empty pixel.
	 *
	 * @since 2.0.0
	 */
	public Pixel() {

	}

	/**
	 * Constructs a new pixel with the specified material.
	 *
	 * @param material the material
	 * @since 2.0.0
	 */
	public Pixel(Material material) {
		this.material = material;
	}

	/**
	 * Constructs a new pixel with the specified material and title.
	 *
	 * @param material the material
	 * @param index the index
	 * @since 2.0.0
	 */
	public Pixel(Material material, int index) {
		this.material = material;
		this.index = index;
	}

	/**
	 * Constructs a new pixel with the specified material and title.
	 *
	 * @param material the material
	 * @param index the index
	 * @param title the title
	 * @since 2.0.0
	 */
	public Pixel(Material material, int index, Component title) {
		this.material = material;
		this.index = index;
		this.title = title;
	}

	/**
	 * Constructs a new pixel with the specified material, index, title, and lore.
	 *
	 * @param material the material
	 * @param index the index
	 * @param title the title
	 * @param lore the lore
	 * @since 2.0.0
	 */
	public Pixel(Material material, int index, Component title, Lore lore) {
		this.material = material;
		this.index = index;
		this.title = title;
		this.lore = lore;
	}

	/**
	 * Constructs a new pixel with the specified material, index, title, lore, and glow.
	 *
	 * @param material the material
	 * @param index the index
	 * @param title the title
	 * @param lore the lore
	 * @param glow whether the pixel should glow
	 * @since 2.0.0
	 */
	public Pixel(Material material, int index, Component title, Lore lore, boolean glow) {
		this.material = material;
		this.index = index;
		this.title = title;
		this.lore = lore;
		this.glow = glow;
	}

	/**
	 * Constructs a new pixel with the specified material and title.
	 *
	 * @param material the material
	 * @param title the title
	 * @since 2.0.0
	 */
	public Pixel(Material material, Component title) {
		this.material = material;
		this.title = title;
	}

	/**
	 * Constructs a new pixel with the specified material, title, and lore.
	 *
	 * @param material the material
	 * @param title the title
	 * @param lore the lore
	 * @since 2.0.0
	 */
	public Pixel(Material material, Component title, Lore lore) {
		this.material = material;
		this.title = title;
		this.lore = lore;
	}

	/**
	 * Constructs a new pixel with the specified material, title, lore, and glow.
	 *
	 * @param material the material
	 * @param title the title
	 * @param lore the lore
	 * @param glow whether the pixel should glow
	 * @since 2.0.0
	 */
	public Pixel(Material material, Component title, Lore lore, boolean glow) {
		this.material = material;
		this.title = title;
		this.lore = lore;
		this.glow = glow;
	}

	/**
	 * Returns the material of this pixel.
	 *
	 * @return the material
	 * @since 2.0.0
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material of this pixel.
	 *
	 * @param material the material
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * Returns the title of this pixel.
	 *
	 * @return the title
	 * @since 2.0.0
	 */
	public Component getTitle() {
		return title;
	}

	/**
	 * Sets the title of this pixel.
	 *
	 * @param title the title
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setTitle(Component title) {
		this.title = title;
		return this;
	}

	/**
	 * Returns the lore of this pixel.
	 *
	 * @return the lore
	 * @since 2.0.0
	 */
	public Lore getLore() {
		return lore;
	}

	/**
	 * Sets the lore of this pixel.
	 *
	 * @param lore the lore
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setLore(Lore lore) {
		this.lore = lore;
		return this;
	}

	/**
	 * Returns the index of this pixel.
	 *
	 * @return the index
	 * @since 2.0.0
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index of this pixel.
	 *
	 * @param index the index
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setIndex(int index) {
		this.index = index;
		return this;
	}

	/**
	 * Returns whether this pixel should glow.
	 *
	 * @return whether this pixel should glow
	 * @since 2.0.0
	 */
	public boolean isGlow() {
		return glow;
	}

	/**
	 * Sets whether this pixel should glow.
	 *
	 * @param glow whether this pixel should glow
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setGlow(boolean glow) {
		this.glow = glow;
		return this;
	}

	/**
	 * Returns whether this pixel is empty. A pixel is
	 * considered empty if its material is null or air
	 * or if its index is less than or equal to 0.
	 *
	 * @return whether this pixel is empty
	 * @since 2.0.0
	 */
	public boolean isEmpty() {
		return material == null || material.isAir() || index <= 0;
	}

	/**
	 * Renders this pixel as an ItemStack.
	 *
	 * @param renderContext the render context
	 * @return the rendered ItemStack
	 * @since 2.0.0
	 */
	public ItemStack renderItemStack(RenderContext renderContext) {
		if (isEmpty()) {
			return null;
		}

		ItemStack item = new ItemStack(material);

		if (title != null) {
			item.editMeta(meta -> meta.displayName(title));
		}
		if (lore != null) {
			item.lore(lore.generateLines(renderContext));
		}

		if (index > 0) {
			item.setAmount(index);
		}
		if (glow) {
			item.addUnsafeEnchantment(Enchantment.LUCK, 1);
			item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}

		return item;
	}

}
