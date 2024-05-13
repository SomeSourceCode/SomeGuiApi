package io.github.somesourcecode.someguiapi.scene;

/**
 * A helper class for nodes to access internal methods.
 *
 * @since 2.0.0
 */
public class NodeHelper {

	private static NodeAccessor nodeAccessor;

	private NodeHelper() {

	}

	public static void setScene(Node node, Scene scene) {
		nodeAccessor.setScene(node, scene);
	}

	public static void setParent(Node node, Parent parent) {
		nodeAccessor.setParent(node, parent);
	}

	public static void setNodeAccessor(final NodeAccessor newAccessor) {
		if (nodeAccessor != null) {
			throw new IllegalStateException();
		}
		nodeAccessor = newAccessor;
	}

	public static NodeAccessor getNodeAccessor() {
		if (nodeAccessor == null) {
			throw new IllegalStateException();
		}
		return nodeAccessor;
	}

	public interface NodeAccessor {

		void setScene(Node node, Scene scene);

		void setParent(Node node, Parent parent);

	}

}
