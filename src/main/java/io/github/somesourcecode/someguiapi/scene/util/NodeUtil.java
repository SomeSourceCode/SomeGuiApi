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

package io.github.somesourcecode.someguiapi.scene.util;

import io.github.somesourcecode.someguiapi.scene.Node;
import io.github.somesourcecode.someguiapi.scene.Parent;
import io.github.somesourcecode.someguiapi.scene.context.NodeClickContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides utility methods for nodes.
 *
 * @since 3.0.0
 */
public class NodeUtil {

	/**
	 * Fires the appropriate click events for the given node and click context. Those include
	 * the general click event, as well as specific events for left clicks, right clicks, shift clicks, and hotbar clicks.
	 *
	 * @param node the node
	 * @param context the click context
	 * @since 3.0.0
	 */
	public static void fireOnClick(final Node node, final NodeClickContext context) {
		if (context == null) {
			return;
		}
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

	/**
	 * Represents the result of a hit-test on the node hierarchy.
	 *
	 * @since 3.0.0
	 */
	public record HitResult(Node hit, List<Node> nodeHierarchy) {}

	/**
	 * Finds the node at the given coordinates and builds the branch of parents
	 * that contain the given hit location. The first element in the node hierarchy is the hit node,
	 * and the last element is the root node.
	 *
	 * @param rootNode the root node to start searching from
	 * @param localX the x coordinate relative to the root
	 * @param localY the y coordinate relative to the root
	 * @return the hit result, or null if no node was hit
	 */
	public static HitResult findHit(Node rootNode, int localX, int localY) {
		final Node clickedNode = rootNode.nodeAt(localX, localY);
		if (clickedNode == null) {
			return null;
		}

		final List<Node> nodeHierarchy = new ArrayList<>();
		nodeHierarchy.add(clickedNode);

		Parent currentParent = clickedNode.getParent();
		int currentLocalX = clickedNode.getLayoutX();
		int currentLocalY = clickedNode.getLayoutY();

		while (currentParent != null) {
			if (currentLocalX >= 0 && currentLocalY >= 0 && currentLocalX < currentParent.getWidth() && currentLocalY < currentParent.getHeight()) {
				nodeHierarchy.add(currentParent);
			}
			currentLocalX += currentParent.getLayoutX();
			currentLocalY += currentParent.getLayoutY();
			currentParent = currentParent.getParent();
		}

		return new HitResult(clickedNode, Collections.unmodifiableList(nodeHierarchy));
	}

}
