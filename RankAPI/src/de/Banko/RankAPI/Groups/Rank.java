package de.Banko.RankAPI.Groups;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.UUID;
import net.md_5.bungee.api.ChatColor;

public enum Rank {

	OWNER,
	DEVELOPER,
	SUPPORTER,
	PARTNER,
	MEDIA,
	DONATOR,
	SPIELER;
	
	public static HashMap<Player, Rank> ranks = new HashMap<>();
	public static HashMap<java.util.UUID, Rank> gettingRank = new HashMap<>();
	
	public static String getPrefix(Rank rank) {
		
		if(rank == Rank.SPIELER) {
			return Rank.getRankColor(rank) + "";
		} else if(rank == Rank.DONATOR) {
			return Rank.getRankColor(rank) + "Donator §8» " + Rank.getRankColor(rank) + "";
		} else if(rank == Rank.MEDIA) {
			return Rank.getRankColor(rank) + "Media §8» " + Rank.getRankColor(rank) + "";
		} else if(rank == Rank.PARTNER) {
			return Rank.getRankColor(rank) + "Partner §8» " + Rank.getRankColor(rank) + "";
		} else if(rank == Rank.SUPPORTER) {
			return Rank.getRankColor(rank) + "Sup §8» " + Rank.getRankColor(rank) + "";
		} else if(rank == Rank.DEVELOPER) {
			return Rank.getRankColor(rank) + "Dev §8» " + Rank.getRankColor(rank) + "";
		} else if(rank == Rank.OWNER) {
			return Rank.getRankColor(rank) + "Owner §8» " + Rank.getRankColor(rank);
		} else {
			
			MySQL mySQL = new MySQL();				
			Main.setMySQL(mySQL);
			
			if(rank == Rank.SPIELER) {
				return Rank.getRankColor(rank) + "";
			} else if(rank == Rank.MEDIA) {
				return Rank.getRankColor(rank) + "Media §8» " + Rank.getRankColor(rank) + "";
			} else if(rank == Rank.PARTNER) {
				return Rank.getRankColor(rank) + "Partner §8» " + Rank.getRankColor(rank) + "";
			} else if(rank == Rank.SUPPORTER) {
				return Rank.getRankColor(rank) + "Sup §8» " + Rank.getRankColor(rank) + "";
			} else if(rank == Rank.DEVELOPER) {
				return Rank.getRankColor(rank) + "Dev §8» " + Rank.getRankColor(rank) + "";
			} else if(rank == Rank.OWNER) {
				return Rank.getRankColor(rank) + "Owner §8» " + Rank.getRankColor(rank);
			} else {
				return Rank.getRankColor(rank) + "";
			}
		}
		
	}
	
	public static ChatColor getChatColor(Rank rank) {
		
		if(rank == Rank.SPIELER) {
			return ChatColor.WHITE;
		} else if(rank == Rank.DONATOR) {
			return ChatColor.WHITE;
		} else if(rank == Rank.MEDIA) {
			return ChatColor.WHITE;
		} else if(rank == Rank.PARTNER) {
			return ChatColor.WHITE;
		} else if(rank == Rank.SUPPORTER) {
			return ChatColor.YELLOW;
		} else if(rank == Rank.DEVELOPER) {
			return ChatColor.YELLOW;
		} else if(rank == Rank.OWNER) {
			return ChatColor.YELLOW;
		} else {
			
			MySQL mySQL = new MySQL();				
			Main.setMySQL(mySQL);
			
			if(rank == Rank.SPIELER) {
				return ChatColor.WHITE;
			} else if(rank == Rank.DONATOR) {
				return ChatColor.WHITE;
			} else if(rank == Rank.MEDIA) {
				return ChatColor.WHITE;
			} else if(rank == Rank.PARTNER) {
				return ChatColor.WHITE;
			} else if(rank == Rank.SUPPORTER) {
				return ChatColor.YELLOW;
			} else if(rank == Rank.DEVELOPER) {
				return ChatColor.YELLOW;
			} else if(rank == Rank.OWNER) {
				return ChatColor.YELLOW;
			} else {
				return ChatColor.GRAY;
			}
		}
		
	}
	
