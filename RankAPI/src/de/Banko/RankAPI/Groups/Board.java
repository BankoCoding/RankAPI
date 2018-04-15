package de.Banko.RankAPI.Groups;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {

	public static Player player;
	public static String name;
	public static Scoreboard scoreBoard;
	public static DisplaySlot displaySlot;
	public static Objective objective;
	
	public static ArrayList<BoardTeam> boardTeams;
		
	public Board(Player player) {
		Board.player = player;
		name = "   " + ChatColor.RED + ChatColor.BOLD + "NONAME" + ChatColor.RESET + " ";
		displaySlot = DisplaySlot.SIDEBAR;
				
		scoreBoard = getScoreboard(player);
		boardTeams = new ArrayList<>();
	}
	
	public static Scoreboard getScoreboard(Player player) {		
		Scoreboard board = player.getScoreboard();
		if(board == null) {			
			board = Bukkit.getScoreboardManager().getNewScoreboard();
		}
		
		objective = board.getObjective("aaa");
		if(objective == null) {
			objective = board.registerNewObjective("aaa", "bbb");
			objective.setDisplayName(getName());
			objective.setDisplaySlot(getDisplaySlot());
		} else {
			objective.setDisplayName(getName());
			objective.setDisplaySlot(getDisplaySlot());
		}
		return board;
	}
	
	public static void resetScoreboard() {
				
		scoreBoard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		objective = scoreBoard.getObjective("aaa");
		if(objective == null) {
			objective = scoreBoard.registerNewObjective("aaa", "bbb");
			objective.setDisplayName(getName());
			objective.setDisplaySlot(getDisplaySlot());
		} else {
			objective.setDisplayName(getName());
			objective.setDisplaySlot(getDisplaySlot());
		}
	}
			
	public static void sendScoreboard() {
		player.setScoreboard(scoreBoard);
	}
	
	public static void updateScoreboard() {
		
	}
	
	public static void addBoardTeam(BoardTeam boardTeam) {
		boardTeams.add(boardTeam);
	}
	
	public static void updateTeams() {
		
		for(BoardTeam boardTeam : boardTeams) {		
			
			Team team = scoreBoard.getTeam(boardTeam.getName());			
			if(team == null) {
				team = scoreBoard.registerNewTeam(boardTeam.getName());
			}
			
			if(!team.hasEntry(boardTeam.getEntry())) {
				team.addEntry(boardTeam.getEntry());
			}
						
			if(boardTeam.getPrefix() != null) {
				team.setPrefix(boardTeam.getPrefix());
			}
			
			if(boardTeam.getSuffix() != null) {
				team.setSuffix(boardTeam.getSuffix());
			}	
			
			objective.getScore(boardTeam.getEntry()).setScore(boardTeam.getScore());			
		}				
	}
	
	public static void resetTeams() {
		boardTeams = new ArrayList<>();
	}
	
	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		Board.player = player;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Board.name = name;
		objective.setDisplayName(name);
	}

	public static Scoreboard getScoreBoard() {
		return scoreBoard;
	}

	public static void setScoreBoard(Scoreboard scoreBoard) {
		Board.scoreBoard = scoreBoard;
	}

	public static DisplaySlot getDisplaySlot() {
		return displaySlot;
	}

	public static void setDisplaySlot(DisplaySlot displaySlot) {
		Board.displaySlot = displaySlot;
		objective.setDisplaySlot(displaySlot);
	}

	public static Objective getObjective() {
		return objective;
	}

	public static void setObjective(Objective objective) {
		Board.objective = objective;
	}

	public static ArrayList<BoardTeam> getBoardTeams() {
		return boardTeams;
	}

	public static void setBoardTeams(ArrayList<BoardTeam> boardTeams) {
		Board.boardTeams = boardTeams;
	}

	public static void addonUpdateTeams() {
		
	}

}
