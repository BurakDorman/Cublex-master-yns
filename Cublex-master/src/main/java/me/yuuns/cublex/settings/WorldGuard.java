package me.yuuns.cublex.settings;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WorldGuard implements Listener{
    @EventHandler
    public void onChangeSpawn(PlayerInteractEvent e) {
        if (e.getAction() != Action.PHYSICAL && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getPlayer().hasPermission("cublex.spawn.edit")) return;
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("world_spawn")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase("world_spawn")) return;
        if (!(e.getEntity() instanceof Player)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase("world_spawn")) return;
        e.getDrops().clear();
    }
}
