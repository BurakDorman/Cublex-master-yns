package me.yuuns.cublex.spawn;

import me.yuuns.cublex.Cublex;
import me.yuuns.cublex.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            Utils.log(p.hasPermission("owner.commands")  + "");
            Cublex.getInstance().getConfig().set("spawn.location", p.getLocation());
            Cublex.getInstance().saveConfig();
            p.sendMessage(Utils.prefix("Admin") + " §aSpawn location set.");
            return true;
        }
        return false;
    }
}
