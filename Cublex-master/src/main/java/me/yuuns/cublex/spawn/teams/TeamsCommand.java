package me.yuuns.cublex.spawn.teams;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.yuuns.cublex.database.YamlManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            YamlConfiguration config = YamlManager.getLocalYaml("teams", p);
            String title = ChatColor.of("#397ae3") + "" + ChatColor.BOLD + config.getString("title");

            ChestGui gui = new ChestGui(5, title);
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
            background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
            background.setRepeat(true);
            gui.addPane(background);

            //gui.show(p);

            Scoreboard teams = Bukkit.getScoreboardManager().getMainScoreboard();
            if (teams.getTeam("zort") == null) {
                Team team = teams.registerNewTeam("zort");
                team.setColor(org.bukkit.ChatColor.AQUA);
                team.setAllowFriendlyFire(false);
                team.setDisplayName(ChatColor.of("#397ae3") + "" + ChatColor.BOLD + "Zort Team");
                team.setCanSeeFriendlyInvisibles(true);
                team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
                team.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
                team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
            }
        }
        return true;
    }
}
