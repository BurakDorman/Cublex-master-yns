package me.yuuns.cublex.spawn.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Teams {
    private final Scoreboard teams = Bukkit.getScoreboardManager().getMainScoreboard();

    public void createNewTeam(String name, ChatColor color) {
        if (getTeam(name) != null) return;

        Team team = teams.registerNewTeam(name.toUpperCase());
        team.setColor(color);
        team.setAllowFriendlyFire(false);
        team.setDisplayName(color + "" + net.md_5.bungee.api.ChatColor.BOLD + "Zort Team");
        team.setCanSeeFriendlyInvisibles(true);
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
    }

    public Team getTeam(String name) {
        return teams.getTeam(name);
    }

    public void removeTeam(String name) {
        if (getTeam(name) == null) return;
        teams.getTeam(name).unregister();
    }
}
