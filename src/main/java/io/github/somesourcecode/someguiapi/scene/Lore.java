package io.github.somesourcecode.someguiapi.scene;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class Lore {

	private final List<Component> lines = new ArrayList<>();;

	public List<Component> getLines() {
		return lines;
	}

	public void setLines(List<Component> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
	}

	public void setLines(Component... lines) {
		this.lines.clear();
		this.lines.addAll(List.of(lines));
	}

	public void setLine(int index, Component line) {
		lines.set(index, line);
	}

	public void insertLine(int index, Component line) {
		lines.add(index, line);
	}

	public void appendLine(Component line) {
		lines.add(line);
	}

	public void setBlankLine(int index) {
		lines.set(index, Component.empty());
	}

	public void insertBlankLine(int index) {
		lines.add(index, Component.empty());
	}

	public void appendBlankLine() {
		lines.add(Component.empty());
	}

	public void removeLine(int index) {
		lines.remove(index);
	}

	public void clear() {
		lines.clear();
	}

	
	/* *************************************************************************
	 *                                                                         *
	 *                              Builder                                    *
	 *                                                                         *
	 **************************************************************************/

	public static Builder create() {
		return new Builder();
	}

	public static class Builder {

		private final Lore lore = new Lore();

		private Builder() {

		}

		public Builder lines(List<Component> lines) {
			lore.setLines(lines);
			return this;
		}

		public Builder lines(Component... lines) {
			lore.setLines(lines);
			return this;
		}

		public Builder line(int index, Component line) {
			lore.setLine(index, line);
			return this;
		}

		public Builder insertLine(int index, Component line) {
			lore.insertLine(index, line);
			return this;
		}

		public Builder line(Component line) {
			lore.appendLine(line);
			return this;
		}

		public Builder blankLine(int index) {
			lore.setBlankLine(index);
			return this;
		}

		public Builder insertBlankLine(int index) {
			lore.insertBlankLine(index);
			return this;
		}

		public Builder blankLine() {
			lore.appendBlankLine();
			return this;
		}

		public Builder removeLine(int index) {
			lore.removeLine(index);
			return this;
		}

		public Builder clear() {
			lore.clear();
			return this;
		}

		public Lore build() {
			return lore;
		}

	}

}
