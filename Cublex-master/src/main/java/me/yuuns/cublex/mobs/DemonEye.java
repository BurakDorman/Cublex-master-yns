package me.yuuns.cublex.mobs;

import me.yuuns.cublex.Cublex;
import me.yuuns.cublex.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;


public class DemonEye implements Listener, CustomMob {
    public void create(Location loc) {
        Vex vex = (Vex) loc.getWorld().spawnEntity(loc, EntityType.VEX);
        vex.getPersistentDataContainer().set(key, PersistentDataType.STRING, "demoneye");

        ItemStack skull = Utils.createSkull("http://textures.minecraft.net/texture/ab5b636ba3bf335c2bf731ae7aa7e56194fb1e0b202aa811ea140037082da0ad");

        vex.setInvisible(true);
        vex.getEquipment().setHelmet(skull);
        vex.getEquipment().setItemInMainHand(null);
        vex.setLifeTicks(30*60*20);
        vex.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);

        new BukkitRunnable() {
            long i = 0;
            @Override
            public void run() {
                i++;
                if (vex.isDead()) {
                    this.cancel();
                    return;
                }
                if (i%2 == 0) vex.getWorld().spawnParticle(Particle.REDSTONE, vex.getEyeLocation().subtract(vex.getEyeLocation().getDirection().multiply(0.3)),
                        1, new Particle.DustOptions(Color.RED, 1.5f));

                Location loc = vex.getEyeLocation();
                Object[] nearby = vex.getNearbyEntities(10, 10, 10).stream().filter(entity -> entity instanceof Player).toArray();
                if (nearby.length > 0) {
                    Player lookAt = (Player) nearby[0];
                    loc.setDirection(Utils.getVectorBetweenLocations(loc, lookAt.getEyeLocation()).multiply(-1));
                    vex.setRotation(loc.getYaw(), loc.getPitch());
                }
            }
        }.runTaskTimer(Cublex.getInstance(), 0, 1);
    }

    @EventHandler
    public void checkLook(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        e.getPlayer().getNearbyEntities(10, 10, 10).stream().filter(entity -> entity instanceof Vex).forEach(v -> {
            LivingEntity vex = (LivingEntity) v;
            if (!vex.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
            if (!vex.getPersistentDataContainer().get(key, PersistentDataType.STRING).equalsIgnoreCase("demoneye")) return;
            if (!Utils.getLookingAt(p, vex)) return;
            p.damage(1);
        });
    }
}
