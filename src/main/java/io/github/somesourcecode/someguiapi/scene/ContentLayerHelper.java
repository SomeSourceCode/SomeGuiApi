package io.github.somesourcecode.someguiapi.scene;

import io.github.somesourcecode.someguiapi.scene.gui.Gui;

/**
 * A helper class for content layers to access internal methods.
 *
 * @since 3.0.0
 */
public class ContentLayerHelper {

	private static ContentLayerAccessor contentLayerAccessor;

	private ContentLayerHelper() {

	}

	public static <T extends Gui> void setGui(ContentLayer<T> contentLayer, T gui) {
		getContentLayerAccessor().setGui(contentLayer, gui);
	}

	public static void setContentLayerAccessor(ContentLayerAccessor accessor) {
		if (contentLayerAccessor != null) {
			throw new IllegalStateException();
		}
		ContentLayerHelper.contentLayerAccessor = accessor;
	}

	public static ContentLayerAccessor getContentLayerAccessor() {
		if (contentLayerAccessor == null) {
			throw new IllegalStateException();
		}
		return contentLayerAccessor;
	}

	public interface ContentLayerAccessor {

		<T extends Gui> void setGui(ContentLayer<T> contentLayer, T gui);

	}

}
