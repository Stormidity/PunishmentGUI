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

@RequiredArgsConstructor
public class NextPageSlot extends Slot {
    private final MenuSwitch menuSwitch;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);
        item.setName("&bNext page");
        if (this.menuSwitch.getPage() < this.menuSwitch.getPages(player)) {
            item.addLoreLine(" ");
            item.addLoreLine("&3Click to go ");
            item.addLoreLine("&3to the next page. ");
            item.addLoreLine(" ");

        } else {
            item.addLoreLine(" ");
            item.addLoreLine("&cYou're on the last page. ");
            item.addLoreLine(" ");
        }
        return item.toItemStack();
    }


    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (this.menuSwitch.getPage() < this.menuSwitch.getPages(player)) {

        } else {
            player.sendMessage(Color.translate("&bYou're on the last page of the menu!"));

        }
        this.menuSwitch.changePage(player, 1);
    }

    @Override
    public int getSlot() {
        return 8;
    }
    public int[] getSlots() {
        return new int[]{44};
    }
}
