package org.npn.punishmentgui.menu.slots.pages;


import lombok.RequiredArgsConstructor;
import org.npn.punishmentgui.menu.menu.MenuSwitch;
import org.npn.punishmentgui.menu.slots.Slot;
import org.npn.punishmentgui.utils.Color;
import org.npn.punishmentgui.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.ClickType;
import org.npn.punishmentgui.utils.Utilities;

@RequiredArgsConstructor
public class PreviousPageSlot extends Slot {
    private final MenuSwitch menuSwitch;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);
        item.setName("&bPrevious page");
        if (this.menuSwitch.getPage() != 1) {
            item.addLoreLine(" ");
            item.addLoreLine("&3Click to go ");
            item.addLoreLine("&3to the previous page. ");
            item.addLoreLine(" ");

        } else {
            item.addLoreLine(" ");
            item.addLoreLine("&cYou're on the first page. ");
            item.addLoreLine(" ");
        }
        return item.toItemStack();
    }


    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (this.menuSwitch.getPage() != 1) {

        } else {
            player.sendMessage(Color.translate("&bYou're on the first page of the menu!"));
            return;
        }
        this.menuSwitch.changePage(player, -1);
    }

    @Override
    public int getSlot() {
        return 0;
    }

    @Override
    public int[] getSlots() {
        return new int[]{36};
    }
}
