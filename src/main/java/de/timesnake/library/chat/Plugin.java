/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.chat;

import java.util.HashMap;
import java.util.Map;

public final class Plugin {

  public static final Plugin GAME = new Plugin("Game", "LGM");

  public static final Plugin SERVER = new Plugin("Server", "LEB");
  public static final Plugin NETWORK = new Plugin("Network", "LEN");

  public static final Plugin INFO = new Plugin("Info", "LEI");
  public static final Plugin TIME_COINS = new Plugin("Coins", "LET");

  public static final Plugin PRIVATE_MESSAGES = new Plugin("Msg", "LEM");
  public static final Plugin MAILS = new Plugin("Mails", "LEN");

  private final String name;
  private final String code;

  private final Map<String, Code> codeByString = new HashMap<>();

  public Plugin(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public String getCode() {
    return this.code;
  }

  public Code createHelpCode(String description) {
    Code code = new Code.Builder().setPlugin(this).setType(Code.Type.HELP).setDescription(description)
        .build();
    this.codeByString.put(code.asStringCode(), code);
    return code;
  }

  public Code createPermssionCode(String permission) {
    Code code = new Code.Builder().setPlugin(this).setType(Code.Type.PERMISSION).setPermission(permission)
        .build();
    this.codeByString.put(code.asStringCode(), code);
    return code;
  }

  public Code createPermssionCode(String permission, String description) {
    Code code = new Code.Builder().setPlugin(this).setType(Code.Type.PERMISSION).setPermission(permission)
        .setDescription(description).build();
    this.codeByString.put(code.asStringCode(), code);
    return code;
  }

  public Code addCode(Code code) {
    this.codeByString.put(code.asStringCode(), code);
    return code;
  }

  public Map<String, Code> getCodeByString() {
    return codeByString;
  }
}
