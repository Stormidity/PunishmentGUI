package org.npn.punishmentgui.menu.slots.pages;


import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.npn.punishmentgui.menu.slots.Slot;
import org.npn.punishmentgui.utils.ItemBuilder;
import org.npn.punishmentgui.menu.menu.MenuSwitch;

@RequiredArgsConstructor
public class PageSlot extends Slot {
    private final MenuSwitch menuSwitch;
    private final int slot;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.PAPER);
        item.setName(menuSwitch.getPagesTitle(player));

            item.addLoreLine(" ");
            item.addLoreLine("&bCurrent page&7: &3" + menuSwitch.getPage());
            item.addLoreLine("&bMax pages&7: &3" + menuSwitch.getPages(player));
            item.addLoreLine(" ");

            return item.toItemStack();

    }
    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public int[] getSlots() {
        return new int[]{40};
    }
}
