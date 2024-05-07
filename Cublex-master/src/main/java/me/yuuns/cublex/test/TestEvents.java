package me.yuuns.cublex.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;

public class TestEvents implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() == null)  return;
        if (e.getItem().getItemMeta() == null) return;
        if (e.getItem().getItemMeta() instanceof BookMeta) {
            BookMeta bookMeta = (BookMeta) e.getItem().getItemMeta();
            System.out.println(bookMeta.getEnchants());
        }
    }
}
