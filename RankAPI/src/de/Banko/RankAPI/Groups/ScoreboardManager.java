package de.Banko.RankAPI.Groups;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.Utils;

public class ScoreboardManager
{
	  public ScoreboardManager() {}
	  
	@SuppressWarnings("deprecation")
	public static void loadTeam(Scoreboard scoreBoard, Player player)
	  {
		  
		  	Rank rank = Rank.getRankFromPlayer(player.getName());
		    
		    Team team = scoreBoard.getTeam(getTeamName(rank));
		    if (team == null) {
		    	team = scoreBoard.registerNewTeam(getTeamName(rank));
			    team.setPrefix(Rank.getPrefix(rank));
		    }
		    
		    if (!team.hasPlayer(player)) {
		    	team.addPlayer(player);
		    }
	  }
	 
	@SuppressWarnings("deprecation")
	public static void loadSpectator(Scoreboard scoreBoard, Player player)
	  {
		    
		    Team team = scoreBoard.getTeam(getSpectatorTeamName());
		    if (team == null) {
		    	team = scoreBoard.registerNewTeam(getSpectatorTeamName());
			    team.setPrefix("§8[§c✞§8] §8");
		    } else {
		    	team.setPrefix("§8[§c✞§8] §8");
		    }
		    
		    if (!team.hasPlayer(player)) {
		    	team.addPlayer(player);
		    }
	  }
	
	  public static String getTeamName(Rank rank) {
			
			if(rank == Rank.OWNER) {
				return Utils.getBetterString("001" + rank.name(), 16);
			} else if(rank == Rank.DEVELOPER) {
				return Utils.getBetterString("002" + rank.name(), 16);
			} else if(rank == Rank.SUPPORTER) {
				return Utils.getBetterString("003" + rank.name(), 16);
			} else if(rank == Rank.PARTNER) {
				return Utils.getBetterString("004" + rank.name(), 16);
			} else if(rank == Rank.MEDIA) {
				return Utils.getBetterString("005" + rank.name(), 16);
			} else if(rank == Rank.DONATOR) {
				return Utils.getBetterString("006" + rank.name(), 16);
			} else if(rank == Rank.SPIELER) {
				return Utils.getBetterString("007" + rank.name(), 16);
			} else {
				MySQL mySQL = new MySQL();				
				Main.setMySQL(mySQL);
				
				if(rank == Rank.OWNER) {
					return Utils.getBetterString("001" + rank.name(), 16);
				} else if(rank == Rank.DEVELOPER) {
					return Utils.getBetterString("002" + rank.name(), 16);
				} else if(rank == Rank.SUPPORTER) {
					return Utils.getBetterString("003" + rank.name(), 16);
				} else if(rank == Rank.PARTNER) {
					return Utils.getBetterString("004" + rank.name(), 16);
				} else if(rank == Rank.MEDIA) {
					return Utils.getBetterString("005" + rank.name(), 16);
				} else if(rank == Rank.DONATOR) {
					return Utils.getBetterString("006" + rank.name(), 16);
				} else if(rank == Rank.SPIELER) {
					return Utils.getBetterString("007" + rank.name(), 16);
				} else {
					return Utils.getBetterString("007" + rank.name(), 16);
				}
		}
			
	}
  
  
	public static String getSpectatorTeamName() {
		return Utils.getBetterString("099Spec", 16);
	}
	  
}
