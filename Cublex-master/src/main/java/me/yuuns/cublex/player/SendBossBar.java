package me.yuuns.cublex.player;

import me.yuuns.cublex.Cublex;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;
import java.util.UUID;

public class SendBossBar implements Listener {
    private final Map<UUID, BossBar> playerBossbars = Cublex.getInstance().getPlayerBossbars();
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        BossBar bossbar = Bukkit.createBossBar(null, BarColor.GREEN, BarStyle.SEGMENTED_12, BarFlag.PLAY_BOSS_MUSIC);
        playerBossbars.put(p.getUniqueId(), bossbar);
        bossbar.addPlayer(p);
    }
}
