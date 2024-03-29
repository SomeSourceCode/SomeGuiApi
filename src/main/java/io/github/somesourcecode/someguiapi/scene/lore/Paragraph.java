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

	static TextParagraph text(Component content) {
		return new TextParagraph(content);
	}

	static LineParagraph line(Component line) {
		return new LineParagraph(line);
	}

	static DividerParagraph divider() {
		return new DividerParagraph();
	}

	static DividerParagraph divider(int length) {
		return new DividerParagraph(length);
	}

	static DividerParagraph divider(int length, NamedTextColor color) {
		return new DividerParagraph(length, color);
	}

	static DividerParagraph divider(int length, NamedTextColor color, boolean bold) {
		return new DividerParagraph(length, color, bold);
	}

	static BlankParagraph blank() {
		return new BlankParagraph();
	}

	static BlankParagraph blank(int space) {
		return new BlankParagraph(space);
	}

	static ContextParagraph context(Function<RenderContext, Paragraph> paragraphGenerator) {
		return new ContextParagraph(paragraphGenerator);
	}

}
