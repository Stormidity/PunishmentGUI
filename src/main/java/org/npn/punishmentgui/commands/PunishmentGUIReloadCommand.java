package org.npn.punishmentgui.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.npn.punishmentgui.PunishmentGUI;
import org.npn.punishmentgui.language.Language;
import org.npn.punishmentgui.utils.Color;
import org.npn.punishmentgui.utils.*;
import org.npn.punishmentgui.menu.handler.CustomMenu;

@CommandAlias("punishreload|reloadpunish")
@CommandPermission("punish.admin")
@Description("Reloads the punishment GUI.")
public class PunishmentGUIReloadCommand extends BaseCommand {

    @Dependency
    private PunishmentGUI plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        plugin.getSettingsFile().load();
        plugin.getLanguageFile().load();
        plugin.getCoreHandler().setupCustomMenuData();
        sender.sendMessage(Color.translate(Language.RELOADED.toString()));
    }
}
