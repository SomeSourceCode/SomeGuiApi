package io.github.somesourcecode.someguiapi.util;

import net.kyori.adventure.text.*;

/**
 * Utility class for components.
 */
public class ComponentUtil {

	/**
	 * Copies a component.
	 * @param component the component to copy
	 * @param copyChildren whether to copy the children of the component
	 * @return the copied component
	 */
	public static Component copyComponent(Component component, boolean copyChildren) {
		if (component == null) {
			return null;
		}
		if (component.equals(Component.empty())) {
			return Component.empty();
		}

		Component copy = Component.empty();

		if (component instanceof TextComponent textComponent) {
			copy = Component.text(textComponent.content())
					.color(textComponent.color())
					.decorations(textComponent.decorations());

		} else if (component instanceof TranslatableComponent translatableComponent) {
			copy = Component.translatable(translatableComponent.key(), translatableComponent.fallback())
					.color(translatableComponent.color())
					.decorations(translatableComponent.decorations());

		} else if (component instanceof KeybindComponent keybindComponent) {
			copy = Component.keybind(keybindComponent.keybind())
					.color(component.color())
					.decorations(component.decorations());

		} else if (component instanceof BlockNBTComponent blockNBTComponent) {
			copy = Component.blockNBT(blockNBTComponent.nbtPath(), blockNBTComponent.interpret(), blockNBTComponent.separator(), blockNBTComponent.pos())
					.color(component.color())
					.decorations(component.decorations());

		} else if (component instanceof EntityNBTComponent entityNBTComponent) {
			copy = Component.entityNBT(entityNBTComponent.nbtPath(), entityNBTComponent.selector())
					.color(component.color())
					.decorations(component.decorations());

		} else if (component instanceof StorageNBTComponent storageNBTComponent) {
			copy = Component.storageNBT(storageNBTComponent.nbtPath(), storageNBTComponent.interpret(), storageNBTComponent.separator(), storageNBTComponent.storage())
					.color(component.color())
					.decorations(component.decorations());
		}

		copy = copy.hoverEvent(component.hoverEvent())
				.clickEvent(component.clickEvent());

		if (copyChildren) {
			for (Component child : component.children()) {
				copy = copy.append(copyComponent(child, true));
			}
		}

		return copy;
	}

}
