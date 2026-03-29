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

import io.github.somesourcecode.someguiapi.scene.context.Context;
import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.context.GuiRenderContext;
import io.github.somesourcecode.someguiapi.scene.context.NodeClickContext;
import io.github.somesourcecode.someguiapi.scene.data.ContextDataHolder;
import io.github.somesourcecode.someguiapi.scene.gui.SceneGui;
import io.github.somesourcecode.someguiapi.scene.storage.Storage;
import io.github.somesourcecode.someguiapi.scene.util.NodeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * The Scene class is the container for all content in a scene graph.
 * The background of the scene is filled as specified by the background property.
 * <p>
 * For content to be rendered the application must specify a root Node by setting the root property.
 *
 * @since 1.0.0
 */
public class Scene extends ContentLayer<SceneGui> {

	@Deprecated(since = "2.1.0", forRemoval = true)
	private final ContextDataHolder dataHolder = new ContextDataHolder();
	private final Storage storage = new Storage();

	private Parent root;
	private Background background;

	private Consumer<? super GuiRenderContext> onRender;

	/**
	 * Constructs a new empty scene.
	 *
	 * @since 1.0.0
	 */
	public Scene() {

	}

	/**
	 * Constructs a new scene with the given root.
	 *
	 * @param root the root of the scene
	 * @since 1.0.0
	 */
	public Scene(Parent root) {
		setRoot(root);
	}

	/**
	 * Returns the data holder of this scene.
	 *
	 * @return the data holder of this scene
	 * @since 2.0.0
	 * @deprecated since 2.1.0 in favor of {@link #getStorage()}
	 */
	@Deprecated(since = "2.1.0", forRemoval = true)
	public ContextDataHolder getDataHolder() {
		return dataHolder;
	}

	/**
	 * Returns the storage of this scene.
	 *
	 * @return the storage of this scene
	 * @since 2.1.0
	 */
	public Storage getStorage() {
		return storage;
	}

	@Override
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slot) {
		final SceneGui gui = getGui();
		if (getGui() == null || root == null) {
			return;
		}

		final int width = gui.getWidth();

		if (width == 0 || slot < 0 || slot >= width * gui.getHeight()) {
			return;
		}

		final int x = slot % width;
		final int y = slot / width;

		final int localX = x - root.getLayoutX();
		final int localY = y - root.getLayoutY();

		final NodeUtil.HitResult hitResult = NodeUtil.findHit(root, localX, localY);
		if (hitResult == null) {
			return;
		}

		final Node clickedNode = hitResult.hit();
		final List<Node> nodeHierarchy = hitResult.nodeHierarchy();

		final NodeClickContext clickContext = new NodeClickContext(gui, null, area, clickType, hotbarButton, whoClicked, 0, 0, clickedNode, clickedNode);
		for (Node node : nodeHierarchy) {
			NodeUtil.fireOnClick(node, clickContext.copyFor(node));
		}
	}

	/**
	 * Returns the root of the scene.
	 *
	 * @return the root of the scene
	 * @since 1.0.0
	 */
	public Parent getRoot() {
		return root;
	}

	/**
	 * Sets the root of the scene.
	 *
	 * @param root the new root of the scene
	 * @since 1.0.0
	 */
	public void setRoot(Parent root) {
		if (this.root == root) {
			return;
		}
		if (this.root != null) {
			NodeHelper.setScene(this.root, null);
		}
		this.root = root;
		if (root != null) {
			NodeHelper.setScene(root, this);
		}
	}

	/**
	 * Looks for any node in the scene that matches the given selector.
	 * If multiple nodes are found, the first one found is returned.
	 * If no node is found, null is returned.
	 *
	 * @param selector the selector
	 * @return the first node on the scene graph that matches the selector,
	 * null if no node matches the selector
	 * @see Node#lookup(String)
	 * @since 2.0.0
	 */
	public Node lookup(String selector) {
		if (root == null) {
			return null;
		}
		return root.lookup(selector);
	}

	/**
	 * Looks for all nodes in the scene that match the given selector.
	 * If no node is found, an empty set is returned.
	 *
	 * @param selector the selector
	 * @return a set of nodes that match the selector. This is always non-null and unmodifiable.
	 * @see Node#lookupAll(String)
	 * @since 2.0.0
	 */
	public Set<Node> lookupAll(String selector) {
		if (root == null) {
			return Collections.emptySet();
		}
		return root.lookupAll(selector);
	}

	/**
	 * Returns the background of the scene.
	 *
	 * @return the background of the scene
	 * @since 1.0.0
	 */
	public Background getBackground() {
		return background;
	}

	/**
	 * Sets the background of the scene.
	 *
	 * @param background the new background of the scene
	 * @since 1.0.0
	 */
	public void setBackground(Background background) {
		this.background = background;
	}

	/**
	 * Returns the consumer that is called when the scene is rendered.
	 *
	 * @return the consumer that is called when the scene is rendered
	 * @since 2.1.0
	 */
	public Consumer<? super GuiRenderContext> getOnRender() {
		return onRender;
	}

	/**
	 * Sets the consumer that is called when the scene is rendered.
	 *
	 * @param onRender the consumer that is called when the scene is rendered
	 * @since 2.1.0
	 */
	public void setOnRender(Consumer<? super GuiRenderContext> onRender) {
		this.onRender = onRender;
	}

	/**
	 * Fires the consumer, set by {@link #setOnRender(Consumer)}, with the specified context.
	 * Catches and logs any exceptions that might be thrown by the consumer.
	 *
	 * @param context the context
	 * @since 2.1.0
	 */
	public void fireOnRender(GuiRenderContext context) {
		fireCallback(onRender, context, "onRender");
	}

	/**
	 * Calls the given callback with the specified context.
	 * If the callback throws an exception, the exception is caught and logged.
	 *
	 * @param callback the callback
	 * @param context the context
	 * @param name the name of the callback
	 * @param <T> the type of the context
	 */
	protected <T extends Context> void fireCallback(Consumer<? super T> callback, T context, String name) {
		if (callback == null) {
			return;
		}

		try {
			callback.accept(context);
		} catch (Exception e) {
			String errorMessage = "An error occurred while calling '" + name + "''";
			Bukkit.getLogger().log(Level.SEVERE, errorMessage, e);
		}
	}

}
