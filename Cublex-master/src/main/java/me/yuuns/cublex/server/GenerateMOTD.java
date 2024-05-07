package me.yuuns.cublex.server;

import me.yuuns.cublex.Cublex;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class GenerateMOTD implements Listener{
    @EventHandler
    public void onMotdRequest(ServerListPingEvent e) {
        FileConfiguration config = Cublex.getInstance().getConfig();
        e.setMotd((config.getString("motd.line1") +
                "\n" + config.getString("motd.line2")).replaceAll("&", "§"));
        System.out.println("The adress " + e.getAddress() + " pinged the server");
    }
}