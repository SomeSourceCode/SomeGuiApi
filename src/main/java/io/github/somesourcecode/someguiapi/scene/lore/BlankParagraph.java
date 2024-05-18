package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;

/**
 * A paragraph that contains just blank lines.
 *
 * @since 2.0.0
 * @since 2.0.0
 */
public class BlankParagraph implements Paragraph {

	private int space;

	/**
	 * Constructs a new blank paragraph with a single blank line.
	 *
	 * @since 2.0.0
	 */
	public BlankParagraph() {
		this.space = 1;
	}

	/**
	 * Constructs a new blank paragraph with the given amount of blank lines.
	 *
	 * @param space the amount of blank lines
	 * @since 2.0.0
	 */
	public BlankParagraph(int space) {
		this.space = space;
	}

	/**
	 * Returns the amount of blank lines.
	 *
	 * @return the amount of blank lines
	 * @since 2.0.0
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * Sets the amount of blank lines.
	 *
	 * @param space the amount of blank lines
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public BlankParagraph setSpace(int space) {
		this.space = space;
		return this;
	}

	@Override
	public List<Component> getLines() {
		if (space == 0) {
			return Collections.emptyList();
		}
		return Collections.nCopies(space, Component.empty());
	}

}
