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

package io.github.somesourcecode.someguiapi.scene.action;

import io.github.somesourcecode.someguiapi.scene.Scene;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * The context in which a pixel is rendered.
 *
 * @since 2.0.0
 */
public class RenderContext {

	private final Gui gui;
	private final Scene scene;

	/**
	 * Constructs a new render context.
	 *
	 * @param gui the gui
	 * @param scene the scene
	 * @since 2.0.0
	 */
	public RenderContext(Gui gui, Scene scene) {
		this.gui = gui;
		this.scene = scene;
	}

	/**
	 * Returns the gui in which the pixel is rendered.
	 *
	 * @return the gui
	 * @since 2.0.0
	 */
	public Gui getGui() {
		return gui;
	}

	/**
	 * Returns the scene in which the pixel is rendered.
	 *
	 * @return the scene
	 * @since 2.0.0
	 */
	public Scene getScene() {
		return scene;
	}

}
