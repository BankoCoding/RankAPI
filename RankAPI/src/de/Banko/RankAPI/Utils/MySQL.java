package de.Banko.RankAPI.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Groups.Rank;

public class MySQL {
	
	public static Statement getStatement() {
		try {
			if(connection != null && !connection.isClosed()) {
				return statement;
			} else {
				try {
					openConnection();
					return statement;	
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Statement statement;
	private static Connection connection;
    private static String host, username, password;
    private static int port;
 
    public MySQL() {
    	
    	getDatas();
    	
        try {     
        	openConnection();
        	statement = connection.createStatement();  
			try {
				update("create database Player;");
			} catch (Exception e) {
			}
            update("CREATE TABLE IF NOT EXISTS Player.PlayerRanks (UUID VARCHAR(255), Rank INT(255));");
            update("CREATE TABLE IF NOT EXISTS Player.Coins (UUID VARCHAR(255), Coins BIGINT);");
            update("CREATE TABLE IF NOT EXISTS Player.UUID (Name VARCHAR(255), UUID VARCHAR(255));");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void getDatas() {
    	FileConfiguration config = Main.getPlugin(Main.class).getConfig();
    	if(!config.isString("host") && !config.isInt("port") && !config.isString("username") && !config.isString("password")) {
    		config.set("host", "localhost");
    		config.set("port", Integer.valueOf(3306));
    		config.set("username", "user");
    		config.set("password", "password");
    		
    		Main.getPlugin(Main.class).saveConfig();
    		
    	} 
    	
		host = config.getString("host");
		port = config.getInt("port");
		username = config.getString("username");
		password = config.getString("password");
    	
    }
 
    public static void openConnection() throws SQLException, ClassNotFoundException {
    	
	    if (connection != null && !connection.isClosed()) {
	        return;
	    } else {
	
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        connection = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/", MySQL.username, MySQL.password);
	    
	    }
	    
	    for(Player all : Bukkit.getOnlinePlayers()) {
	    	Rank.ranks.put(all, Rank.getRankFromPlayer(all.getUniqueId()));
	    }
    
    }
    
    public void closeConnection() throws SQLException, ClassNotFoundException {
    	
	    if (connection != null && !connection.isClosed()) {
	        return;
	    } else {
	
	        connection.close();
	        
	    }
    
    }
    
    public static void update(String qry) {
    	try {
			if(connection != null && ! connection.isClosed()) {
				PreparedStatement ps;
				try {
					ps = connection.prepareStatement(qry);
					ps.executeUpdate();
				} catch (SQLException e) {
				}
			} else {
				try {
					openConnection();
				} catch (ClassNotFoundException e1) {
				}
				
				PreparedStatement ps;
				ps = connection.prepareStatement(qry);
				ps.executeUpdate();
				
			}
		} catch (SQLException e) {
		}
    }

}
