package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.context.PixelRenderContext;
import io.github.somesourcecode.someguiapi.scene.context.RenderContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.security.PrivilegedAction;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents the text which is shown below the title on hover.
 *
 * @since 2.0.0
 */
public class Lore {

	private final List<Paragraph> paragraphs = new ArrayList<>();

	private final HashMap<TextDecoration, Boolean> decorations = new HashMap<>();

	/**
	 * Adds a decoration override to the lore.
	 * Every line will have the given decoration applied.
	 *
	 * @param decoration the decoration
	 * @param value the value
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore setDecoration(TextDecoration decoration, boolean value) {
		decorations.put(decoration, value);
		return this;
	}

	/**
	 * Removes a decoration override from the lore.
	 *
	 * @param decoration the decoration
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore unsetDecoration(TextDecoration decoration) {
		decorations.remove(decoration);
		return this;
	}

	/**
	 * Sets the paragraphs of the lore.
	 * Previous paragraphs will be overwritten.
	 *
	 * @param paragraphs the paragraphs
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs.clear();
		this.paragraphs.addAll(paragraphs);
		return this;
	}

	/**
	 * Appends a line to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a line paragraph.
	 *
	 * @param line the line to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendLine(Component line) {
		return appendParagraph(Paragraph.line(line));
	}

	/**
	 * Appends a text to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a text paragraph.
	 *
	 * @param text the text to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendText(Component text) {
		return appendParagraph(Paragraph.text(text));
	}

	/**
	 * Appends a text to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a text paragraph.
	 *
	 * @param text the text to append
	 * @param wrapWidth the wrap width
	 * @param wrapType the wrap type
	 * @return the lore for method chaining
	 * @since 2.1.0
	 */
	public Lore appendText(Component text, int wrapWidth, WrapType wrapType) {
		return appendParagraph(Paragraph.text(text)
				.setWrapWidth(wrapWidth)
				.setWrapType(wrapType));
	}

	/**
	 * Appends a context paragraph to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a context paragraph.
	 *
	 * @param paragraphGenerator the paragraph generator
	 * @return the lore for method chaining
	 * @since 2.1.0
	 */
	public Lore appendParagraph(Function<PixelRenderContext, Paragraph> paragraphGenerator) {
		return appendParagraph(Paragraph.context(paragraphGenerator));
	}

	/**
	 * Appends a paragraph to the lore.
	 *
	 * @param paragraph the paragraph to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendParagraph(Paragraph paragraph) {
		paragraphs.add(paragraph);
		return this;
	}

	/**
	 * Appends multiple paragraphs to the lore.
	 *
	 * @param paragraphs the paragraphs to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendParagraphs(Paragraph... paragraphs) {
		for (Paragraph paragraph : paragraphs) {
			appendParagraph(paragraph);
		}
		return this;
	}

	/**
	 * Appends multiple paragraphs to the lore.
	 *
	 * @param paragraphs the paragraphs to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs.addAll(paragraphs);
		return this;
	}

	/**
	 * Appends a blank line to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a blank paragraph.
	 *
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendBlank() {
		return appendParagraph(Paragraph.blank());
	}

	/**
	 * Appends a blank lines to the lore.
	 * The amount of lines is determined by the space parameter.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a blank paragraph.
	 *
	 * @param space the amount of blank lines to append
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendBlank(int space) {
		return appendParagraph(Paragraph.blank(space));
	}

	/**
	 * Appends a divider with the given length and color to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a divider paragraph.
	 *
	 * @param length the length of the divider
	 * @param color the color of the divider
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendDivider(int length, NamedTextColor color) {
		return appendParagraph(Paragraph.divider(length, color));
	}

	/**
	 * Appends a divider with the given length, color and boldness to the lore.
	 * This is a shorthand for {@link #appendParagraph(Paragraph)} with a divider paragraph.
	 *
	 * @param length the length of the divider
	 * @param color the color of the divider
	 * @param bold whether the divider should be bold
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore appendDivider(int length, NamedTextColor color, boolean bold) {
		return appendParagraph(Paragraph.divider(length, color, bold));
	}

	/**
	 * Removes all paragraphs from the lore.
	 *
	 * @return the lore for method chaining
	 * @since 2.0.0
	 */
	public Lore clear() {
		paragraphs.clear();
		return this;
	}

	/**
	 * Generates the lines of the lore. This is used for the rendering process.
	 *
	 * @param context the render context
	 * @return the lines of the lore
	 * @since 2.0.0
	 */
	public List<Component> generateLines(PixelRenderContext context) {
		paragraphs.stream()
				.filter(ReloadableParagraph.class::isInstance)
				.map(ReloadableParagraph.class::cast)
				.forEach(paragraph -> paragraph.reload(context));
		return paragraphs.stream()
				.filter(Objects::nonNull)
				.map(Paragraph::getLines)
				.flatMap(List::stream)
				.map(line -> {
					for (Map.Entry<TextDecoration, Boolean> entry : decorations.entrySet()) {
						line = line.decoration(entry.getKey(), entry.getValue());
					}
					return line;
				})
				.toList();
	}

}
