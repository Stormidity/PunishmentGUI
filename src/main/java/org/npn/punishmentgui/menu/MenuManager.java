package org.npn.punishmentgui.menu;



import lombok.Getter;
import org.npn.punishmentgui.*;
import org.npn.punishmentgui.menu.menu.*;
import org.npn.punishmentgui.utils.*;

import java.util.*;


@Getter
public class MenuManager extends Manager {
    public Map<UUID, MenuMain> openedMenus = new HashMap<>();
    public Map<UUID, MenuMain> lastOpenedMenus = new HashMap<>();

    public MenuManager(PunishmentGUI plugin) {
        super(plugin);
    }
}

