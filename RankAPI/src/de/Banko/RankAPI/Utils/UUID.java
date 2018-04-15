package de.Banko.RankAPI.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.Banko.RankAPI.Main;

public class UUID {
	
	public static java.util.UUID getUUID(String name) {
		
		ResultSet result;
		try {
			result = MySQL.getStatement().executeQuery("SELECT * FROM Player.UUID WHERE Name = '" + name + "';");
			while (result.next()) {
				java.util.UUID uuid = java.util.UUID.fromString(result.getString("UUID"));
				return uuid;
			}
		} catch (SQLException e) {
			MySQL mySQL = new MySQL();				
			Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "§eMySQL: §cDie Verbindung zur MySQL ist unterbrochen und wird neu aufgebaut!");
			Main.setMySQL(mySQL);
			
			try {
				result = MySQL.getStatement().executeQuery("SELECT * FROM Player.UUID WHERE Name = '" + name + "';");
				while (result.next()) {
					java.util.UUID uuid = java.util.UUID.fromString(result.getString("UUID"));
					return uuid;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	public static String getName(String uuid) {
		
		ResultSet result;
		try {
			result = MySQL.getStatement().executeQuery("SELECT * FROM Player.UUID WHERE UUID = '" + uuid + "';");
			while (result.next()) {
				String name = result.getString("Name");
				return name;
			}
		} catch (SQLException e) {
			MySQL mySQL = new MySQL();				
			Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "§eMySQL: §cDie Verbindung zur MySQL ist unterbrochen und wird neu aufgebaut!");
			Main.setMySQL(mySQL);
			
			try {
				result = MySQL.getStatement().executeQuery("SELECT * FROM Player.UUID WHERE UUID = '" + uuid + "';");
				while (result.next()) {
					String name = result.getString("Name");
					return name;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
}
