package me.yuuns.cublex.effects;

import me.yuuns.cublex.Cublex;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class LowHealthAmbient implements Listener {
    private final ArrayList<UUID> players = new ArrayList<>();
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if ((p.getHealth()-e.getDamage()) > 4) return;
        if (players.contains(p.getUniqueId())) return;
        players.add(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (p.getHealth() > 4) {
                    p.setWorldBorder(p.getWorld().getWorldBorder());
                    cancel();
                    players.remove(p.getUniqueId());
                    return;
                }
                heartBeat(p);
            }
        }.runTaskTimer(Cublex.getInstance(), 0, 20);
    }

    private void heartBeat(Player p) {
        WorldBorder wb = Bukkit.createWorldBorder();
        wb.setCenter(p.getLocation());
        wb.setSize(500);
        wb.setWarningDistance(1000);
        wb.setWarningTime(25);
        wb.setDamageAmount(0);
        p.setWorldBorder(wb);
        p.playSound(p, Sound.ENTITY_WARDEN_HEARTBEAT, 2, 1);
    }
}
