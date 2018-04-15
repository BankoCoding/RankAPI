package de.Banko.RankAPI.Coins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.UUID;

public class Coins {

	public static HashMap<Player, Integer> coins = new HashMap<>();
	
	public static boolean setCoinsToPlayer(String name, int coins) {
		
		Player player = Bukkit.getPlayer(name);
		Coins.coins.put(player, coins);
		if(getCoinsFromPlayer(UUID.getUUID(name)) >= 0) {
			MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + player.getUniqueId().toString() + "';");
		}
		
		MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + player.getUniqueId().toString() + "', '" + coins + "');");
		return true;
		
	}
	
	@SuppressWarnings("unused")
	public static boolean setCoinsToPlayer(java.util.UUID uuid, int coins) {
		
		if(uuid != null) {

			if(getCoinsFromPlayer(uuid) >= 0) {
				MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
			}
			
			MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + uuid.toString() + "', '" + coins + "');");
			return true;
			
		}
		
		MySQL mySQL = new MySQL();
		
		Main.setMySQL(mySQL);
		
		if(uuid != null) {

			if(getCoinsFromPlayer(uuid) >= 0) {
				MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
			}
			
			MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + uuid.toString() + "', '" + coins + "');");
			return true;
			
		}
		
		return false;
		
	}
	
	public static boolean addCoinsToPlayer(String name, int coins) {
		
		Player player = Bukkit.getPlayer(name);
		Coins.coins.put(player, coins);
		int oldCoins = getCoinsFromPlayer(name);
		if(getCoinsFromPlayer(UUID.getUUID(name)) >= 0) {
			MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + player.getUniqueId().toString() + "';");
		}

		MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + name + "', '" + (coins + oldCoins) + "');");
		return true;
		
	}
	
	@SuppressWarnings("unused")
	public static boolean addCoinsToPlayer(java.util.UUID uuid, int coins) {
		
		if(uuid != null) {
			
			int oldCoins = getCoinsFromPlayer(uuid);
			if(getCoinsFromPlayer(uuid) >= 0) {
				MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
			}
			
			MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + uuid.toString() + "', '" + (coins + oldCoins) + "');");
			return true;
			
		}
		
		MySQL mySQL = new MySQL();
		
		Main.setMySQL(mySQL);
		
		if(uuid != null) {

			int oldCoins = getCoinsFromPlayer(uuid);
			if(getCoinsFromPlayer(uuid) >= 0) {
				MySQL.update("DELETE FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
			}
			
			MySQL.update("INSERT INTO Player.Coins (UUID, Coins) VALUES ('" + uuid.toString() + "', '" + (coins + oldCoins) + "');");
			return true;
			
		}
		
		return false;
		
	}
	
	public static int getCoinsFromPlayer(String name) {
		
		if(coins != null && !coins.isEmpty()) {
			if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
				if(coins.containsKey(Bukkit.getPlayer(name))) {
					return coins.get(Bukkit.getPlayer(name));
				}
			}
		}
		

		if(UUID.getUUID(name) != null) {
			java.util.UUID uuid = UUID.getUUID(name);
			return getCoinsFromPlayer(uuid);
		}
		
		return 0;
		
	}
	
	public static int getCoinsFromPlayer(java.util.UUID uuid) {
		
		if(uuid != null) {
			ResultSet res;
			try {
				res = MySQL.getStatement().executeQuery("SELECT * FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
				res.next();			
				if(res.getInt("Coins") >= 0) {
					return res.getInt("Coins");				
				}
			} catch (SQLException e) {
				MySQL mySQL = new MySQL();

				Main.setMySQL(mySQL);
				
				if(uuid != null) {
					try {
						res = MySQL.getStatement().executeQuery("SELECT * FROM Player.Coins WHERE UUID = '" + uuid.toString() + "';");
						res.next();			
						if(res.getInt("Coins") >= 0) {
							return res.getInt("Coins");				
						}
					} catch (SQLException e1) { }
				}
			}
		}
		
		
		return 0;
		
	}
	
}
