package io.github.somesourcecode.someguiapi.scene.lore;

import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.List;

public class BlankParagraph implements Paragraph {

	private int space;

	public BlankParagraph() {
		this.space = 1;
	}

	public BlankParagraph(int space) {
		this.space = space;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	@Override
	public List<Component> getLines() {
		if (space == 0) {
			return Collections.emptyList();
		}
		return Collections.nCopies(space, Component.empty());
	}

}
