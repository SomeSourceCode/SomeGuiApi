package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * A helper class for scenes to access internal methods.
 *
 * @since 2.0.0
 */
public class SceneHelper {

	private static SceneAccessor sceneAccessor;

	private SceneHelper() {

	}

	public static void setGui(Scene scene, Gui gui) {
		sceneAccessor.setGui(scene, gui);
	}

	public static void setSceneAccessor(final SceneAccessor newAccessor) {
		if (sceneAccessor != null) {
			throw new IllegalStateException();
		}
		sceneAccessor = newAccessor;
	}

	public static SceneAccessor getSceneAccessor() {
		if (sceneAccessor == null) {
			throw new IllegalStateException();
		}
		return sceneAccessor;
	}

	public interface SceneAccessor {

		void setGui(Scene scene, Gui gui);

	}

}
