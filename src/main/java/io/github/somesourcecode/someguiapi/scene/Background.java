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

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a background for a node or scene.
 */
public interface Background {

	/**
	 * Returns the item stack that should be displayed at the given layout coordinates.
	 * @param renderContext the render context
	 * @param layoutX the x coordinate of the layout
	 * @param layoutY the y coordinate of the layout
	 * @return the item stack that should be displayed at the given layout coordinates
	 */
	 ItemStack backgroundAt(RenderContext renderContext, int layoutX, int layoutY);

	/**
	 * Creates a background that fills the background with the given item.
	 * @param item the item to fill the background with
	 * @return the background
	 */
	 static Background fill(GuiItem item) {
		return (renderContext, layoutX, layoutY) -> item.renderPixel(renderContext);
	 }

	/**
	 * Creates a checkerboard background with the given primary and secondary items.
	 * @param primary the primary item
	 * @param secondary the secondary item
	 * @return the background
	 */
	 static Background checkerboard(GuiItem primary, GuiItem secondary) {
		 return (renderContext, layoutX, layoutY) -> (layoutX + layoutY) % 2 == 0 ? primary.renderPixel(renderContext) : secondary.renderPixel(renderContext);
	 }

}
