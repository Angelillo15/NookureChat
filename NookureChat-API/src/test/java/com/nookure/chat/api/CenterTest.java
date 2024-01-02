package com.nookure.chat.api;

import com.nookure.chat.api.font.DefaultFontInfo;
import org.junit.jupiter.api.Test;

public class CenterTest {
  @Test
  public void test() {
    String input = "<red>Hello <bold>World!</bold></red>";

    String centered = DefaultFontInfo.center(input);

    assert centered.equals("                             <red>Hello <bold>World!</bold></red>");
  }
}
