package me.yuuns.cublex;

import me.yuuns.cublex.economy.shop.MerchantShop;
import me.yuuns.cublex.effects.LowHealthAmbient;
import me.yuuns.cublex.effects.SpitCommand;
import me.yuuns.cublex.player.*;
import me.yuuns.cublex.recipes.RecipeManager;
import me.yuuns.cublex.server.GenerateMOTD;
import me.yuuns.cublex.server.PreventRepeatedReforge;
import me.yuuns.cublex.server.UpdateBossBar;
import me.yuuns.cublex.settings.WorldGuard;
import me.yuuns.cublex.spawn.SpawnCommand;
import me.yuuns.cublex.spawn.SpawnSettings;
import me.yuuns.cublex.test.TestEvents;
import me.yuuns.cublex.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cublex extends JavaPlugin {
    private static Cublex instance;
    private final Map<UUID, BossBar> playerBossbars = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        Utils.log(ChatColor.GREEN + "Cublex has been enabled!");

        saveDefaultConfig();

        WorldManager worldManager = new WorldManager();
        worldManager.loadWorldSettings();

        registerCommands();
        registerEvents();

        new UpdateBossBar();
        RecipeManager.setRecipes();

        for (Player p : Bukkit.getOnlinePlayers()) {
            BossBar bossbar = Bukkit.createBossBar(null, BarColor.GREEN, BarStyle.SEGMENTED_12, BarFlag.PLAY_BOSS_MUSIC);
            playerBossbars.put(p.getUniqueId(), bossbar);
            bossbar.addPlayer(p);
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        Utils.log(ChatColor.RED + "Cublex has been disabled!");

        for (UUID id : playerBossbars.keySet()) {
            playerBossbars.get(id).removeAll();
        }
    }

    public static Cublex getInstance() {
        return instance;
    }

    public void registerCommands() {
        getCommand("spawnlocation").setExecutor(new SpawnCommand());
        getCommand("spit").setExecutor(new SpitCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new SpawnSettings(), this);
        getServer().getPluginManager().registerEvents(new WorldGuard(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessage(), this);
        getServer().getPluginManager().registerEvents(new DeathMessage(), this);
        getServer().getPluginManager().registerEvents(new LowHealthAmbient(), this);
        //getServer().getPluginManager().registerEvents(new DemonEye(), this);
        //getServer().getPluginManager().registerEvents(new Demon(), this);
        //getServer().getPluginManager().registerEvents(new SkeletalRider(), this);
        //getServer().getPluginManager().registerEvents(new NPCWearLeather(), this);
        getServer().getPluginManager().registerEvents(new MerchantShop(), this);
        getServer().getPluginManager().registerEvents(new GenerateMOTD(), this);
        //getServer().getPluginManager().registerEvents(new TablistManager(), this);
        //getServer().getPluginManager().registerEvents(new SendTablist(), this);
        getServer().getPluginManager().registerEvents(new LoadResourcePack(), this);
        //getServer().getPluginManager().registerEvents(new CreateUserdata(), this);
        getServer().getPluginManager().registerEvents(new TestEvents(), this);
        getServer().getPluginManager().registerEvents(new SendBossBar(), this);
        getServer().getPluginManager().registerEvents(new MainMenu(), this);
        getServer().getPluginManager().registerEvents(new PreventRepeatedReforge(), this);
    }

    public Map<UUID, BossBar> getPlayerBossbars() {
        return playerBossbars;
    }
}