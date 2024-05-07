package me.yuuns.cublex.player;

import com.mongodb.client.MongoCollection;
import me.yuuns.cublex.database.DatabaseManager;
import me.yuuns.cublex.database.YamlManager;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CreateUserdata implements Listener {
    private final MongoCollection<Document> gems = new DatabaseManager().getDatabase().getCollection("gems");
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) return;
        YamlManager.createPlayerYaml(p.getUniqueId());
        YamlManager.setValue(p.getUniqueId(), "coins", 5);
        Document player = new Document("_id", new ObjectId()).append("uuid", p.getUniqueId().toString()).append("gems", 0);
        gems.insertOne(player);
    }
}
