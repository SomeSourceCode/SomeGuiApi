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
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import io.github.somesourcecode.someguiapi.scene.gui.GuiHelper;
import io.github.somesourcecode.someguiapi.scene.storage.Storage;
import io.github.somesourcecode.someguiapi.state.ObjectState;
import io.github.somesourcecode.someguiapi.state.ReadOnlyObjectState;
import io.github.somesourcecode.someguiapi.state.ReadOnlyObjectStateWrapper;
import io.github.somesourcecode.someguiapi.state.SimpleObjectState;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

import java.util.ArrayList;
import java.util.Collections;
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
public class Scene {

	static {
		SceneHelper.setSceneAccessor(new SceneHelper.SceneAccessor() {
			@Override
			public void setGui(Scene scene, Gui gui) {
				scene.setGui(gui);
			}
		});
	}

	@Deprecated(since = "2.1.0", forRemoval = true)
	private final ContextDataHolder dataHolder = new ContextDataHolder();
	private final Storage storage = new Storage();

	private ReadOnlyObjectStateWrapper<Gui> guiStateWrapper;

	private ObjectState<Parent> rootState;
	private ObjectState<Background> backgroundState;

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

	/**
	 * Returns the state wrapper that holds the GUI that this scene is attached to.
	 *
	 * @return the state wrapper that holds the GUI that this scene is attached to
	 * @since 3.0.0
	 */
	private ReadOnlyObjectStateWrapper<Gui> guiStateWrapper() {
		if (guiStateWrapper == null) {
			guiStateWrapper = new ReadOnlyObjectStateWrapper<>();
		}
		return guiStateWrapper;
	}

	/**
	 * Returns a read-only state that holds the GUI that this scene is attached to.
	 *
	 * @return a read-only state that holds the GUI that this scene is attached to
	 * @since 3.0.0
	 */
	public ReadOnlyObjectState<Gui> guiState() {
		return guiStateWrapper().readOnly();
	}

	/**
	 * Returns the GUI that this scene is attached to.
	 *
	 * @return the GUI that this scene is attached to
	 * @since 2.0.0
	 */
	public Gui getGui() {
		return guiStateWrapper == null ? null : guiStateWrapper.get();
	}

	/**
	 * Sets the GUI that this scene is attached to.
	 *
	 * @param gui the GUI that this scene is attached to
	 * @since 2.0.0
	 */
	private void setGui(Gui gui) {
		guiStateWrapper().set(gui);
	}

	/**
	 * Fires the onClick event for the node at the given coordinates.
	 * The listeners a called for the clicked node and all of its parents, respectively.
	 *
	 * @param area the area of the click
	 * @param clickType the click type
	 * @param hotbarButton the hot bar button
	 * @param whoClicked the human entity that clicked
	 * @param x the x coordinate of the slot
	 * @param y the y coordinate of the slot
	 * @since 2.1.0
	 */
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int x, int y) {
		final Parent root = getRoot();
		if (root == null) {
			return;
		}

		final int localX = x - root.getX();
		final int localY = y - root.getY();

		final ArrayList<Node> nodeBranch = new ArrayList<>();

		final Node clickedNode = root.nodeAt(localX, localY);
		if (clickedNode == null) {
			return;
		}
		nodeBranch.add(clickedNode);

		Parent parent = clickedNode.getParent();
		int clickedLocalX = clickedNode.getX();
		int clickedLocalY = clickedNode.getY();
		while (parent != null) {
			if (clickedLocalX >= 0 && clickedLocalY >= 0 && clickedLocalX < parent.getWidth() && clickedLocalY < parent.getHeight()) {
				nodeBranch.add(parent);
			}
			clickedLocalX += parent.getX();
			clickedLocalY += parent.getY();
			parent = parent.getParent();
		}

		NodeClickContext context = new NodeClickContext(getGui(), this, area, clickType, hotbarButton, whoClicked, x, y, clickedNode, clickedNode);

		for (Node node : nodeBranch) {
			context = context.copyFor(node);
			node.fireOnClick(context);
			if (context.isLeftClick()) {
				node.fireOnLeftClick(context);
			}
			if (context.isRightClick()) {
				node.fireOnRightClick(context);
			}
			if (context.isShiftClick()) {
				node.fireOnShiftClick(context);
			}
			if (context.isHotBarClick()) {
				node.fireOnHotBarClick(context);
			}
		}
	}

	/**
	 * Returns the state that holds the root of the scene.
	 *
	 * @return the state that holds the root of the scene
	 * @since 3.0.0
	 */
	public ObjectState<Parent> rootState() {
		if (rootState == null) {
			rootState = new SimpleObjectState<>();
			rootState.observe(() -> {
				final Gui gui = getGui();
				if (gui == null) {
					return;
				}
				GuiHelper.setDirtyFlag(gui, DirtyFlag.GUI_CONTENT);
			});
		}
		return rootState;
	}

	/**
	 * Returns the root of the scene.
	 *
	 * @return the root of the scene
	 * @since 1.0.0
	 */
	public Parent getRoot() {
		return rootState == null ? null : rootState.get();
	}

	/**
	 * Sets the root of the scene.
	 *
	 * @param root the new root of the scene
	 * @since 1.0.0
	 */
	public void setRoot(Parent root) {
		final Parent thisRoot = getRoot();
		if (thisRoot == root) {
			return;
		}
		if (thisRoot != null) {
			NodeHelper.setScene(thisRoot, null);
		}
		setRoot(root);
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
		final Parent root = getRoot();
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
		final Parent root = getRoot();
		if (root == null) {
			return Collections.emptySet();
		}
		return root.lookupAll(selector);
	}

	/**
	 * Returns the state that holds the background of the scene.
	 *
	 * @return the state that holds the background of the scene
	 * @since 3.0.0
	 */
	public ObjectState<Background> backgroundState() {
		if (backgroundState == null) {
			backgroundState = new SimpleObjectState<>();
			backgroundState.observe(() -> {
				final Gui gui = getGui();
				if (gui == null) {
					return;
				}
				GuiHelper.setDirtyFlag(gui, DirtyFlag.GUI_CONTENT);
			});
		}
		return backgroundState;
	}

	/**
	 * Returns the background of the scene.
	 *
	 * @return the background of the scene
	 * @since 1.0.0
	 */
	public Background getBackground() {
		return backgroundState == null ? null : backgroundState.get();
	}

	/**
	 * Sets the background of the scene.
	 *
	 * @param background the new background of the scene
	 * @since 1.0.0
	 */
	public void setBackground(Background background) {
		backgroundState().set(background);
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
