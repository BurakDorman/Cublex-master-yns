package me.yuuns.cublex.economy;

import me.yuuns.cublex.database.YamlManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Objects;

public class Coins implements Economy {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "Cublex";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return Math.round(v) + "";
    }

    @Override
    public String currencyNamePlural() {
        return "Coins";
    }

    @Override
    public String currencyNameSingular() {
        return "Coin";
    }

    @Override
    public boolean hasAccount(String s) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).get("coins") != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return YamlManager.getPlayerYaml(offlinePlayer.getUniqueId()).get("coins") != null;
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).get("coins") != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return hasAccount(offlinePlayer);
    }

    @Override
    public double getBalance(String s) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).getInt("coins");
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return YamlManager.getPlayerYaml(offlinePlayer.getUniqueId()).getInt("coins");
    }

    @Override
    public double getBalance(String s, String s1) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).getInt("coins");
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return getBalance(offlinePlayer);
    }

    @Override
    public boolean has(String s, double v) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).getInt("coins") >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return YamlManager.getPlayerYaml(offlinePlayer.getUniqueId()).getInt("coins") >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId()).getInt("coins") >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return YamlManager.getPlayerYaml(offlinePlayer.getUniqueId()).getInt("coins") >= v;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        if (!hasAccount(offlinePlayer)) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "No account.");
        }
        int coins = (int) getBalance(offlinePlayer);
        if (coins < v) {
            return new EconomyResponse(0, coins, EconomyResponse.ResponseType.FAILURE, "Insufficient balance.");
        }
        YamlManager.setValue(offlinePlayer.getUniqueId(), "coins", coins-v);
        return new EconomyResponse(v, coins, EconomyResponse.ResponseType.SUCCESS, "Success.");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return withdrawPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        if (!hasAccount(offlinePlayer)) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "No account.");
        }
        int coins = (int) getBalance(offlinePlayer);
        YamlManager.setValue(offlinePlayer.getUniqueId(), "coins", coins+v);
        return new EconomyResponse(v, coins, EconomyResponse.ResponseType.SUCCESS, "Success.");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        if (!hasAccount(offlinePlayer)) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "No account.");
        }
        int coins = (int) getBalance(offlinePlayer);
        return new EconomyResponse(v, coins, EconomyResponse.ResponseType.SUCCESS, "Success.");
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId());
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        YamlManager.getPlayerYaml(offlinePlayer.getUniqueId());
        return true;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        YamlManager.getPlayerYaml(Objects.requireNonNull(Bukkit.getPlayer(s)).getUniqueId());
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        YamlManager.getPlayerYaml(offlinePlayer.getUniqueId());
        return true;
    }
}
