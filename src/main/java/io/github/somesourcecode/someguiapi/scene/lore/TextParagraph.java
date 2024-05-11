package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.util.ComponentUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * A paragraph that contains text in form of a component.
 * The content of the paragraph can be wrapped to fit a certain width.
 * @since 2.0.0
 */
public class TextParagraph implements Paragraph {

	/**
	 * The default width at which the text should be wrapped.
	 * @since 2.0.0
	 */
	public static final int DEFAULT_WRAP_WIDTH = 30;
	/**
	 * The default type of wrapping.
	 * @since 2.0.0
	 */
	public static final WrapType DEFAULT_WRAP_TYPE = WrapType.WORD;

	private Component contentComponent;

	private WrapType wrapType = DEFAULT_WRAP_TYPE;
	private int wrapWidth = DEFAULT_WRAP_WIDTH;

	/**
	 * Constructs a new empty text paragraph.
	 * @since 2.0.0
	 */
	public TextParagraph() {

	}

	/**
	 * Constructs a new text paragraph with the given content.
	 * @param content the content of the paragraph
	 * @since 2.0.0
	 */
	public TextParagraph(Component content) {
		this.contentComponent = content;
	}

	/**
	 * Returns the content of the paragraph.
	 * @return the content of the paragraph
	 * @since 2.0.0
	 */
	public Component getContent() {
		return contentComponent;
	}

	/**
	 * Sets the content of the paragraph.
	 * Previous content will be overwritten. To append content,
	 * use {@link #append(Component)}.
	 * @param content the content of the paragraph
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public TextParagraph setContent(Component content) {
		this.contentComponent = content;
		return this;
	}

	/**
	 * Appends a component to the content of the paragraph.
	 * @param component the component to append
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public TextParagraph append(Component component) {
		this.contentComponent = this.contentComponent.append(component);
		return this;
	}

	/**
	 * Returns the type of wrapping that is applied to the paragraph.
	 * @return the type of wrapping
	 * @since 2.0.0
	 */
	public WrapType getWrapType() {
		return wrapType;
	}

	/**
	 * Sets the type of wrapping that should be applied to the paragraph.
	 * @param wrapType the type of wrapping
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public TextParagraph setWrapType(WrapType wrapType) {
		this.wrapType = wrapType;
		return this;
	}

	/**
	 * Returns the width at which the text should be wrapped.
	 * @return the wrap width
	 * @since 2.0.0
	 */
	public int getWrapWidth() {
		return wrapWidth;
	}

	/**
	 * Sets the width at which the text should be wrapped.
	 * @param wrapWidth the wrap width
	 * @return the paragraph for method chaining
	 * @since 2.0.0
	 */
	public TextParagraph setWrapWidth(int wrapWidth) {
		this.wrapWidth = wrapWidth;
		return this;
	}

	@Override
	public List<Component> getLines() {

		if (contentComponent == null) {
			return Collections.emptyList();
		}

		if (wrapType == WrapType.NONE || wrapWidth <= 0) {
			return List.of(contentComponent);
		}

		Stack<Component> stack = new Stack<>();
		stack.push(contentComponent);

		List<Component> renderedComponents = new ArrayList<>();
		Component currentRenderedComponent = null;

		int currentWidth = 0;
		while (!stack.isEmpty()) {
			Component currentComponent = stack.pop();

			List<Component> children = currentComponent.children();
			for (int i = children.size() - 1; i >= 0; i--) {
				stack.push(children.get(i));
			}

			currentComponent = ComponentUtil.copyComponent(currentComponent, false);

			if (!(currentComponent instanceof TextComponent currentTextComponent)) {
				currentRenderedComponent = currentRenderedComponent == null ? currentComponent : currentRenderedComponent.append(currentComponent);
				continue;
			}

			String content = currentTextComponent.content();
			if (currentWidth == 0) {
				content = content.replaceAll("^\\s+", "");
				currentTextComponent = currentTextComponent.content(content);
			}

			if (currentWidth + content.length() >= wrapWidth) {
				int remaining = wrapWidth - currentWidth;
				String append = content.substring(0, remaining);
				String rest = content.substring(remaining);

				wordWrapping: {
					if (wrapType == WrapType.WORD) {
						if ((rest.startsWith(" ") && !rest.isBlank())
								|| (rest.isBlank() && !children.isEmpty() && (children.get(0) instanceof TextComponent childText) && !childText.content().isBlank() && childText.content().startsWith(" "))
								|| (rest.isBlank() && !stack.isEmpty() && stack.peek() instanceof TextComponent nextText && !nextText.content().isBlank() && nextText.content().startsWith(" "))) {
							break wordWrapping;
						}
						if (append.contains(" ")) {
							append = append.substring(0, append.lastIndexOf(" "));
							rest = content.substring(append.length());
							break wordWrapping;
						}
						if (currentWidth > 0) {
							renderedComponents.add(currentRenderedComponent);
							currentRenderedComponent = null;
						}
					}
				}

				if (!append.isBlank()) {
					Component appendComponent = Component.text(append, currentTextComponent.color())
							.decorations(currentTextComponent.decorations());
					currentRenderedComponent = currentRenderedComponent == null ? appendComponent : currentRenderedComponent.append(appendComponent);
				}

				if (!rest.isBlank()) {
					stack.push(Component.text(rest, currentTextComponent.color())
							.decorations(currentTextComponent.decorations()));
				}

				renderedComponents.add(currentRenderedComponent);
				currentRenderedComponent = null;
				currentWidth = 0;
				continue;
			}

			currentRenderedComponent = currentRenderedComponent == null ? currentTextComponent : currentRenderedComponent.append(currentTextComponent);
			currentWidth += content.length();

			if (stack.isEmpty()) {
				renderedComponents.add(currentRenderedComponent);
			}
		}

		return renderedComponents;
	}

}
