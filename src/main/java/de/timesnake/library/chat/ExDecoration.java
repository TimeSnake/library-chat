/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.chat;

import java.util.List;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.util.Index;

public class ExDecoration {

    public static final ExDecoration UNDERLINED = new ExDecoration('u', TextDecoration.UNDERLINED);
    public static final ExDecoration BOLD = new ExDecoration('l', TextDecoration.BOLD);
    public static final ExDecoration ITALIC = new ExDecoration('i', TextDecoration.ITALIC);
    public static final ExDecoration OBFUSCATED = new ExDecoration('o', TextDecoration.OBFUSCATED);
    public static final ExDecoration STRIKETHROUGH = new ExDecoration('r',
            TextDecoration.STRIKETHROUGH);

    public static final List<ExDecoration> VALUES = List.of(UNDERLINED, BOLD, ITALIC, OBFUSCATED,
            STRIKETHROUGH);

    public static final Index<String, ExDecoration> NAMES = Index.create(ExDecoration::getName,
            VALUES);
    public static final Index<Character, ExDecoration> TOKENS = Index.create(ExDecoration::getToken,
            VALUES);

    private final char token;
    private final TextDecoration decoration;
    private final String name;

    public ExDecoration(char token, TextDecoration decoration) {
        this.token = token;
        this.decoration = decoration;
        this.name = decoration.toString();
    }

    public ExDecoration(char token, String name, TextDecoration decoration) {
        this.token = token;
        this.decoration = decoration;
        this.name = name;
    }

    public char getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public TextDecoration getDecoration() {
        return decoration;
    }

    @Override
    public String toString() {
        return this.decoration.toString();
    }

}
