package me.yuuns.cublex.database;

import me.yuuns.cublex.Cublex;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class YamlManager {
    public static YamlConfiguration getLocalYaml(String name, Player p) {
        String clientLang = p.getLocale();
        File lang = new File(Cublex.getInstance().getDataFolder() + "/lang/" + clientLang.split("_")[0] + "/" + name + ".yml");
        if (!lang.exists()) {
            lang = new File(Cublex.getInstance().getDataFolder() + "/lang/en/" + name + ".yml");
        }
        return YamlConfiguration.loadConfiguration(lang);
    }
    public static YamlConfiguration getPlayerYaml(UUID uuid) {
        File configFile = new File(Cublex.getInstance().getDataFolder() + "/userdata/" + uuid.toString() + ".yml");
        if (!configFile.exists())
            createPlayerYaml(uuid);
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public static void createPlayerYaml(UUID uuid) {
        File configFile = new File(Cublex.getInstance().getDataFolder() + "/userdata/" + uuid.toString() + ".yml");
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setValue(UUID uuid, String key, Object value) {
        File configFile = new File(Cublex.getInstance().getDataFolder() + "/userdata/" + uuid.toString() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set(key, value);
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
