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

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The context for a GUI render cycle.
 *
 * @since 2.0.0
 */
public class RenderContext extends GuiContext {

	protected long renderStart;

	/**
	 * Constructs a new render context.
	 *
	 * @param gui the GUI
	 * @param scene the scene
	 * @since 2.1.0
	 */
	public RenderContext(Gui gui, Scene scene) {
		super(gui, scene);
		renderStart = System.currentTimeMillis();
	}

	/**
	 * Returns the time when the render cycle started.
	 *
	 * @return the time when the render cycle started
	 * @since 2.1.0
	 */
	public long getRenderStart() {
		return renderStart;
	}

	/**
	 * Returns the time it took to render the GUI
	 * since the render cycle started.
	 *
	 * @return the time it took to render the GUI
	 * @since 2.1.0
	 */
	public long getRenderTime() {
		return System.currentTimeMillis() - renderStart;
	}

}
