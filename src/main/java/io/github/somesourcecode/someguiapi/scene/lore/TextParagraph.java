package io.github.somesourcecode.someguiapi.scene.lore;

import io.github.somesourcecode.someguiapi.util.ComponentUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TextParagraph implements Paragraph {

	public static final int DEFAULT_WRAP_WIDTH = 30;
	public static final WrapType DEFAULT_WRAP_TYPE = WrapType.WORD;

	private Component contentComponent;

	private WrapType wrapType = DEFAULT_WRAP_TYPE;
	private int wrapWidth = DEFAULT_WRAP_WIDTH;

	public TextParagraph() {

	}

	public TextParagraph(Component content) {
		this.contentComponent = content;
	}

	public Component getContent() {
		return contentComponent;
	}

	public TextParagraph setContent(Component content) {
		this.contentComponent = content;
		return this;
	}

	public TextParagraph append(Component component) {
		this.contentComponent = this.contentComponent.append(component);
		return this;
	}

	public WrapType getWrapType() {
		return wrapType;
	}

	public TextParagraph setWrapType(WrapType wrapType) {
		this.wrapType = wrapType;
		return this;
	}

	public int getWrapWidth() {
		return wrapWidth;
	}

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
