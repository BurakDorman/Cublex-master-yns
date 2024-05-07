package me.yuuns.cublex.player;

import me.yuuns.cublex.Cublex;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessage implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Bukkit.getScheduler().runTaskLater(Cublex.getInstance(), () -> {
            e.getEntity().spigot().respawn();
            //e.setDeathMessage(null);
            e.getEntity().sendTitle("☠", null, 20, 40, 50);
        }, 1);
    }
}