	public static ChatColor getRankColor(Rank rank) {
		if(rank == Rank.SPIELER) {
			return ChatColor.GRAY;
		} else if(rank == Rank.DONATOR) {
			return ChatColor.GOLD;
		} else if(rank == Rank.MEDIA) {
			return ChatColor.DARK_PURPLE;
		} else if(rank == Rank.PARTNER) {
			return ChatColor.DARK_GREEN;
		} else if(rank == Rank.SUPPORTER) {
			return ChatColor.BLUE;
		} else if(rank == Rank.DEVELOPER) {
			return ChatColor.AQUA;
		} else if(rank == Rank.OWNER) {
			return ChatColor.DARK_RED;
		} else {
			
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			if(rank == Rank.SPIELER) {
				return ChatColor.GRAY;
			} else if(rank == Rank.DONATOR) {
				return ChatColor.GOLD;
			} else if(rank == Rank.MEDIA) {
				return ChatColor.DARK_PURPLE;
			} else if(rank == Rank.PARTNER) {
				return ChatColor.DARK_GREEN;
			} else if(rank == Rank.SUPPORTER) {
				return ChatColor.BLUE;
			} else if(rank == Rank.DEVELOPER) {
				return ChatColor.AQUA;
			} else if(rank == Rank.OWNER) {
				return ChatColor.DARK_RED;
			} else {
				return ChatColor.GRAY;
			}			
		}
	}
	
