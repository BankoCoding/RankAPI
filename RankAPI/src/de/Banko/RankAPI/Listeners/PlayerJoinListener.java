package de.Banko.RankAPI.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.Banko.RankAPI.Groups.Rank;
import de.Banko.RankAPI.Groups.Tablist;
import de.Banko.RankAPI.Utils.MySQL;
import de.Banko.RankAPI.Utils.UUID;
import de.Banko.RankAPI.Utils.Utils;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		if(UUID.getUUID(player.getName()) == null) {
			MySQL.update("INSERT INTO Player.UUID (Name, UUID) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "');");	
			MySQL.update("INSERT INTO Player.PlayerRanks (UUID, Rank) VALUES ('" + player.getUniqueId().toString() + "', '0');");	
			Rank.ranks.put(player, Rank.SPIELER);
		} else {
			Rank.ranks.put(player, Rank.getRankFromPlayer(player.getName()));
		}
		
		Rank rank = Rank.getRankFromPlayer(player.getName());
		
		player.setScoreboard(Tablist.getScoreboard());
		player.setDisplayName(Utils.getBetterString(Rank.getPrefix(rank) + player.getName(), 16));
		
//		if(rank != Rank.LEITUNG) {
//			if(player.isOp()) {
//				player.sendMessage(Main.getPrefix() + "§cDein §4OP §cwurde entfernt, da du keine Leitung mehr bist!");
//				player.setOp(false);
//			}
//		} else {
//			player.setOp(true);
//		}
		
		Tablist.getScoreboard().getTeam(Tablist.getTeamOfRank(rank).getName()).addEntry(player.getName());
		
	}
	
}
