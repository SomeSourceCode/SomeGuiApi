package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;

/**
 * A paragraph that contains a single line.
 */
public class LineParagraph implements Paragraph {

	private Component line;

	/**
	 * Constructs a new line paragraph with the given line.
	 */
	public LineParagraph(Component line) {
		this.line = line;
	}

	/**
	 * Returns the line of the paragraph.
	 * @return the line of the paragraph
	 */
	public Component getLine() {
		return line;
	}

	/**
	 * Sets the line of the paragraph.
	 * @param line the line of the paragraph
	 * @return the paragraph for method chaining
	 */
	public LineParagraph setLine(Component line) {
		this.line = line;
		return this;
	}

	@Override
	public List<Component> getLines() {
		if (line == null) {
			return Collections.emptyList();
		}
		return Collections.singletonList(line);
	}

}
