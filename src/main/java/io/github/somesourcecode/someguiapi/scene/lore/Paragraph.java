package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;
import java.util.function.Function;

/**
 * Represents a paragraph in a lore.
 */
public interface Paragraph {

	/**
	 * Returns a list representing the lines of the paragraph.
	 * @return the lines of the paragraph
	 */
	List<Component> getLines();

	/**
	 * Constructs a new text paragraph with the given content.
	 * @param content the content of the paragraph
	 * @return the text paragraph
	 */
	static TextParagraph text(Component content) {
		return new TextParagraph(content);
	}

	/**
	 * Constructs a new line paragraph with the given line.
	 * @param line the line of the paragraph
	 * @return the line paragraph
	 */
	static LineParagraph line(Component line) {
		return new LineParagraph(line);
	}

	/**
	 * Constructs a new divider paragraph.
	 * @return the divider paragraph
	 */
	static DividerParagraph divider() {
		return new DividerParagraph();
	}

	/**
	 * Constructs a new divider paragraph with the given length.
	 * @param length the length of the divider
	 * @return the divider paragraph
	 */
	static DividerParagraph divider(int length) {
		return new DividerParagraph(length);
	}

	/**
	 * Constructs a new divider paragraph with the given length and color.
	 * @param length the length of the divider
	 * @param color the color of the divider
	 * @return the divider paragraph
	 */
	static DividerParagraph divider(int length, NamedTextColor color) {
		return new DividerParagraph(length, color);
	}

	/**
	 * Constructs a new divider paragraph with the given length, color and boldness.
	 * @param length the length of the divider
	 * @param color the color of the divider
	 * @param bold whether the divider is bold
	 * @return the divider paragraph
	 */
	static DividerParagraph divider(int length, NamedTextColor color, boolean bold) {
		return new DividerParagraph(length, color, bold);
	}

	/**
	 * Constructs a new blank paragraph.
	 * @return the blank paragraph
	 */
	static BlankParagraph blank() {
		return new BlankParagraph();
	}

	/**
	 * Constructs a new blank paragraph with the given amount of blank lines.
	 * @param space the amount of blank lines
	 * @return the blank paragraph
	 */
	static BlankParagraph blank(int space) {
		return new BlankParagraph(space);
	}

	/**
	 * Constructs a new context paragraph with the given paragraph generator.
	 * @param paragraphGenerator the paragraph generator
	 * @return the context paragraph
	 */
	static ContextParagraph context(Function<RenderContext, Paragraph> paragraphGenerator) {
		return new ContextParagraph(paragraphGenerator);
	}

}
