package me.yuuns.cublex.mobs;

import me.yuuns.cublex.Cublex;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;

public interface CustomMob {
    NamespacedKey key = new NamespacedKey(Cublex.getInstance(), "mob");
    void create(Location loc);
}
