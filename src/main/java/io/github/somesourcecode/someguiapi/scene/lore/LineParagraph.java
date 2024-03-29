package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;

public class LineParagraph implements Paragraph {

	private Component line;

	public LineParagraph(Component line) {
		this.line = line;
	}

	public Component getLine() {
		return line;
	}

	public void setLine(Component line) {
		this.line = line;
	}

	@Override
	public List<Component> getLines() {
		if (line == null) {
			return Collections.emptyList();
		}
		return Collections.singletonList(line);
	}

}
