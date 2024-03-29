package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ContextParagraph implements ReloadableParagraph {

	private Function<RenderContext, Paragraph> contentGenerator;
	private Paragraph paragraph;

	public ContextParagraph() {

	}

	public ContextParagraph(Function<RenderContext, Paragraph> contentGenerator) {
		this.contentGenerator = contentGenerator;
	}

	public ContextParagraph setContentGenerator(Function<RenderContext, Paragraph> paragraphGenerator) {
		this.contentGenerator = paragraphGenerator;
		return this;
	}

	@Override
	public void reload(RenderContext context) {
		if (contentGenerator == null) {
			paragraph = null;
			return;
		}
		this.paragraph = contentGenerator.apply(context);
	}

	@Override
	public List<Component> getLines() {
		if (paragraph == null) {
			return Collections.emptyList();
		}
		return paragraph.getLines();
	}

}
