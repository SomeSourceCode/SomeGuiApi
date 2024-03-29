package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.function.Function;

public interface Paragraph {

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
