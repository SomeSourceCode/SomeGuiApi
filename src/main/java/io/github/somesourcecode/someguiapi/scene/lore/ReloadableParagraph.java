package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;

/**
 * Represents a paragraph whose content depends on the context in which it is rendered.
 */
public interface ReloadableParagraph extends Paragraph {

	/**
	 * Reloads the paragraph with the given context.
	 * @param context the context in which the paragraph is rendered
	 */
	void reload(RenderContext context);

}
