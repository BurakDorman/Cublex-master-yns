package me.yuuns.cublex.server;

import me.yuuns.cublex.Cublex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class PreventRepeatedReforge implements Listener {
    private final List<String> reforgeList = Cublex.getInstance().getConfig().getStringList("reforges");
    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        if (e.getClickedInventory() instanceof AnvilInventory anvil) {
            if (e.getSlotType() == InventoryType.SlotType.RESULT) {
                String newName = anvil.getRenameText();
                for (String reforge : reforgeList) {
                    if (newName == null) return;
                    if (newName.contains(reforge + " ")) {
                        ItemStack item = e.getCurrentItem();
                        ItemMeta itemMeta = Objects.requireNonNull(item).getItemMeta();
                        Objects.requireNonNull(itemMeta).setDisplayName(itemMeta.getDisplayName().replace(reforge + " ", ""));
                        item.setItemMeta(itemMeta);
                    }
                }
            }
        }
    }
}
