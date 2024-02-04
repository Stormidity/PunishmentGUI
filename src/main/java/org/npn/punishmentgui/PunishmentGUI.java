package org.npn.punishmentgui;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.npn.punishmentgui.commands.PunishmentCommand;
import org.npn.punishmentgui.menu.menu.MenuMain;
import org.npn.punishmentgui.utils.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.ConditionFailedException;
import lombok.Getter;
import org.npn.punishmentgui.commands.PunishmentGUIReloadCommand;
import org.npn.punishmentgui.language.Language;
import org.npn.punishmentgui.listeners.InventoryListener;
import org.npn.punishmentgui.menu.MenuManager;
import org.npn.punishmentgui.menu.handler.CoreHandler;
import org.npn.punishmentgui.utils.*;



@Getter
public final class PunishmentGUI extends JavaPlugin {
    @Getter
    public static PunishmentGUI instance;
    private ConfigFile settingsFile, languageFile;
    private CoreHandler coreHandler;
    private MenuManager menuManager;
    private List<Listener> listeners = new ArrayList<>();
    private BannedManager bannedPlayersManager = BannedManager.getManager();
    private PlaceholderAPI placeholderAPI;
    private String Version = "1.1.8";

    public static PunishmentGUI getInstance() {
        return PunishmentGUI.instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.settingsFile = new ConfigFile(this, "settings.yml");
        this.languageFile = new ConfigFile(this, "language.yml");
        Language.setConfig(this.languageFile);
        loadLanguage();
        Utilities.log("&aLoaded config files!");
        loadCommands();
        loadListeners();
        loadHandlers();
        this.coreHandler.setupCustomMenuData();
        Utilities.log("&aLoaded menus!");
        new Metrics(this, 5694);
        Utilities.log("&aLoaded metrics!");
        if (getSettingsFile().getBoolean("CheckForUpdates")) {
            Utilities.log("&aChecking for updates!");
            updateCheck(Bukkit.getConsoleSender(), true);
        }
        Utilities.log("&aPunishmentGUI Loaded!");
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MenuUpdate(), 20L, 20L);
    }

    public void loadCommands() {
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.getCommandConditions().addCondition("noconsole", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            if (!issuer.isPlayer()) {
                throw new ConditionFailedException(Language.CONSOLE_ERROR.toString());
            }
            return;
        });
        manager.registerCommand(new PunishmentCommand());
        manager.registerCommand(new PunishmentGUIReloadCommand());
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    @Override
    public void onDisable() {
        getBannedManager().clear();
    }

    private void loadHandlers() {
        this.menuManager = new MenuManager(this);
        this.coreHandler = new CoreHandler(this);
        this.placeholderAPI = new PlaceholderAPI(this);
    }

    private void loadLanguage() {
        if (this.languageFile == null) return;
        Arrays.stream(Language.values()).forEach(language -> {
            if (this.languageFile.getString(language.getPath()) == null) {
                this.languageFile.set(language.getPath(), language.getValue());
            }
        });
        this.languageFile.save();
    }

    public BannedManager getBannedManager() { return this.bannedPlayersManager; }

    private class MenuUpdate implements Runnable {

        @Override
        public void run() {
            Utilities.getOnlinePlayers().forEach(player -> {
                MenuMain menuMain = menuManager.getOpenedMenus().get(player.getUniqueId());
                if (menuMain != null && menuMain.isUpdateInTask()) {
                    menuMain.update(player);
                }
            });
        }
    }

    public void updateCheck(CommandSender sender, boolean console) {
        try {
            String urlString = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = reader.readLine()) != null) {
                response.append(input);
            }
            reader.close();
            JsonObject object = new JsonParser().parse(response.toString()).getAsJsonObject();

            if (object.has("plugins")) {
                JsonObject plugins = object.get("plugins").getAsJsonObject();
                JsonObject info = plugins.get("PunishmentGUI").getAsJsonObject();
                String version = info.get("version").getAsString();
                if (version.equals(getVersion())) {
                    if (console) {
                        sender.sendMessage(Color.translate("&aPunishmentGUI is on the latest version."));
                    }
                } else {
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour PunishmentGUI version is out of date!"));
                    sender.sendMessage(Color.translate("&cWe recommend updating ASAP!"));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour Version: &e" + getDescription().getVersion()));
                    sender.sendMessage(Color.translate("&aNewest Version: &e" + version));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    return;
                }
                return;
            } else {
                sender.sendMessage(Color.translate("&cWrong response from update API, contact plugin developer!"));
                return;
            }
        } catch (
                Exception ex) {
            sender.sendMessage(Color.translate("&cFailed to get updater check. (" + ex.getMessage() + ")"));
            return;
        }
    }


}
