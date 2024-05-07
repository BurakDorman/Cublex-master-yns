package me.yuuns.cublex.mobs;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;

public class Demon implements CustomMob, Listener {
    @Override
    public void create(Location loc) {
        Evoker demon = (Evoker) loc.getWorld().spawnEntity(loc, EntityType.EVOKER);
        demon.getPersistentDataContainer().set(key, PersistentDataType.STRING, "demon");
        demon.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        demon.setHealth(200);
        demon.getEquipment().setHelmet(null);
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        if (!(e.getEntity() instanceof Vex vex)) return;
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPELL) return;
        vex.remove();
        new DemonEye().create(e.getLocation());
    }
}
