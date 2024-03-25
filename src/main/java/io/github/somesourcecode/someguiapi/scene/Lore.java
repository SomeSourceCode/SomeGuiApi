/*
 * Copyright 2024, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.scene;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the lore of an item.
 */
public class Lore {

	private final List<Component> lines = new ArrayList<>();;

	/**
	 * Constructs a new empty lore.
	 */
	public Lore() {

	}

	/**
	 * Constructs a new lore with the given lines.
	 * @param lines the lines of the lore
	 */
	public Lore(List<Component> lines) {
		this.lines.addAll(lines);
	}

	/**
	 * Constructs a new lore with the given lines.
	 * @param lines the lines of the lore
	 */
	public Lore(Component... lines) {
		this.lines.addAll(List.of(lines));
	}

	/**
	 * Returns the lines of the lore, represented as a list of components.
	 * @return the lines of the lore
	 */
	public List<Component> getLines() {
		return lines;
	}

	/**
	 * Sets the lines of the lore. Lines that were previously set will be removed.
	 * @param lines the lines of the lore
	 */
	public void setLines(List<Component> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
	}

	/**
	 * Sets the lines of the lore. Lines that were previously set will be removed.
	 * @param lines the lines of the lore
	 */
	public void setLines(Component... lines) {
		this.lines.clear();
		this.lines.addAll(List.of(lines));
	}

	/**
	 * Replaces the line at the specified index with the given line.
	 * @param index the index of the line to replace
	 * @param line the line to replace the existing line with
	 */
	public void setLine(int index, Component line) {
		lines.set(index, line);
	}

	/**
	 * Inserts the given line at the specified index.
	 * @param index the index to insert the line at
	 * @param line the line to insert
	 */
	public void insertLine(int index, Component line) {
		lines.add(index, line);
	}

	/**
	 * Appends the given line to the end of the lore.
	 * @param line the line to append
	 */
	public void appendLine(Component line) {
		lines.add(line);
	}

	/**
	 * Sets the line at the specified index to a blank line.
	 * @param index the index of the line to set to a blank line
	 */
	public void setBlankLine(int index) {
		lines.set(index, Component.empty());
	}

	/**
	 * Inserts a blank line at the specified index.
	 * @param index the index to insert the blank line at
	 */
	public void insertBlankLine(int index) {
		lines.add(index, Component.empty());
	}

	/**
	 * Appends a blank line to the end of the lore.
	 */
	public void appendBlankLine() {
		lines.add(Component.empty());
	}

	/**
	 * Removes the line at the specified index.
	 * @param index the index of the line to remove
	 */
	public void removeLine(int index) {
		lines.remove(index);
	}

	/**
	 * Removes all lines from the lore.
	 */
	public void clear() {
		lines.clear();
	}

	
	/* *************************************************************************
	 *                                                                         *
	 *                              Builder                                    *
	 *                                                                         *
	 **************************************************************************/

	/**
	 * Constructs a new empty lore builder.
	 * @return the lore builder
	 */
	public static Builder create() {
		return new Builder();
	}

	/**
	 * Constructs a new lore builder with the lines of the given lore.
	 * @param lore the lore to copy the lines from
	 * @return the lore builder
	 */
	public static Builder create(Lore lore) {
		return new Builder().lines(lore.getLines());
	}

	/**
	 * The builder for the Lore class. Allows for easy construction of lore objects,
	 * by making use of a fluent API.
	 */
	public static class Builder {

		private final Lore lore = new Lore();

		private Builder() {

		}

		/**
		 * Sets the lines of the lore. Previous lines will be removed.
		 * @param lines the lines of the lore
		 * @return the builder for method chaining
		 */
		public Builder lines(List<Component> lines) {
			lore.setLines(lines);
			return this;
		}

		/**
		 * Sets the lines of the lore. Previous lines will be removed.
		 * @param lines the lines of the lore
		 * @return the builder for method chaining
		 */
		public Builder lines(Component... lines) {
			lore.setLines(lines);
			return this;
		}

		/**
		 * Replaces the line at the specified index with the given line.
		 * @param index the index of the line to replace
		 * @param line the line to replace the existing line with
		 * @return the builder for method chaining
		 */
		public Builder line(int index, Component line) {
			lore.setLine(index, line);
			return this;
		}

		/**
		 * Inserts the given line at the specified index.
		 * @param index the index to insert the line at
		 * @param line the line to insert
		 * @return the builder for method chaining
		 */
		public Builder insertLine(int index, Component line) {
			lore.insertLine(index, line);
			return this;
		}

		/**
		 * Appends the given line to the end of the lore.
		 * @param line the line to append
		 * @return the builder for method chaining
		 */
		public Builder line(Component line) {
			lore.appendLine(line);
			return this;
		}

		/**
		 * Sets the line at the specified index to a blank line.
		 * @param index the index of the line to set to a blank line
		 * @return the builder for method chaining
		 */
		public Builder blankLine(int index) {
			lore.setBlankLine(index);
			return this;
		}

		/**
		 * Inserts a blank line at the specified index.
		 * @param index the index to insert the blank line at
		 * @return the builder for method chaining
		 */
		public Builder insertBlankLine(int index) {
			lore.insertBlankLine(index);
			return this;
		}

		/**
		 * Appends a blank line to the end of the lore.
		 * @return the builder for method chaining
		 */
		public Builder blankLine() {
			lore.appendBlankLine();
			return this;
		}

		/**
		 * Removes the line at the specified index.
		 * @param index the index of the line to remove
		 * @return the builder for method chaining
		 */
		public Builder removeLine(int index) {
			lore.removeLine(index);
			return this;
		}

		/**
		 * Clears all lines from the lore.
		 * @return the builder for method chaining
		 */
		public Builder clear() {
			lore.clear();
			return this;
		}

		/**
		 * Builds the lore object.
		 * @return the lore object
		 */
		public Lore build() {
			return lore;
		}

	}

}
