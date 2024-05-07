package me.yuuns.cublex.economy;

import me.yuuns.cublex.Utils;
import me.yuuns.cublex.database.YamlManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
    private final Coins coins = new Coins();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (strings.length == 0 ){
                p.sendMessage(Utils.prefix("§a♦") + ChatColor.of("#f7e3b5") + "You have §6§l" + coins.format(coins.getBalance(p)) +
                        ChatColor.of("#f7e3b5") + " Coins.");
                return true;
            }
            else if (strings.length == 1) {
                p.sendMessage(Utils.prefix("!") + " §cUsage: /coin <send/see>");
                return true;
            }
            Player player = Bukkit.getPlayer(strings[1]);
            if (player == null) {
                p.sendMessage(Utils.prefix("!") + " §cNo player found.");
                return true;
            }
            if (!coins.hasAccount(player)) {
                p.sendMessage(Utils.prefix("!") + " §cThe player doesn't have an account.");
                return true;
            }
            else {
                switch (strings[0]) {
                    case "set":
                        if (!p.isOp()) {
                            p.sendMessage("§cYou don't have permission to do this.");
                            return true;
                        }
                        if (strings.length == 3) {
                            int amount = Integer.parseInt(strings[2]);
                            p.sendMessage(Utils.prefix("§a♦") + ChatColor.of("#f7e3b5") + "You have set the player's coin amount to §6§l" + amount +
                                    ChatColor.of("#f7e3b5") + " Coins.");
                            YamlManager.setValue(player.getUniqueId(), "coins", amount);
                        }
                        else {
                            p.sendMessage(Utils.prefix("!") + " §cUsage: /coin set <player> <amount>");
                        }
                        break;

                    case "see":
                        if (strings.length == 2) {
                            p.sendMessage(Utils.prefix("§a♦") + ChatColor.of("#f7e3b5") + "The player has §6§l" + coins.format(coins.getBalance(player)) +
                                    ChatColor.of("#f7e3b5") + " Coins.");
                            return true;
                        }
                        else {
                            p.sendMessage(Utils.prefix("!") + " §cUsage: /coin see <player>");
                        }
                        break;
                    default:
                        p.sendMessage(Utils.prefix("!") + " §cUsage: /coin <send/see>");
                        break;

                    case "send":
                        if (strings.length == 3) {
                            int amount = 0;
                            try {
                                amount = Integer.parseInt(strings[2]);
                            } catch (Exception e) {
                                p.sendMessage(Utils.prefix("!") + " §cUsage: /coin send <player> <amount>");
                                return true;
                            }
                            if (!coins.has(p, amount)) {
                                p.sendMessage(Utils.prefix("!") + " §cYou don't have enough Coins.");
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                                return true;
                            }
                            p.sendMessage(Utils.prefix("§a♦") + ChatColor.of("#f7e3b5") + "You have sent §6§l"
                                    + amount + ChatColor.of("#f7e3b5") + " Coins to " + player.getDisplayName()
                                    + ChatColor.of("#f7e3b5") + ".");
                            player.sendMessage(Utils.prefix("§a♦") + ChatColor.of("#f7e3b5") + p.getDisplayName() + " has sent you §6§l"
                                    + amount + ChatColor.of("#f7e3b5") + " Coins.");
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            coins.depositPlayer(player, amount);
                            coins.withdrawPlayer(p, amount);
                        }
                        else {
                            p.sendMessage(Utils.prefix("!") + " §cUsage: /coin send <player> <amount>");
                        }
                        break;
                }
            }
            return true;
        }
        return false;
    }
}
