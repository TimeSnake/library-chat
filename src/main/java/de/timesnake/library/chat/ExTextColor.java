/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.chat;

import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.Index;

public class ExTextColor implements TextColor {

    public static final ExTextColor BLACK = new ExTextColor('0', NamedTextColor.BLACK);
    public static final ExTextColor QUICK_INFO = new ExTextColor('q', "quick_info",
            NamedTextColor.DARK_GRAY);
    public static final ExTextColor DARK_BLUE = new ExTextColor('1', NamedTextColor.DARK_BLUE);
    public static final ExTextColor DARK_GREEN = new ExTextColor('2', NamedTextColor.DARK_GREEN);
    public static final ExTextColor DARK_AQUA = new ExTextColor('3', NamedTextColor.DARK_AQUA);
    public static final ExTextColor DARK_RED = new ExTextColor('4', NamedTextColor.DARK_RED);
    public static final ExTextColor DARK_PURPLE = new ExTextColor('5', NamedTextColor.DARK_PURPLE);
    public static final ExTextColor GOLD = new ExTextColor('6', NamedTextColor.GOLD);
    public static final ExTextColor GRAY = new ExTextColor('7', NamedTextColor.GRAY);
    public static final ExTextColor VALUE = new ExTextColor('v', "value", NamedTextColor.GRAY);
    public static final ExTextColor DARK_GRAY = new ExTextColor('8', NamedTextColor.DARK_GRAY);
    public static final ExTextColor BLUE = new ExTextColor('9', NamedTextColor.BLUE);
    public static final ExTextColor GREEN = new ExTextColor('a', NamedTextColor.GREEN);
    public static final ExTextColor AQUA = new ExTextColor('b', NamedTextColor.AQUA);
    public static final ExTextColor RED = new ExTextColor('c', NamedTextColor.RED);
    public static final ExTextColor WARNING = new ExTextColor('w', "warning", NamedTextColor.RED);
    public static final ExTextColor LIGHT_PURPLE = new ExTextColor('d',
            NamedTextColor.LIGHT_PURPLE);
    public static final ExTextColor YELLOW = new ExTextColor('e', NamedTextColor.YELLOW);
    public static final ExTextColor PERSONAL = new ExTextColor('s', "personal",
            NamedTextColor.YELLOW);
    public static final ExTextColor WHITE = new ExTextColor('f', NamedTextColor.WHITE);
    public static final ExTextColor PUBLIC = new ExTextColor('p', "public", NamedTextColor.WHITE);

    public static final List<ExTextColor> VALUES = List.of(BLACK, QUICK_INFO, DARK_BLUE, DARK_GREEN,
            DARK_AQUA, DARK_RED,
            DARK_PURPLE, GOLD, GRAY, VALUE, DARK_GRAY, BLUE, GREEN, AQUA, RED, WARNING,
            LIGHT_PURPLE, YELLOW, PERSONAL,
            WHITE, PUBLIC);

    public static final Index<String, ExTextColor> NAMES = Index.create(ExTextColor::getName,
            VALUES);
    public static final Index<Character, ExTextColor> TOKENS = Index.create(ExTextColor::getToken,
            VALUES);

    private final char token;
    private final NamedTextColor color;
    private final String name;

    public ExTextColor(char token, NamedTextColor color) {
        this.token = token;
        this.color = color;
        this.name = color.toString();
    }

    public ExTextColor(char token, String name, NamedTextColor color) {
        this.token = token;
        this.color = color;
        this.name = name;
    }

    public char getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    @Override
    public int value() {
        return this.color.value();
    }

    @Override
    public String toString() {
        return this.color.toString();
    }
}
