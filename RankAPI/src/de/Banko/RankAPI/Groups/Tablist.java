package de.Banko.RankAPI.Groups;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.Utils;

public class Tablist {
	
	static Scoreboard scoreboard;
	static Objective objective;
	
	public static Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public static Objective getObjective() {
		return objective;
	}
	
	public Tablist() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
		Tablist.scoreboard = scoreboard;
		Tablist.objective = objective;
		
		for(Rank rank : Rank.values()) {
			Team team = getTeamOfRank(rank);
			team.setPrefix(Rank.getPrefix(rank) + "");
			for(Player player : Rank.getPlayersOfRank(rank)) {
				//Bukkit.broadcastMessage(player.getName() + ": " + Rank.getChatColor(rank) + rank.name() + " §8| §bPrefix§7: " + Rank.getPrefix(rank));
				team.addEntry(player.getName());
			}
			
		}
		objective.setDisplayName("Ranks");
		Tablist.scoreboard = scoreboard;
		Tablist.objective = objective;
	}
	
	public static Team getTeamOfRank(Rank rank) {
		
		if(rank == Rank.OWNER) {
			if(getScoreboard().getTeam(Utils.getBetterString(Utils.getBetterString("001" + rank.name(), 16), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("001" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("001" + rank.name(), 16));
			}
		} else if(rank == Rank.DEVELOPER) {
			if(getScoreboard().getTeam(Utils.getBetterString("002" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("002" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("002" + rank.name(), 16));
			}
		} else if(rank == Rank.SUPPORTER) {
			if(getScoreboard().getTeam(Utils.getBetterString("003" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("003" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("003" + rank.name(), 16));
			}
		} else if(rank == Rank.PARTNER) {
			if(getScoreboard().getTeam(Utils.getBetterString("004" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("004" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("004" + rank.name(), 16));
			}
		} else if(rank == Rank.MEDIA) {
			if(getScoreboard().getTeam(Utils.getBetterString("005" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("005" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("005" + rank.name(), 16));
			}
		} else if(rank == Rank.DONATOR) {
			if(getScoreboard().getTeam(Utils.getBetterString("006" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("006" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("006" + rank.name(), 16));
			}
		} else if(rank == Rank.SPIELER) {
			if(getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16)) != null) {
				return getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16));
			} else {
				return getScoreboard().registerNewTeam(Utils.getBetterString("007" + rank.name(), 16));
			}
		} else {
			MySQL mySQL = new MySQL();				

			Main.setMySQL(mySQL);
			
			if(rank == Rank.OWNER) {
				if(getScoreboard().getTeam(Utils.getBetterString(Utils.getBetterString("001" + rank.name(), 16), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("001" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("001" + rank.name(), 16));
				}
			} else if(rank == Rank.DEVELOPER) {
				if(getScoreboard().getTeam(Utils.getBetterString("002" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("002" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("002" + rank.name(), 16));
				}
			} else if(rank == Rank.SUPPORTER) {
				if(getScoreboard().getTeam(Utils.getBetterString("003" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("003" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("003" + rank.name(), 16));
				}
			} else if(rank == Rank.PARTNER) {
				if(getScoreboard().getTeam(Utils.getBetterString("004" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("004" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("004" + rank.name(), 16));
				}
			} else if(rank == Rank.MEDIA) {
				if(getScoreboard().getTeam(Utils.getBetterString("005" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("005" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("005" + rank.name(), 16));
				}
			} else if(rank == Rank.DONATOR) {
				if(getScoreboard().getTeam(Utils.getBetterString("006" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("006" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("006" + rank.name(), 16));
				}
			} else if(rank == Rank.SPIELER) {
				if(getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("007" + rank.name(), 16));
				}
			} else {
				if(getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16)) != null) {
					return getScoreboard().getTeam(Utils.getBetterString("007" + rank.name(), 16));
				} else {
					return getScoreboard().registerNewTeam(Utils.getBetterString("007" + rank.name(), 16));
				}
			}
			
		}
		
	}
	
	public static Team getSpectatorTeam() {
		if(getScoreboard().getTeam(Utils.getBetterString("099Spec", 16)) != null) {
			Team team = getScoreboard().getTeam(Utils.getBetterString("099Spec", 16));
			team.setCanSeeFriendlyInvisibles(true);
			team.setPrefix("§8[§c✞§8] §8");
			return getScoreboard().getTeam(Utils.getBetterString("099Spec", 16));
		} else {
			return getScoreboard().registerNewTeam(Utils.getBetterString("099Spec", 16));
		}
	}
	
}
