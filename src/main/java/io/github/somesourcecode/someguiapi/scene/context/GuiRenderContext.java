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

package io.github.somesourcecode.someguiapi.scene.context;

import io.github.somesourcecode.someguiapi.scene.Pixel;
import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

import java.util.HashMap;

/**
 * The context for a GUI render cycle.
 *
 * @since 2.1.0
 */
public class GuiRenderContext extends RenderContext implements Cancelable {

	private final HashMap<Integer, Pixel> renderOverrides = new HashMap<>();

	private boolean canceled;

	/**
	 * Constructs a new render context.
	 *
	 * @param gui   the GUI
	 * @param scene the scene
	 * @since 2.1.0
	 */
	public GuiRenderContext(Gui gui, Scene scene) {
		super(gui, scene);
	}

	/**
	 * Sets a render override for a slot. This will override the pixel
	 * that would be rendered at the specified slot.
	 * Pass {@code null} to remove the override.
	 *
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @param pixel the pixel to render
	 * @since 2.1.0
	 */
	public void setRenderOverride(int slotX, int slotY, Pixel pixel) {
		if (slotX < 0 || slotX >= 9 || slotY < 0 || slotY >= 6) {
			throw new IllegalArgumentException("Slot coordinates out of bounds (" + slotX + ", " + slotY + ")");
		}
		final int slot = slotX + 9 * slotY;

		if (pixel == null) {
			renderOverrides.remove(slot);
			return;
		}
		renderOverrides.put(slot, pixel);
	}

	/**
	 * Removes a render override for a slot.
	 * If the slot does not have an override, nothing happens.
	 *
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @since 2.1.0
	 */
	public void removeOverride(int slotX, int slotY) {
		if (slotX < 0 || slotX >= 9 || slotY < 0 || slotY >= 6) {
			return;
		}
		final int slot = slotX + 9 * slotY;
		renderOverrides.remove(slot);
	}

	/**
	 * Clears all render overrides.
	 *
	 * @since 2.1.0
	 */
	public void clearRenderOverrides() {
		renderOverrides.clear();
	}

	/**
	 * Returns the render override for a slot.
	 * If there is no override, {@code null} is returned.
	 *
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @return the render override
	 * @since 2.1.0
	 */
	public Pixel getRenderOverride(int slotX, int slotY) {
		if (slotX < 0 || slotX >= 9 || slotY < 0 || slotY >= 6) {
			return null;
		}
		final int slot = slotX + 9 * slotY;
		return renderOverrides.get(slot);
	}

	/**
	 * Returns a snapshot of the render overrides.
	 * The key is represented as the slot index.
	 *
	 * @return the render overrides
	 * @since 2.1.0
	 */
	public HashMap<Integer, Pixel> getRenderOverrides() {
		return new HashMap<>(renderOverrides);
	}

	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	/**
	 * Copies this context for a different slot.
	 *
	 * @param slotX the x coordinate of the slot
	 * @param slotY the y coordinate of the slot
	 * @return the new context
	 * @since 2.1.0
	 */
	public PixelRenderContext copyForPixel(int slotX, int slotY) {
		PixelRenderContext context = new PixelRenderContext(getGui(), getScene(), slotX, slotY);
		context.renderStart = renderStart;
		return context;
	}

}