	public static boolean setRankToPlayer(String name, Rank rank) {
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.getName().equalsIgnoreCase(name)) {
				ranks.put(all, rank);
				MySQL.update("UPDATE Player.PlayerRanks SET Rank = '" + Rank.getRankID(rank) + "' WHERE UUID = '" + all.getUniqueId().toString() + "';");
				return true;
			}
		}
		
		if(setRankToPlayer(UUID.getUUID(name), rank)) {
			return true;
		}
		
		return false;
		
	}
	
	public static boolean setRankToPlayer(java.util.UUID uuid, Rank rank) {
		
		if(uuid != null) {

			MySQL.update("UPDATE Player.PlayerRanks SET Rank = '" + Rank.getRankID(rank) + "' WHERE UUID = '" + uuid.toString() + "';");
			return true;
			
		}
		
		return false;
		
	}
	
	public static Rank getRankFromPlayer(String name) {

		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.getName().equalsIgnoreCase(name)) {
				ResultSet result;
				try {
					result = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE UUID = '" + all.getUniqueId().toString() + "';");
					while (result.next()) {
						Rank rank = Rank.getRankFromID(result.getInt("Rank"));
						return rank;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

		if(UUID.getUUID(name) != null) {
			java.util.UUID uuid = UUID.getUUID(name);
			ResultSet result;
			try {
				result = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE UUID = '" + uuid.toString() + "';");
				while (result.next()) {
					Rank rank = Rank.getRankFromID(result.getInt("Rank"));
					return rank;
				}
			} catch (SQLException e) {
				MySQL mySQL = new MySQL();				
	
				Main.setMySQL(mySQL);
				try {
					result = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE UUID = '" + uuid.toString() + "';");
					while (result.next()) {
						Rank rank = Rank.getRankFromID(result.getInt("Rank"));
						return rank;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return Rank.SPIELER;
		
	}
	
	public static Rank getRankFromPlayer(java.util.UUID uuid) {
		
		if(uuid != null) {
			ResultSet res;
			try {
				res = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE UUID = '" + uuid.toString() + "';");
				res.next();			
				if(res.getInt("Rank") >= 0) {
					return Rank.getRankFromID(res.getInt("Rank"));				
				}
			} catch (SQLException e) {
				MySQL mySQL = new MySQL();				
	
				Main.setMySQL(mySQL);
				try {
					res = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE UUID = '" + uuid.toString() + "';");
					res.next();			
					if(res.getInt("Rank") >= 0) {
						return Rank.getRankFromID(res.getInt("Rank"));				
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return Rank.SPIELER;
		
	}
	
	public static List<Player> getPlayersOfRank(Rank rank) {
		
		List<String> onlineUUIDS = new ArrayList<>();
		List<Player> playerList = new ArrayList<>();

		for(Player all : Bukkit.getOnlinePlayers()) {
			onlineUUIDS.add(all.getUniqueId().toString());
		}
		
		ResultSet res;
		try {
			res = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE Rank = '" + Rank.getRankID(rank) + "';");		
			while(res.next()) {
				if(res.getString("UUID") != null) {	
					if(onlineUUIDS.contains(res.getString("UUID"))) {
						playerList.add(Bukkit.getPlayer(java.util.UUID.fromString(res.getString("UUID"))));
					}
				}
			}
		} catch (SQLException e) {				
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			try {
				res = MySQL.getStatement().executeQuery("SELECT * FROM Player.PlayerRanks WHERE Rank = '" + Rank.getRankID(rank) + "';");		
				while(res.next()) {
					if(res.getString("UUID") != null) {	
						if(onlineUUIDS.contains(res.getString("UUID"))) {
							playerList.add(Bukkit.getPlayer(java.util.UUID.fromString(res.getString("UUID"))));
						}
					}
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return playerList;
		
	}
	
	public static Rank getRankAbove(Rank rank) {
		
		if(rank == Rank.SPIELER) {
			return Rank.DONATOR;
		} else if(rank == Rank.DONATOR) {
			return Rank.MEDIA;
		} else if(rank == Rank.MEDIA) {
			return Rank.PARTNER;
		} else if(rank == Rank.PARTNER) {
			return Rank.SUPPORTER;
		} else if(rank == Rank.SUPPORTER) {
			return Rank.DEVELOPER;
		} else if(rank == Rank.DEVELOPER) {
			return Rank.OWNER;
		} else if(rank == Rank.OWNER) {
			return null;
		} else {
			
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			if(rank == Rank.SPIELER) {
				return Rank.DONATOR;
			} else if(rank == Rank.DONATOR) {
				return Rank.MEDIA;
			} else if(rank == Rank.MEDIA) {
				return Rank.PARTNER;
			} else if(rank == Rank.PARTNER) {
				return Rank.SUPPORTER;
			} else if(rank == Rank.SUPPORTER) {
				return Rank.DEVELOPER;
			} else if(rank == Rank.DEVELOPER) {
				return Rank.OWNER;
			} else if(rank == Rank.OWNER) {
				return null;
			} else {
				return Rank.SPIELER;
			}
		}
		
	}
	
	public static int getRankID(Rank rank) {
		
		int id = 0;
		
		if(rank == Rank.SPIELER) {
			id = 0;
		} else if(rank == Rank.DONATOR) {
			id = 1;
		} else if(rank == Rank.MEDIA) {
			id = 2;
		} else if(rank == Rank.PARTNER) {
			id = 3;
		} else if(rank == Rank.SUPPORTER) {
			id = 4;
		} else if(rank == Rank.DEVELOPER) {
			id = 5;
		} else if(rank == Rank.OWNER) {
			id = 6;
		} else {
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			if(rank == Rank.SPIELER) {
				id = 0;
			} else if(rank == Rank.DONATOR) {
				id = 1;
			} else if(rank == Rank.MEDIA) {
				id = 2;
			} else if(rank == Rank.PARTNER) {
				id = 3;
			} else if(rank == Rank.SUPPORTER) {
				id = 4;
			} else if(rank == Rank.DEVELOPER) {
				id = 5;
			} else if(rank == Rank.OWNER) {
				id = 6;
			} else {
				id = 0;
			}
		}
		return id;
	}
	
	public static Rank getRankFromID(int id) {
		
		Rank rank = Rank.SPIELER;
		
		if(id == 0) {
			rank = Rank.SPIELER;
		} else if(id == 1) {
			rank = Rank.DONATOR;
		} else if(id == 2) {
			rank = Rank.MEDIA;
		} else if(id == 3) {
			rank = Rank.PARTNER;
		} else if(id == 4) {
			rank = Rank.SUPPORTER;
		} else if(id == 5) {
			rank = Rank.DEVELOPER;
		} else if(id == 6) {
			rank = Rank.OWNER;
		} else {
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			if(id == 0) {
				rank = Rank.SPIELER;
			} else if(id == 1) {
				rank = Rank.DONATOR;
			} else if(id == 2) {
				rank = Rank.MEDIA;
			} else if(id == 3) {
				rank = Rank.PARTNER;
			} else if(id == 4) {
				rank = Rank.SUPPORTER;
			} else if(id == 5) {
				rank = Rank.DEVELOPER;
			} else if(id == 6) {
				rank = Rank.OWNER;
			} else {
				rank = Rank.SPIELER;
			}
		}
		return rank;
	}
	
	public static boolean isRankAble(Rank rank, Rank isAbove) {
		
		int id = 0;
		
		int needed = 0;
		
		id = getRankID(rank);
		
		needed = getRankID(isAbove);
		
		if(needed <= id) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
