/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;

public interface Chat {

  Component SPLITTER = Component.text("» ", ExTextColor.DARK_GRAY);
  Component COLORED_SPLITTER = Component.text(ChatColor.DARK_PURPLE + "» ");

  Component COLORED_OUT = Component.text("<- ", ExTextColor.DARK_PURPLE);
  Component COLORED_IN = Component.text("-> ", ExTextColor.DARK_PURPLE);
  Component COLORED_OUT_IN = Component.text("<-> ", ExTextColor.DARK_PURPLE);

  static @NotNull Component getSplitter() {
    return Component.text("» ", NamedTextColor.DARK_GRAY);
  }

  static Component getOtherSplitter() {
    return Component.text("» ", NamedTextColor.DARK_PURPLE);
  }

  static Component getLineSeparator() {
    return Component.text("----------", NamedTextColor.WHITE);
  }

  static String getLineTDSeparator() {
    return "----------";
  }

  static Component getLongLineSeparator() {
    return Component.text("---------------", NamedTextColor.WHITE);
  }

  static String getLongLineTDSeparator() {
    return "---------------";
  }

  static Component getDoubleLineSeparator() {
    return Component.text("==========", NamedTextColor.WHITE);
  }

  static Component getSenderPlugin(Plugin plugin) {
    return Component.text(plugin.getName(), NamedTextColor.DARK_AQUA).append(getSplitter());
  }

  @Deprecated
  static Component getMessageCode(String codeType, int code, Plugin plugin) {
    return Component.text("(Code: " + codeType + code + " " + plugin.getCode() + ")");
  }

  static Component getMessageCode(Code code, ExTextColor color) {
    return Component.text("(Code: " + code.asStringCode() + ")", color);
  }

  static Component listToComponent(Collection<?> list) {
    if (list.isEmpty()) {
      return Component.empty();
    }

    TextComponent.Builder builder = Component.text();

    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (obj instanceof Component) {
        builder.append(((Component) obj));
      } else {
        builder.append(Component.text(obj.toString()));
      }

      if (iterator.hasNext()) {
        builder.append(Component.text(", "));
      }
    }

    return builder.build();
  }

  static Component listToComponent(Collection<?> list, TextColor valueColor,
                                   TextColor separaterColor) {
    if (list.isEmpty()) {
      return Component.empty();
    }

    TextComponent.Builder builder = Component.text();

    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (obj instanceof Component) {
        builder.append(((Component) obj).color(valueColor));
      } else {
        builder.append(Component.text(obj.toString(), valueColor));
      }
      if (iterator.hasNext()) {
        builder.append(Component.text(", ", separaterColor));
      }
    }

    return builder.build();
  }

  static float roundCoinAmount(float coins) {
    return ((int) (coins * 100)) / 100f;
  }

  @Deprecated
  static String getSplitterString() {
    return ChatColor.DARK_GRAY + "» " + ChatColor.RESET;
  }

  @Deprecated
  static String getOtherSplitterString() {
    return ChatColor.DARK_PURPLE + "» " + ChatColor.RESET;
  }

  @Deprecated
  static String getLineSeparatorString() {
    return ChatColor.WHITE + "----------";
  }

  @Deprecated
  static String getLongLineSeparatorString() {
    return ChatColor.WHITE + "---------------";
  }

  @Deprecated
  static String getDoubleLineSeparatorString() {
    return ChatColor.WHITE + "==========";
  }

  @Deprecated
  static String getSenderPluginString(Plugin plugin) {
    return ChatColor.DARK_AQUA + plugin.getName() + getSplitterString();
  }

  static String getTimeString(Integer timeInSec) {
    return getTimeString(Duration.ofSeconds(timeInSec));
  }

  static String getTimeString(Duration time) {
    StringBuilder sb = new StringBuilder();
    if (time.toDays() > 0) {
      sb.append(time.toDays()).append("d ");
    }
    if (time.toHoursPart() > 0) {
      sb.append(time.toHoursPart()).append("h ");
    }
    if (time.toMinutesPart() > 0) {
      sb.append(time.toMinutesPart()).append("min ");
    }
    if (time.toSecondsPart() > 0) {
      sb.append(time.toSecondsPart()).append("s");
    }

    if (sb.isEmpty()) {
      sb.append("0s");
    }

    return sb.toString();
  }

  static String getLocationString(int x, int y, int z) {
    return x + " " + y + " " + z;
  }

}
