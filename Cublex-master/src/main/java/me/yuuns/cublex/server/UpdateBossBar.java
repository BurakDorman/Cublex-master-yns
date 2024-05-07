package me.yuuns.cublex.server;

import me.casperge.realisticseasons.api.SeasonsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.yuuns.cublex.Cublex;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class UpdateBossBar {
    private final SeasonsAPI seasons = SeasonsAPI.getInstance();
    private final Cublex plugin = Cublex.getInstance();

    public UpdateBossBar() {
        updateBossbars();
    }

    public void updateBossbars() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Map<UUID, BossBar> playerBossbars = plugin.getPlayerBossbars();

                for (UUID uuid : playerBossbars.keySet()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null) {
                        playerBossbars.remove(uuid);
                        continue;
                    }
                    if (!player.isOnline()) {
                        playerBossbars.remove(uuid);
                        continue;
                    }
                    BossBar bossBar = playerBossbars.get(player.getUniqueId());

                    if (Cublex.getInstance().getConfig().getStringList("bossbar.blacklist").contains(player.getWorld().getName())) {
                        bossBar.setTitle("§b§kTUESDAY §7§l一 §e§k16th of march 1453 §7§l一 §a§kWINTER");
                        bossBar.setProgress(0);
                    }
                    else {
                        String date = PlaceholderAPI.setPlaceholders(player, "%javascript_world_date_full%");

                        String curr = PlaceholderAPI.setPlaceholders(player, "%rs_days_until_next_season_world%");
                        int currInt = Integer.parseInt(curr);

                        String max = PlaceholderAPI.setPlaceholders(player, "%rs_seasonlength_world%");
                        int maxInt = Integer.parseInt(max);

                        String day = PlaceholderAPI.setPlaceholders(player, "%rs_weekday%");
                        String season = PlaceholderAPI.setPlaceholders(player, "%rs_season%");

                        bossBar.setTitle("§b§l" + convertWeekday(day) + " §7§l一 §e" + date + " §7§l一 " + convertSeason(season));
                        bossBar.setProgress((double) (maxInt-currInt+1) / maxInt);

                        switch (seasons.getSeason(Bukkit.getWorld("world"))) {
                            case FALL -> bossBar.setColor(BarColor.RED);
                            case SPRING -> bossBar.setColor(BarColor.PINK);
                            case SUMMER -> bossBar.setColor(BarColor.GREEN);
                            case WINTER -> bossBar.setColor(BarColor.WHITE);
                        }
                    }
                }
            }
        }.runTaskTimer(Cublex.getInstance(), 10, 50);
    }
    public String convertWeekday(String weekday) {
        return switch (weekday) {
            case "Monday" -> "ᴍᴏɴᴅᴀʏ";
            case "Tuesday" -> "ᴛᴜᴇꜱᴅᴀʏ";
            case "Wednesday" -> "ᴡᴇᴅɴᴇꜱᴅᴀʏ";
            case "Thursday" -> "ᴛʜᴜʀꜱᴅᴀʏ";
            case "Friday" -> "ꜰʀɪᴅᴀʏ";
            case "Saturday" -> "ꜱᴀᴛᴜʀᴅᴀʏ";
            default -> "ꜱᴜɴᴅᴀʏ";
        };
    }

    public String convertSeason(String season) {
        return switch (season) {
            case "Spring" -> ChatColor.of("#f699cd") + "§lꜱᴘʀɪɴɢ";
            case "Summer" -> "§a§lꜱᴜᴍᴍᴇʀ";
            case "Fall" -> ChatColor.of("#CF5230") + "§lꜰᴀʟʟ";
            default -> "§f§lᴡɪɴᴛᴇʀ";
        };
    }
}
