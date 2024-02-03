package org.npn.punishmentgui.language;


import lombok.Getter;
import lombok.Setter;
import org.npn.punishmentgui.PunishmentGUI;
import org.npn.punishmentgui.utils.ConfigFile;
import org.npn.punishmentgui.utils.Color;
import org.npn.punishmentgui.menu.handler.Replacement;

public enum Language {

    PUNISH_USAGE("PUNISH.USAGE", "&cUse Like: /punish (player)"),
    PUNISH_NAME_ERROR("PUNISH.NAME-ERROR", "&cThat player name is invalid!"),
    CONSOLE_ERROR("PUNISH.COMMAND-CONSOLE", "&cConsole cannot use this command!"),
    RELOADED("PUNISH.RELOAD", "&aPlugin files have been reloaded!"),

    END("", "");

    @Getter
    private String path;
    @Getter
    private String value;
    @Setter
    private static ConfigFile config;
    private PunishmentGUI plugin = PunishmentGUI.getInstance();

    Language(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String toString() {
        Replacement replacement = new Replacement(Color.translate(config.getString(this.path)));
        replacement.add("{0}", "\n");
        return replacement.toString();
    }
}
