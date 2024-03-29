package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Collections;
import java.util.List;

/**
 * A paragraph that acts as a divider.
 */
public class DividerParagraph implements Paragraph {

	private int length;
	private TextColor color;
	private boolean bold;

	/**
	 * Constructs a new divider paragraph.
	 */
	public DividerParagraph() {
		this(30);
	}

	/**
	 * Constructs a new divider paragraph with the given length.
	 * @param length the length of the divider
	 */
	public DividerParagraph(int length) {
		this.length = length;
	}

	/**
	 * Returns the length of the divider.
	 * @return the length of the divider
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length of the divider.
	 * @param length the length of the divider
	 * @return the paragraph for method chaining
	 */
	public DividerParagraph setLength(int length) {
		this.length = length;
		return this;
	}

	/**
	 * Returns the color of the divider.
	 * @return the color of the divider
	 */
	public TextColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the divider.
	 * @param color the color of the divider
	 * @return the paragraph for method chaining
	 */
	public DividerParagraph setColor(TextColor color) {
		this.color = color;
		return this;
	}

	/**
	 * Returns whether the divider is bold.
	 * @return whether the divider is bold
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * Sets whether the divider is bold.
	 * @param bold whether the divider is bold
	 * @return the paragraph for method chaining
	 */
	public DividerParagraph setBold(boolean bold) {
		this.bold = bold;
		return this;
	}

	@Override
	public List<Component> getLines() {
		if (length <= 0) {
			return Collections.singletonList(Component.empty());
		}
		Component component = Component.text(" ".repeat(length))
				.decoration(TextDecoration.ITALIC, false)
				.decoration(TextDecoration.BOLD, bold)
				.decoration(TextDecoration.STRIKETHROUGH, true)
				.color(color);
		return Collections.singletonList(component);
	}

}
