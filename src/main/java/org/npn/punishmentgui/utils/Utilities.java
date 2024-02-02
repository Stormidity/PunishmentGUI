package org.npn.punishmentgui.utils;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            players.add(player);
        }

        return players;
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(org.npn.punishmentgui.utils.Color.translate("[PunishmentGUI] " + message));
    }

    public static XMaterial getMaterial(String source) {
        XMaterial material;
        try {
            material = XMaterial.matchXMaterial(source).get();
        } catch (Exception e) {
            material = XMaterial.REDSTONE_BLOCK;
        }
        return material;
    }

    public static Color getBukkitColor(String n) {
        String name = n.toUpperCase();
        switch (name) {
            case "RED": {
                return Color.RED;
            }
            case "BLUE": {
                return Color.BLUE;
            }
            case "BLACK": {
                return Color.BLACK;
            }
            case "YELLOW": {
                return Color.YELLOW;
            }
            case "AQUA": {
                return Color.AQUA;
            }
            case "FUCHSIA": {
                return Color.FUCHSIA;
            }
            case "GRAY": {
                return Color.GRAY;
            }
            case "GREEN": {
                return Color.GREEN;
            }
            case "MAROON": {
                return Color.MAROON;
            }
            case "NAVY": {
                return Color.NAVY;
            }
            case "ORANGE": {
                return Color.ORANGE;
            }
            case "LIME": {
                return Color.LIME;
            }
            case "OLIVE": {
                return Color.OLIVE;
            }
            case "PURPLE": {
                return Color.PURPLE;
            }
            case "TEAL": {
                return Color.TEAL;
            }
            case "SILVER": {
                return Color.SILVER;
            }
        }
        return Color.WHITE;
    }

    public static int pingPlayer(Player who) {

        try {
            String bukkitversion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);

            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitversion + ".entity.CraftPlayer");

            Object handle = craftPlayer.getMethod("getHandle").invoke(who);
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            return -1;
        }
    }

    public void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 2F, 2F);
    }
}
