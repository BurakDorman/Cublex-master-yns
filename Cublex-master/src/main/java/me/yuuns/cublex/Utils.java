package me.yuuns.cublex;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.util.Vector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Utils {
    public static void log(String msg) {
        Bukkit.getLogger().info(msg);
    }

    public static String ordinal(int i) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];
        }
    }

    public static Vector rotate(Vector v, double angle) {
        double x = v.getX();
        double z = v.getZ();
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector(x * cos - z * sin, v.getY(), x * sin + z * cos);
    }

    public static Vector getVectorBetweenLocations(Location p1, Location p2) {
        return p1.toVector().subtract(p2.toVector());
    }

    public static boolean getLookingAt(Player player, LivingEntity e)
    {
        Location eye = player.getEyeLocation();
        Vector toEntity = e.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }

    public static String prefix(String m) {
        return ChatColor.of("#f0d117") + "§l[" + ChatColor.of("#f7e3b5") + m + ChatColor.of("#f0d117") + "§l] " +
                ChatColor.of("#85d1bb");
    }

    public static ItemStack createSkull(String url) {
        PlayerProfile profile = Bukkit.getOfflinePlayer(UUID.randomUUID()).getPlayerProfile();
        PlayerTextures textures = profile.getTextures();
        try {
            textures.setSkin(new URL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        profile.setTextures(textures);
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) is.getItemMeta();
        sm.setOwnerProfile(profile);
        is.setItemMeta(sm);
        return is;
    }
}
