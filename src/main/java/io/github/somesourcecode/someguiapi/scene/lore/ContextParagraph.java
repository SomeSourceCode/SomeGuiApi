package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.context.PixelRenderContext;
import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * The basic implementation of a {@link ReloadableParagraph}.
 *
 * @since 2.0.0
 */
public class ContextParagraph implements ReloadableParagraph {

	private Function<? super PixelRenderContext, Paragraph> contentGenerator;
	private Paragraph paragraph;

	/**
	 * Constructs a new empty context paragraph.
	 *
	 * @since 2.0.0
	 */
	public ContextParagraph() {

	}

	/**
	 * Constructs a new context paragraph with the given content generator.
	 *
	 * @param contentGenerator the content generator
	 * @since 2.0.0
	 */
	public ContextParagraph(Function<? super PixelRenderContext, Paragraph> contentGenerator) {
		this.contentGenerator = contentGenerator;
	}

	/**
	 * Sets the content generator of the paragraph.
	 *
	 * @param paragraphGenerator the content generator
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public ContextParagraph setContentGenerator(Function<? super PixelRenderContext, Paragraph> paragraphGenerator) {
		this.contentGenerator = paragraphGenerator;
		return this;
	}

	@Override
	public void reload(PixelRenderContext context) {
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
