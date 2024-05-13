package io.github.somesourcecode.someguiapi.scene.gui;

import io.github.somesourcecode.someguiapi.scene.DirtyFlag;

/**
 * A helper class for GUIs to access internal methods.
 *
 * @since 2.0.0
 */
public class GuiHelper {

	private static GuiAccessor guiAccessor;

	private GuiHelper() {

	}

	public static void setGuiAccessor(final GuiAccessor newAccessor) {
		if (guiAccessor != null) {
			throw new IllegalStateException();
		}
		guiAccessor = newAccessor;
	}

	public static GuiAccessor getGuiAccessor() {
		if (guiAccessor == null) {
			throw new IllegalStateException();
		}
		return guiAccessor;
	}

	public static void setDirtyFlag(Gui gui, DirtyFlag flag) {
		guiAccessor.setDirtyFlag(gui, flag);
	}

	public static void clearDirtyFlag(Gui gui, DirtyFlag flag) {
		guiAccessor.clearDirtyFlag(gui, flag);
	}

	public static void clearDirtyFlags(Gui gui) {
		guiAccessor.clearDirtyFlags(gui);
	}

	public interface GuiAccessor {

		void setDirtyFlag(Gui gui, DirtyFlag flag);

		void clearDirtyFlag(Gui gui, DirtyFlag flag);

		void clearDirtyFlags(Gui gui);

	}

}
