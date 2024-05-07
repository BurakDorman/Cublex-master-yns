package me.yuuns.cublex.settings;

import me.yuuns.cublex.Cublex;
import org.bukkit.Bukkit;

public class TravelSettings {
    private int dayStep = 0;
    public void doFastDayCycle(String world) {
        Bukkit.getScheduler().runTaskTimer(Cublex.getInstance(), () -> {
            dayStep++;
            dayStep %= 480;
            Cublex.getInstance().getServer().getWorld(world).setTime(dayStep * 50);
        }, 10, 1);
    }
}
