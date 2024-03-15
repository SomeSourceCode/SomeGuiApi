package io.github.somesourcecode.someguiapi.scene;

import net.kyori.adventure.text.Component;

import java.util.List;

public class Lore {

	private List<Component> lines;

	public Lore(List<Component> lines) {
		this.lines = lines;
	}

	public List<Component> getLines() {
		return lines;
	}

	public void setLines(List<Component> lines) {
		this.lines = lines;
	}

}
