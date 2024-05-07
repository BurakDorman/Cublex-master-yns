package me.yuuns.cublex.effects;

import me.yuuns.cublex.Cublex;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SpitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (player.hasPermission("cublex.spit")) {
                final Location location = player.getLocation().toVector().add(player.getLocation().getDirection().multiply(0.8)).toLocation(player.getWorld()).add(0.0, 1.0, 0.0);
                final Entity spitmonster = player.getWorld().spawnEntity(location, EntityType.LLAMA_SPIT);
                spitmonster.setVelocity(player.getEyeLocation().getDirection().multiply(1));

                File file = new File(Cublex.getInstance().getDataFolder() + "/blacklist.yml");
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                int num = 0;
                String[] skip = {"_SWORD", "_AXE", "_PICKAXE", "_SHOVEL",
                        "_HOE", "_HELMET", "_CHESTPLATE", "_LEGGINGS", "_BOOTS", "ELYTRA",
                        "TRIDENT", "BOW", "CROSSBOW", "SHIELD", "FISHING_ROD",
                        "_HORSE_ARMOR", "_SPAWN_EGG", "POTION", "_ARROW", "FIREWORK",
                        "ENCHANTED_BOOK", "FILLED_MAP", "_HEAD", "RABBIT_FOOT",
                        "BANNER", "GOLDEN_APPLE", "TOTEM_OF_UNDYING", "STRUCTURE_VOID",
                        "TURTLE_HELMET", "SPYGLASS", "CLOCK", "NAME_TAG", "LEAD",
                        "CONDUIT", "GOAT_HORN", "BEACON"};

                loop:
                for (Material material : Material.values()) {
                    for (String skp : skip) {
                        if (material.name().contains(skp)) {
                            System.out.println("Skipping " + material.name());
                            continue loop;
                        }
                    }
                    yaml.createSection(num + "");
                    yaml.set(num + ".item", "minecraft:" + material.name().toLowerCase());
                    num++;
                }

                try {
                    yaml.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                /*
                System.out.println("§aProcessing!");
                File file = new File(Bukkit.getPluginManager().getPlugin("mcMMO").getDataFolder() + "/locales/locale.properties");
                InputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                File file1 = new File(Cublex.getInstance().getDataFolder() + "/locale_override.properties");
                OutputStream fileInputStream1 = null;
                try {
                    fileInputStream1 = new FileOutputStream(file1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(file.exists());
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Properties prop = new Properties();
                try {
                    prop.load(fileInputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Properties prop1 = new Properties();
                Enumeration em = prop.keys();
                while (em.hasMoreElements()) {
                    String next = (String) em.nextElement();
                    System.out.println(next);
                    String value = (String) prop.get(next);
                    char[] chars = value.toCharArray();
                    boolean skip = false;
                    for (int i = 0; i < chars.length; i++) {
                        if (skip) {
                            if (chars[i] == ' ') {
                                skip = false;
                                continue;
                            }
                            continue;
                        }
                        if (chars[i] == '\\') {
                            skip = true;
                            continue;
                        }
                        if (Character.isLetter(chars[i])) {
                            if (chars[Math.max(i-1, 0)] != '&') {
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)a", "ᴀ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)b", "ʙ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)c", "ᴄ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)d", "ᴅ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)e", "ᴇ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)f", "ꜰ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)g", "ɢ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)h", "ʜ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)i", "ɪ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)j", "ᴊ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)k", "ᴋ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)l", "ʟ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)m", "ᴍ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)n", "ɴ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)o", "ᴏ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)p", "ᴘ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)q", "Q").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)r", "ʀ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)s", "ꜱ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)t", "ᴛ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)u", "ᴜ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)v", "ᴠ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)w", "ᴡ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)x", "x").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)y", "ʏ").charAt(0);
                                chars[i] = String.valueOf(chars[i])
                                        .replaceAll("(?i)z", "ᴢ").charAt(0);
                            }
                        }
                    }
                    prop1.setProperty(next, String.valueOf(chars));
                    System.out.println("§b" + String.valueOf(chars));
                }
                try {
                    prop1.store(fileInputStream1, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                */

                /*System.out.println("§aProcessing!");
                File file = new File(Bukkit.getPluginManager().getPlugin("EcoSkills").getDataFolder() + "/effects.yml");
                File file1 = new File(Cublex.getInstance().getDataFolder() + "/lang.yml");
                System.out.println(file.exists());
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
                YamlConfiguration yaml1 = YamlConfiguration.loadConfiguration(file1);
                for (String key : yaml.getKeys(false)) {
                    String value = yaml.getString(key+".description");
                    char[] chars = value.toCharArray();
                    boolean skip = false;
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] == '%' && chars[Math.max(i-1, 0)] != '%') {
                            skip = !skip;
                            continue;
                        }
                        if (skip) continue;
                        if (Character.isLetter(chars[i])) {
                            if (i-1 >= 0) {
                                if (chars[i-1] != '&') {
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)a", "ᴀ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)b", "ʙ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)c", "ᴄ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)d", "ᴅ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)e", "ᴇ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)f", "ꜰ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)g", "ɢ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)h", "ʜ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)i", "ɪ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)j", "ᴊ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)k", "ᴋ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)l", "ʟ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)m", "ᴍ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)n", "ɴ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)o", "ᴏ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)p", "ᴘ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)q", "Q").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)r", "ʀ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)s", "ꜱ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)t", "ᴛ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)u", "ᴜ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)v", "ᴠ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)w", "ᴡ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)x", "x").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)y", "ʏ").charAt(0);
                                    chars[i] = String.valueOf(chars[i])
                                            .replaceAll("(?i)z", "ᴢ").charAt(0);
                                }
                            }
                        }
                    }
                    yaml1.set(key, String.valueOf(chars));

                }
                try {
                    yaml1.save(file1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
            }
            else {
                player.sendMessage(ChatColor.RED + "No Permission.");
            }
            return true;
        }

        return false;
    }
}
