package org.npn.punishmentgui.menu;


import lombok.Getter;
import org.npn.punishmentgui.PunishmentGUI;
import org.npn.punishmentgui.menu.menu.MenuMain;
import org.npn.punishmentgui.utils.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class MenuManager extends Manager {
    public Map<UUID, MenuMain> openedMenus = new HashMap<>();
    public Map<UUID, MenuMain> lastOpenedMenus = new HashMap<>();

    public MenuManager(PunishmentGUI plugin) {
        super(plugin);
    }
}
