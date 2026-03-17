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

import io.github.somesourcecode.someguiapi.scene.context.PixelRenderContext;
import io.github.somesourcecode.someguiapi.scene.lore.Lore;
import io.github.somesourcecode.someguiapi.state.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

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

	private ObjectState<Material> materialState;

	private ObjectState<Component> titleState;
	private ObjectState<Function<? super PixelRenderContext, Component>> titleFunctionState;
	private ObjectState<Lore> loreState;

	private IntegerState indexState;
	private BooleanState glowState;

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
		setMaterial(material);
	}

	/**
	 * Constructs a new pixel with the specified material and title.
	 *
	 * @param material the material
	 * @param index the index
	 * @since 2.0.0
	 */
	public Pixel(Material material, int index) {
		this(material);
		setIndex(index);
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
		this(material, index);
		setTitle(title);
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
		this(material, index, title);
		setLore(lore);
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
		this(material, index, title, lore);
		setGlow(glow);
	}

	/**
	 * Constructs a new pixel with the specified material and title.
	 *
	 * @param material the material
	 * @param title the title
	 * @since 2.0.0
	 */
	public Pixel(Material material, Component title) {
		this(material);
		setTitle(title);
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
		this(material, title);
		setLore(lore);
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
		this(material, title, lore);
		setGlow(glow);
	}

	/**
	 * Returns the state holding the material of this pixel.
	 *
	 * @return the state holding the material
	 * @since 3.0.0
	 */
	public ObjectState<Material> materialState() {
		if (materialState == null) {
			materialState = new SimpleObjectState<>();
		}
		return materialState;
	}

	/**
	 * Returns the material of this pixel.
	 *
	 * @return the material
	 * @since 2.0.0
	 */
	public Material getMaterial() {
		return materialState == null ? null : materialState.get();
	}

	/**
	 * Sets the material of this pixel.
	 *
	 * @param material the material
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setMaterial(Material material) {
		materialState().set(material);
		return this;
	}

	/**
	 * Returns the state holding the title of this pixel.
	 *
	 * @return the state holding the title
	 * @since 3.0.0
	 */
	public ObjectState<Component> titleState() {
		if (titleState == null) {
			titleState = new SimpleObjectState<>();
			titleState.observe(() -> {
				if (titleFunctionState != null) {
					titleFunctionState.set(null);
				}
			});
		}
		return titleState;
	}

	/**
	 * Returns the title of this pixel.
	 *
	 * @return the title
	 * @since 2.0.0
	 */
	public Component getTitle() {
		return titleState == null ? null : titleState.get();
	}

	/**
	 * Sets the title of this pixel.
	 * This method overrides the dynamic title.
	 *
	 * @param title the title
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setTitle(Component title) {
		titleState().set(title);
		return this;
	}

	/**
	 * Returns the state holding the dynamic title of this pixel.
	 * This is a function that generates the title during rendering.
	 *
	 * @return the state holding the dynamic title
	 * @since 3.0.0
	 */
	public ObjectState<Function<? super PixelRenderContext, Component>> dynamicTitleState() {
		if (titleFunctionState == null) {
			titleFunctionState = new SimpleObjectState<>();
			titleFunctionState.observe(() -> {
				if (titleState != null) {
					titleState.set(null);
				}
			});
		}
		return titleFunctionState;
	}

	/**
	 * Returns the dynamic title of this pixel. This is
	 * the function that generates the title during rendering.
	 *
	 * @return the title function
	 * @since 2.1.0
	 */
	public Function<? super PixelRenderContext, Component> getDynamicTitle() {
		return titleFunctionState == null ? null : titleFunctionState.get();
	}

	/**
	 * Sets the title of this pixel to be generated by the
	 * specified function during rendering.
	 * This method overrides the static title.
	 *
	 * @param titleFunction the title function
	 * @return the pixel for method chaining
	 * @since 2.1.0
	 */
	public Pixel setDynamicTitle(Function<? super PixelRenderContext, Component> titleFunction) {
		dynamicTitleState().set(titleFunction);
		return this;
	}

	/**
	 * Returns the state holding the lore of this pixel.
	 *
	 * @return the state holding the lore
	 * @since 3.0.0
	 */
	public ObjectState<Lore> loreState() {
		if (loreState == null) {
			loreState = new SimpleObjectState<>();
		}
		return loreState;
	}

	/**
	 * Returns the lore of this pixel.
	 *
	 * @return the lore
	 * @since 2.0.0
	 */
	public Lore getLore() {
		return loreState == null ? null : loreState.get();
	}

	/**
	 * Sets the lore of this pixel.
	 *
	 * @param lore the lore
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setLore(Lore lore) {
		loreState().set(lore);
		return this;
	}

	/**
	 * Returns the state holding the index of this pixel.
	 *
	 * @return the state holding the index
	 * @since 3.0.0
	 */
	public IntegerState indexState() {
		if (indexState == null) {
			indexState = new SimpleIntegerState(1);
		}
		return indexState;
	}

	/**
	 * Returns the index of this pixel.
	 *
	 * @return the index
	 * @since 2.0.0
	 */
	public int getIndex() {
		return indexState == null ? 1 : indexState.get();
	}

	/**
	 * Sets the index of this pixel.
	 *
	 * @param index the index
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setIndex(int index) {
		indexState().set(index);
		return this;
	}

	/**
	 * Returns the state holding that is {@code true} if this pixel should glow,
	 * {@code false} otherwise.
	 *
	 * @return the state holding whether this pixel should glow
	 */
	public BooleanState glowState() {
		if (glowState == null) {
			glowState = new SimpleBooleanState();
		}
		return glowState;
	}

	/**
	 * Returns whether this pixel should glow.
	 *
	 * @return whether this pixel should glow
	 * @since 2.0.0
	 */
	public boolean isGlow() {
		return glowState != null && glowState.get();
	}

	/**
	 * Sets whether this pixel should glow.
	 *
	 * @param glow whether this pixel should glow
	 * @return the pixel for method chaining
	 * @since 2.0.0
	 */
	public Pixel setGlow(boolean glow) {
		glowState().set(glow);
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
		final Material material = getMaterial();
		final int index = getIndex();
		return material == null || material.isAir() || index <= 0;
	}

	/**
	 * Renders this pixel as an ItemStack.
	 *
	 * @param renderContext the render context
	 * @return the rendered ItemStack
	 * @since 2.0.0
	 */
	public ItemStack renderItemStack(PixelRenderContext renderContext) {
		if (isEmpty()) {
			return null;
		}

		final Material material = getMaterial();
		final Component title = getTitle();
		final Function<? super PixelRenderContext, Component> titleFunction = getDynamicTitle();
		final Lore lore = getLore();
		final int index = getIndex();
		final boolean glow = isGlow();

		final ItemStack item = new ItemStack(material);

		if (title != null) {
			item.editMeta(meta -> meta.displayName(title));
		} else if (titleFunction != null) {
			item.editMeta(meta -> meta.displayName(titleFunction.apply(renderContext)));
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
