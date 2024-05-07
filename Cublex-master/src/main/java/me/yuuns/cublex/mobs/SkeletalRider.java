package me.yuuns.cublex.mobs;

import me.yuuns.cublex.Cublex;
import me.yuuns.cublex.Utils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SkeletalRider implements Listener, CustomMob {
    public void create(Location loc) {
        SkeletonHorse sh = loc.getWorld().spawn(loc, SkeletonHorse.class);
        Skeleton sk = loc.getWorld().spawn(loc, Skeleton.class);
        sh.addPassenger(sk);

        ItemStack skull = Utils.createSkull("http://textures.minecraft.net/texture/543cbf3fef9dbc00685d62dc2e3a9c07dd7ed3bdfa4892c9d732cec6c3684f3f");

        sk.getEquipment().setHelmet(skull);
        sk.getPersistentDataContainer().set(key, PersistentDataType.STRING, "sk");
        sh.getPersistentDataContainer().set(key, PersistentDataType.STRING, "sh");

        sh.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        sh.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
        sk.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
        sk.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(50);

        sh.setHealth(150);
        sk.setHealth(80);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Entity ent = e.getEntity();
        if (!ent.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        switch (ent.getPersistentDataContainer().get(key, PersistentDataType.STRING)) {
            case "sk":
                // TODO Set drops
                e.getDrops().clear();
                if (ent.getVehicle() != null) ((LivingEntity) ent.getVehicle()).damage(500);
                break;
            case "sh":
                // TODO Set drops
                ItemStack potion = new ItemStack(Material.LINGERING_POTION);
                PotionMeta meta = (PotionMeta) potion.getItemMeta();
                meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 160, 1), true);
                potion.setItemMeta(meta);
                ThrownPotion thrownPotion = (ThrownPotion) ent.getWorld().spawnEntity(ent.getLocation(), EntityType.SPLASH_POTION);
                thrownPotion.setVelocity(new Vector(0, 0.5, 0));
                thrownPotion.setItem(potion);
                e.getDrops().clear();
                break;
        }
    }

    @EventHandler
    public void onShoot(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Skeleton sk)) return;

        if (!sk.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        if (!sk.getPersistentDataContainer().get(key, PersistentDataType.STRING).equalsIgnoreCase("sk")) return;

        if (!(e.getEntity() instanceof Player p)) return;

        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 130, 1, true, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 3, true, false));
        p.setVelocity(sk.getLocation().getDirection().multiply(1).add(new Vector(0, 0.2, 0)));

        if (sk.getVehicle() == null) return;
        if (!(sk.getVehicle() instanceof SkeletonHorse sh)) return;

        sh.setVelocity(Utils.rotate(sh.getLocation().getDirection(), 90).multiply(new Random().nextBoolean() ? 1 : -1).add(new Vector(0, 0.3, 0)));
        p.getWorld().playSound(sh.getLocation(), Sound.ENTITY_SKELETON_HORSE_AMBIENT, 5, 0.5f);
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        if (!(e.getEntity() instanceof Arrow arrow)) return;
        if (arrow.getPersistentDataContainer().get(key, PersistentDataType.STRING).equalsIgnoreCase("arr")) return;
        if (!(arrow.getShooter() instanceof Skeleton sk)) return;

        if (!sk.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        if (!sk.getPersistentDataContainer().get(key, PersistentDataType.STRING).equalsIgnoreCase("sk")) return;

        new BukkitRunnable() {
            int i = 1;
            @Override
            public void run() {
                if (i == 4) {
                    cancel();
                    return;
                }
                Arrow arr = sk.launchProjectile(Arrow.class);
                arr.setVelocity(sk.getEyeLocation().getDirection().multiply(1.5));
                arr.getPersistentDataContainer().set(key, PersistentDataType.STRING, "arr");
                i++;
            }
        }.runTaskLater(Cublex.getInstance(), 10);
    }
}
