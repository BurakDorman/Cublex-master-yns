package me.yuuns.cublex.worlds;

import me.yuuns.cublex.settings.TravelSettings;
import me.yuuns.cublex.spawn.SpawnSettings;
import org.bukkit.WorldCreator;

public class WorldManager {
    private final SpawnSettings spawnSettings = new SpawnSettings();
    private final TravelSettings travelSettings = new TravelSettings();

    public void loadWorlds() {
        new WorldCreator("world_spawn").createWorld();
        new WorldCreator("world_travel").createWorld();
    }

    public void loadWorldSettings() {
        //spawnSettings.teleportEscapers("world_spawn");
        //travelSettings.doFastDayCycle("world_travel");
    }
}
