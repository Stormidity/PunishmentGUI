package org.npn.punishmentgui.commands;

import co.aikar.commands.*;
import co.aikar.commands.annotation.*;
import org.npn.punishmentgui.*;
import org.npn.punishmentgui.language.Language;
import org.npn.punishmentgui.menu.handler.*;
import org.npn.punishmentgui.utils.Tasks;
import org.npn.punishmentgui.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;


@CommandAlias("punish|punishment")
@CommandPermission("punish.use")
@Description("Opens the punish menu for specified player.")
@Conditions("noconsole")
public class PunishmentCommand extends BaseCommand {

    @Dependency
    private PunishmentGUI plugin;

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0 || args.length > 2) {
            player.sendMessage(plugin.getPlaceholderAPI().translate(player, Language.PUNISH_USAGE.toString()));
            return;
        }
        if (args.length == 1) {
            plugin.getBannedManager().add(player.getUniqueId(), args[0]);
        }
        if (plugin.getBannedManager().get(player.getUniqueId()).length() > 16) {
            player.sendMessage(plugin.getPlaceholderAPI().translate(player, Language.PUNISH_NAME_ERROR.toString()));
            return;
        }
        String menu = plugin.getSettingsFile().getString("Command").replace("{openmenu:", "").replace("}", "").toLowerCase();
        CustomMenu customMenu = plugin.getCoreHandler().getCustomMenuData().get(menu);
        if (menu != null) {
            Tasks.run(plugin, () -> {
                player.closeInventory();
                customMenu.getMenu().open(player);
                return;
            });
        } else {
            Utilities.log("&cThere is no menu with name &e&n" + plugin.getSettingsFile().getString("Command") + "&b &eto open for &b" + player.getName() + "&e. &c&oPlease check your configurations.");
            return;
        }
        player.updateInventory();
    }
}
