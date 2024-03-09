/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.LinkedList;
import java.util.List;

public class TimeDownParser {

	public Component parse2Component(String text) {
		return parse2Component(text, '§');
	}

	public Component parse2Component(String text, char specialToken) {
		return parse2Component(Component.empty(), text, specialToken, ExTextColor.WHITE, new LinkedList<>());
	}

	protected Component parse2Component(Component component, String text, char specialToken, ExTextColor currentColor,
																			List<TextDecoration> decorations) {
		if (text == null) {
			return null;
		}

		int tokenIndex = getNextTokenKeyIndex(text, 0, specialToken);

		if (tokenIndex == -1) {
			return component.append(Component.text(text.replace("\\", ""), currentColor)
					.decorate(decorations.toArray(TextDecoration[]::new)));
		}

		if (tokenIndex > 0) {
			component = component.append(
					Component.text(text.substring(0, tokenIndex).replace("\\", ""), currentColor)
							.decorate(decorations.toArray(TextDecoration[]::new)));
		}

		ExTextColor nextColor = currentColor;

		do {
			tokenIndex++;
			char token = text.charAt(tokenIndex);

			if (ExTextColor.TOKENS.value(token) == null) {
				ExDecoration decoration = ExDecoration.TOKENS.value(token);

				if (decoration != null) {
					decorations.add(decoration.getDecoration());
				} else {
					throw new TimeDownParseException(
							"Invalid format-option '" + token + "' at index " + tokenIndex);
				}
			} else {
				nextColor = ExTextColor.TOKENS.value(token);
				decorations.clear();
			}
		} while (text.charAt(++tokenIndex) == specialToken);

		String nextText = text.substring(tokenIndex);

		return parse2Component(component, nextText, specialToken, nextColor, decorations);
	}

	public String parse2Legacy(String text) {
		return parse2Legacy(text, '§', '§');
	}

	public String parse2Legacy(String text, char srcSpecialToken, char resSpecialToken) {
		for (ExTextColor c : ExTextColor.CUSTOM_VALUES) {
			text = text.replace("" + srcSpecialToken + c.getToken(), "" + resSpecialToken + c.getLegacyToken());
		}
		return text;
	}


	protected int getNextTokenKeyIndex(String text, int startIndex, char specialToken) {
		int tokenKeyIndex = startIndex - 1;

		do {
			tokenKeyIndex = text.indexOf(specialToken, tokenKeyIndex + 1);
			if (tokenKeyIndex >= text.length() + 1) {
				tokenKeyIndex = -1;
				break;
			}
		} while (tokenKeyIndex > 0 && text.charAt(tokenKeyIndex - 1) == '\\');

		return tokenKeyIndex;
	}

}
