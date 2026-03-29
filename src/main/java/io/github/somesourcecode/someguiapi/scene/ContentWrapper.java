/*
 * Copyright 2026, SomeSourceCode - MIT License
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

import io.github.somesourcecode.someguiapi.scene.context.GuiArea;
import io.github.somesourcecode.someguiapi.scene.context.NodeClickContext;
import io.github.somesourcecode.someguiapi.scene.gui.Gui;
import io.github.somesourcecode.someguiapi.scene.util.NodeUtil;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

import java.util.*;

/**
 * The ContentWrapper class is the container for all content that is mapped to individual slots
 * in a GUI.
 *
 * @since 3.0.0
 */
public class ContentWrapper extends ContentLayer<Gui> {

	private final Gui gui;
	private final List<Integer> slots;
	private final Map<Integer, Node> slotToNode = new HashMap<>();

	private ContentWrapper(Gui gui, List<Integer> slots) {
		if (gui == null) {
			throw new IllegalArgumentException("gui cannot be null");
		}
		if (slots == null || slots.isEmpty()) {
			throw new IllegalArgumentException("slots cannot be null or empty");
		}
		this.gui = gui;
		this.slots = List.copyOf(slots);
	}

	/**
	 * Returns whether this content wrapper contains the given slot.
	 *
	 * @param slot the slot
	 * @return whether this content wrapper contains the given slot
	 * @since 3.0.0
	 */
	public boolean contains(int slot) {
		return slotToNode.containsKey(slot);
	}

	/**
	 * Sets the node to be rendered in the given slot.
	 *
	 * @param slot the slot
	 * @param node the node
	 * @throws IllegalArgumentException if the slot is not part of this content wrapper
	 * @since 3.0.0
	 */
	public void setNode(int slot, Node node) {
		if (!slots.contains(slot)) {
			throw new IllegalArgumentException("Slot " + slot + " is not part of the content wrapper");
		}
		if (node == null) {
			return;
		}
		slotToNode.put(slot, node);
	}

	/**
	 * Returns the node that is rendered in the given slot, or null if no node is set.
	 *
	 * @param slot the slot
	 * @return the node at that slot
	 * @since 3.0.0
	 */
	public Node getNode(int slot) {
		return slotToNode.get(slot);
	}

	/**
	 * Renders the content of the given slot into a Pixel, if a node is set for that slot.
	 * If no node is set for that slot, null is returned.
	 *
	 * @param slot the slot
	 * @return the rendered pixel
	 * @since 3.0.0
	 */
	public Pixel renderSlot(int slot) {
		final Node node = slotToNode.get(slot);
		if (node == null) {
			return null;
		}
		final int localX = -node.getLayoutX();
		final int localY = -node.getLayoutY();
		return node.renderPixelAt(localX, localY);
	}

	/**
	 * Renders the content of all slots into a map of slot to pixel.
	 *
	 * @param renderOverrides a map of render overrides that takes precedence over
	 * the normal rendering process.
	 * @return the rendered slots
	 * @since 3.0.0
	 */
	public Map<Integer, Pixel> renderSlots(Map<Integer, Pixel> renderOverrides) {
		final Map<Integer, Pixel> renderedSlots = new HashMap<>();
		for (int slot : slots) {
			if (!slotToNode.containsKey(slot)) {
				continue;
			}
			if (renderOverrides != null && renderOverrides.containsKey(slot)) {
				renderedSlots.put(slot, renderOverrides.get(slot));
				continue;
			}
			final Pixel renderedPixel = renderSlot(slot);
			if (renderedPixel != null) {
				renderedSlots.put(slot, renderedPixel);
			}
		}
		return renderedSlots;
	}

	/**
	 * Renders the content of all slots into a map of slot to pixel.
	 *
	 * @return the rendered slots
	 * @since 3.0.0
	 */
	public Map<Integer, Pixel> renderSlots() {
		return renderSlots(null);
	}

	@Override
	public void handleClick(GuiArea area, ClickType clickType, int hotbarButton, HumanEntity whoClicked, int slot) {
		final Node rootNode = slotToNode.get(slot);
		if (rootNode == null) {
			return;
		}

		final int localX = -rootNode.getLayoutX();
		final int localY = -rootNode.getLayoutY();

		final NodeUtil.HitResult hitResult = NodeUtil.findHit(rootNode, localX, localY);
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
	 * Looks for any node in the layer that matches the given selector.
	 * If multiple nodes are found, the first one found is returned.
	 * If no node is found, null is returned.
	 *
	 * @param selector the selector
	 * @return the first node on the content graph that matches the selector,
	 * null if no node matches the selector
	 * @see Node#lookup(String)
	 * @since 3.0.0
	 */
	public Node lookup(String selector) {
		for (Node node : slotToNode.values()) {
			if (node == null) {
				continue;
			}
			final Node found = node.lookup(selector);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Looks for all nodes in the layer that match the given selector.
	 * If no node is found, an empty set is returned.
	 *
	 * @param selector the selector
	 * @return a set of nodes that match the selector. This is always non-null and unmodifiable.
	 * @see Node#lookupAll(String)
	 * @since 3.0.0
	 */
	public Set<Node> lookupAll(String selector) {
		final Set<Node> found = new HashSet<>();
		for (Node node : slotToNode.values()) {
			if (node == null) {
				continue;
			}
			found.addAll(node.lookupAll(selector));
		}
		return found;
	}

	/**
	 * Constructs a new content wrapper builder with the given gui
	 *
	 * @param gui the GUI that this content wrapper belongs to
	 * @return a new content wrapper builder
	 * @throws IllegalArgumentException if gui is null
	 * @since 3.0.0
	 */
	public static Builder create(Gui gui) {
		return new Builder(gui);
	}

	/**
	 * The builder for the content wrapper class.
	 *
	 * @since 3.0.0
	 */
	public static class Builder {

		private final Gui gui;
		private final List<Integer> slots = new ArrayList<>();

		private Builder(Gui gui) {
			if (gui == null) {
				throw new IllegalArgumentException("gui cannot be null");
			}
			this.gui = gui;
		}

		/**
		 * Adds the given slot to the content wrapper.
		 *
		 * @param slot the slot
		 * @return this builder for chaining
		 * @since 3.0.0
		 */
		public Builder withSlot(int slot) {
			slots.add(slot);
			return this;
		}

		/**
		 * Adds the given slots to the content wrapper.
		 *
		 * @param slots the slots
		 * @return this builder for chaining
		 * @since 3.0.0
		 */
		public Builder withSlots(int... slots) {
			for (int slot : slots) {
				this.slots.add(slot);
			}
			return this;
		}

		 /**
		  * Adds the given slot range to the content wrapper, including both from and to.
		  *
		  * @param from the starting slot (inclusive)
		  * @param to the ending slot (inclusive)
		  * @return this builder for chaining
		  * @since 3.0.0
		  */
		public Builder withSlotRange(int from, int to) {
			for (int i = from; i <= to; i++) {
				slots.add(i);
			}
			return this;
		}

		/**
		 * Builds the content wrapper with the given slots.
		 *
		 * @return the built content wrapper
		 * @since 3.0.0
		 */
		public ContentWrapper build() {
			return new ContentWrapper(gui, slots);
		}

	}

}
