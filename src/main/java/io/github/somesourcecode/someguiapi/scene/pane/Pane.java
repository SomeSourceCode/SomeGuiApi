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

package io.github.somesourcecode.someguiapi.scene.pane;

import io.github.somesourcecode.someguiapi.collections.ObservableList;
import io.github.somesourcecode.someguiapi.scene.Node;

/**
 * Pane does not modify the layout of its children in any way.
 * However, it is exposing the children list, so child nodes
 * can be added and removed freely.
 * <p>
 * Layout panes should extend this class.
 * <p>
 * For more complex layouts, use different panes, e.g.
 * {@link io.github.somesourcecode.someguiapi.scene.pane.VBox},
 * {@link io.github.somesourcecode.someguiapi.scene.pane.HBox},
 * {@link io.github.somesourcecode.someguiapi.scene.pane.FlowPane}, etc.
 *
 * @since 1.0.0
 */
public class Pane extends Region {

	/**
	 * Constructs a new pane empty.
	 *
	 * @since 1.0.0
	 */
	public Pane() {
		super();
	}

	/**
	 * Constructs a new pane with the given children.
	 *
	 * @param children the children of the pane
	 * @since 1.0.0
	 */
	public Pane(Node... children) {
		super();
		getChildren().addAll(children);
	}

	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

}
