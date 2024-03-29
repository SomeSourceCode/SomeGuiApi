package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.scene.action.RenderContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lore {

	private final List<Paragraph> paragraphs = new ArrayList<>();

	public Lore setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs.clear();
		this.paragraphs.addAll(paragraphs);
		return this;
	}

	public Lore appendLine(Component line) {
		return appendParagraph(Paragraph.line(line));
	}

	public Lore appendParagraph(Paragraph paragraph) {
		paragraphs.add(paragraph);
		return this;
	}

	public Lore appendParagraphs(Paragraph... paragraphs) {
		for (Paragraph paragraph : paragraphs) {
			appendParagraph(paragraph);
		}
		return this;
	}

	public Lore appendParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs.addAll(paragraphs);
		return this;
	}

	public Lore appendBlank() {
		return appendParagraph(Paragraph.blank());
	}

	public Lore appendBlank(int space) {
		return appendParagraph(Paragraph.blank(space));
	}

	public Lore appendDivider(int length, NamedTextColor color) {
		return appendParagraph(Paragraph.divider(length, color));
	}

	public Lore appendDivider(int length, NamedTextColor color, boolean bold) {
		return appendParagraph(Paragraph.divider(length, color, bold));
	}

	public Lore clear() {
		paragraphs.clear();
		return this;
	}

	public List<Component> generateLines(RenderContext context) {
		paragraphs.stream()
				.filter(ReloadableParagraph.class::isInstance)
				.map(ReloadableParagraph.class::cast)
				.forEach(paragraph -> paragraph.reload(context));
		return paragraphs.stream()
				.filter(Objects::nonNull)
				.map(Paragraph::getLines)
				.flatMap(List::stream)
				.toList();
	}

}
