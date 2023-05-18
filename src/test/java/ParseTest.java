/*
 * Copyright (C) 2023 timesnake
 */

import de.timesnake.library.chat.TimeDownParser;
import java.util.stream.Stream;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParseTest {

  public static Stream<Arguments> parseStrings() {
    return Stream.of(
        Arguments.of("test", "§ftest"),
        Arguments.of("§6test", "§6test"),
        Arguments.of("\\§6test", "§f§6test"),
        Arguments.of("§ptest", "§ftest"),
        Arguments.of("§stest", "§etest"),
        Arguments.of("§vtest", "§7test"),
        Arguments.of("§6te §cst", "§6te §cst")
    );
  }

  @ParameterizedTest
  @MethodSource("parseStrings")
  public void parse(String text, String expected) {
    Assertions.assertEquals(expected, LegacyComponentSerializer.legacySection()
        .serialize(new TimeDownParser().parse2Component(text)));
  }

}
