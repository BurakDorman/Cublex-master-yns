package me.yuuns.cublex.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {
    private static MongoClient client;
    private static MongoDatabase database;

    public DatabaseManager() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Plugin:dE7FJV608Rk4SdPp@serverlessinstance0.bl6z8.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        client = MongoClients.create(settings);
        database = client.getDatabase("cublex");
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
