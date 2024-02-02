package org.npn.punishmentgui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.npn.punishmentgui.PunishmentGUI;
import org.npn.punishmentgui.menu.menu.MenuMain;
import org.npn.punishmentgui.menu.slots.Slot;
import org.npn.punishmentgui.utils.Manager;

public class InventoryListener extends Manager implements Listener {

    private PunishmentGUI plugin = PunishmentGUI.getInstance();

    public InventoryListener(PunishmentGUI plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleInventories(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        MenuMain menu = plugin.getMenuManager().getOpenedMenus().get(player.getUniqueId());

        if (menu == null) return;

        event.setCancelled(true);

        if (event.getSlot() != event.getRawSlot()) return;
        if (!menu.hasSlot(event.getSlot())) return;

        Slot slot = menu.getSlot(event.getSlot());
        slot.onClick(player, event.getSlot(), event.getClick());
    }


    @EventHandler(priority = EventPriority.HIGHEST)    public void handleInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        MenuMain menu = plugin.getMenuManager().getOpenedMenus().get(player.getUniqueId());

        if (menu == null) return;

        menu.onClose(player);
        plugin.getMenuManager().getOpenedMenus().remove(player.getUniqueId());
    }
}
