package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;

public interface ReloadableParagraph extends Paragraph {

	void reload(RenderContext context);

}
