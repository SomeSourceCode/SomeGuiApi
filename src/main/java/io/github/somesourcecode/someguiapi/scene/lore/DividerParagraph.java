package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Collections;
import java.util.List;

public class DividerParagraph implements Paragraph {

	private int length;
	private TextColor color;
	private boolean bold;

	public DividerParagraph() {
		this(30);
	}

	public DividerParagraph(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public DividerParagraph setLength(int length) {
		this.length = length;
		return this;
	}

	public TextColor getColor() {
		return color;
	}

	public DividerParagraph setColor(TextColor color) {
		this.color = color;
		return this;
	}

	public boolean isBold() {
		return bold;
	}

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
