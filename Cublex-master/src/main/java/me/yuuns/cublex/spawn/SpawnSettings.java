package me.yuuns.cublex.spawn;

import me.yuuns.cublex.Cublex;
import me.yuuns.cublex.Utils;
import me.yuuns.cublex.database.YamlManager;
import net.citizensnpcs.api.CitizensAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SpawnSettings implements Listener {
    private final FileConfiguration config = Cublex.getInstance().getConfig();

    public void teleportEscapers(String world) {
        Bukkit.getScheduler().runTaskTimer(Cublex.getInstance(), () -> {
            for (Player p : Bukkit.getWorld(world).getPlayers()) {
                YamlConfiguration worldConfig = YamlManager.getLocalYaml("worlds", p);
                if (p.getLocation().getX() < config.getInt("spawn.borders.x-min") || p.getLocation().getX() > config.getInt("spawn.borders.x_max")
                || p.getLocation().getZ() < config.getInt("spawn.borders.z-min") || p.getLocation().getZ() > config.getInt("spawn.borders.z-max")) {
                    p.teleport(config.getLocation("spawn.location"));
                    p.sendMessage(Utils.prefix("?") + worldConfig.getString("spawn-escape").replaceAll("%npc%", CitizensAPI.getNPCRegistry().getById(1).getName()
                                    + ChatColor.of("#85d1bb")));
                }
            }
        }, 50, 100);
    }
}
