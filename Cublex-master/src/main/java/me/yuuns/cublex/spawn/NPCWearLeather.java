package me.yuuns.cublex.spawn;

import me.casperge.realisticseasons.api.SeasonChangeEvent;
import me.casperge.realisticseasons.season.Season;
import me.yuuns.cublex.Cublex;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;


public class NPCWearLeather implements Listener {
    private final NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
    @EventHandler
    public void onSeasonChange(SeasonChangeEvent e) {
        for (NPC npc : npcRegistry.sorted()) {
            if (npc.getEntity() == null) continue;
            if (!npc.getEntity().getWorld().getName().equals("world_spawn")) continue;
            if (npc.getEntity().getType() != EntityType.PLAYER) continue;

            if (e.getNewSeason() == Season.WINTER) {
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();

                String hex = Cublex.getInstance().getConfig().getString("npc." + npc.getId());
                meta.setColor(Color.fromRGB(Integer.valueOf(hex.substring(0, 2), 16), Integer.valueOf(hex.substring(2, 4), 16), Integer.valueOf(hex.substring(4, 6), 16)));
                chestplate.setItemMeta(meta);
                leggings.setItemMeta(meta);
                boots.setItemMeta(meta);

                Equipment eq = npc.getOrAddTrait(Equipment.class);
                eq.set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
                eq.set(Equipment.EquipmentSlot.LEGGINGS, leggings);
                eq.set(Equipment.EquipmentSlot.BOOTS, boots);
            }
            else {
                Equipment eq = npc.getOrAddTrait(Equipment.class);
                eq.set(Equipment.EquipmentSlot.CHESTPLATE, null);
                eq.set(Equipment.EquipmentSlot.LEGGINGS, null);
                eq.set(Equipment.EquipmentSlot.BOOTS, null);
            }
        }
    }
}
