package me.yuuns.cublex.economy.shop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.TradeSelectEvent;

public class MerchantShop implements Listener {
    @EventHandler
    public void onClick(TradeSelectEvent e) {
        if (e.getView().getTitle().equals("§7§lBaker")) {
            e.getWhoClicked().sendMessage(e.getInventory().getMerchant().getRecipe(e.getIndex()).getIngredients().get(0) + " " + e.getInventory().getMerchant().getRecipe(e.getIndex()).getResult());
            e.setCancelled(true);
        }
    }
}
