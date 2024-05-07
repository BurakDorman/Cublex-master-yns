package me.yuuns.cublex.player;

import me.yuuns.cublex.Cublex;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.text.DecimalFormat;

public class JoinLeaveMessage implements Listener {
    private final FileConfiguration config = Cublex.getInstance().getConfig();
    private final DecimalFormat formatter = new DecimalFormat("#,###");

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.teleport(config.getLocation("spawn.location"));
            int registeredPlayers = config.getInt("players");
            config.set("players", registeredPlayers+1);
            Cublex.getInstance().saveConfig();
            e.setJoinMessage(ChatColor.of("#85d1bb") + "§l» " + "§6" + e.getPlayer().getDisplayName() + ChatColor.of("#f7e3b5") + " has joined the game " +
                    ChatColor.of("#67a8dd") + " (#" + formatter.format(registeredPlayers+1) + ")");
        }
        e.setJoinMessage(ChatColor.of("#85d1bb") + "§l» " + "§6" + e.getPlayer().getDisplayName() + ChatColor.of("#f7e3b5") + " has joined the game");
    }

    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        String world = e.getSpawnLocation().getWorld().getName();
        if (world.equals("world_spawn") || world.equals("world_travel")) {
            e.setSpawnLocation(config.getLocation("spawn.location"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.of("#eb2b79") + "§l» " + "§6" + e.getPlayer().getDisplayName() + ChatColor.of("#f7e3b5") + " left the game");
    }
}
