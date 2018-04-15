package de.Banko.RankAPI;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.Banko.RankAPI.Commands.Command_Coins;
import de.Banko.RankAPI.Commands.Command_Rank;
import de.Banko.RankAPI.Groups.Rank;
import de.Banko.RankAPI.Groups.Tablist;
import de.Banko.RankAPI.Listeners.PlayerJoinListener;
import de.Banko.RankAPI.Listeners.PlayerQuitListener;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.UUID;
import de.Banko.RankAPI.Utils.Utils;

public class Main extends JavaPlugin {

	private static MySQL mySQL;

	public static String getPrefix() {
		return "§8[§6System§8] §7";
	}
	
	public static MySQL getMySQL() {
		return mySQL;
	}
	
	public static void setMySQL(MySQL mySQL) {
		Main.mySQL = mySQL;
	}
	
	public void onEnable() {
		
		MySQL mySQL = new MySQL();
		Main.mySQL = mySQL;
		PluginManager pm = Bukkit.getPluginManager();
		
		if(MySQL.getStatement() != null) {
			try {
				Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §aErfolgreich zur Datenbank verbunden!");
			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §eFehler beim Verbinden zur Datenbank!");
				pm.disablePlugin(this);
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §eFehler beim Verbinden zur Datenbank!");
			pm.disablePlugin(this);
		}
		
		
		new Tablist();
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		this.getCommand("rank").setExecutor(new Command_Rank());
		this.getCommand("coins").setExecutor(new Command_Coins());
		this.getCommand("buycraftRank").setExecutor(new de.Banko.RankAPI.BuyCraft.Command_Rank());
		this.getCommand("buycraftCoins").setExecutor(new de.Banko.RankAPI.BuyCraft.Command_Coins());
		
		Bukkit.getConsoleSender().sendMessage(getPrefix() + "Die §c§lRankAPI §7wurde aktiviert!");
			
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.setDisplayName(Utils.getBetterString(Rank.getPrefix(Rank.getRankFromPlayer(all.getName())) + all.getName(), 16));
			all.setScoreboard(Tablist.getScoreboard());
			if(UUID.getUUID(all.getName()) == null) {
				MySQL.update("INSERT INTO Player.UUID (Name, UUID) VALUES ('" + all.getName().toLowerCase() + "', '" + all.getUniqueId().toString() + "');");	
				MySQL.update("INSERT INTO Player.PlayerRanks (UUID, Rank) VALUES ('" + all.getUniqueId().toString() + "', '0');");
			}
		}
		
	}

	public void onDisable() {
		
		if(MySQL.getStatement() != null) {
			try {
				MySQL.getStatement().close();
				Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §aErfolgreich die Verbindung der Datenbank beendet!");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §eFehler beim Beenden der Verbindung!");
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(getPrefix() + "§eMySQL: §eFehler beim Beenden der Verbindung!");
		}
		
	}

}
