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
 */
public class Pane extends Region {

	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

}
