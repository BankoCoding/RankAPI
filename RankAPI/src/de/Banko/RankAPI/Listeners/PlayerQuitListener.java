package de.Banko.RankAPI.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Banko.RankAPI.Groups.Rank;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if(Rank.ranks.containsKey(event.getPlayer())) {			
			Rank.ranks.remove(event.getPlayer());			
		}
	}

	@EventHandler
	public void onKick(PlayerKickEvent event) {
		if(Rank.ranks.containsKey(event.getPlayer())) {			
			Rank.ranks.remove(event.getPlayer());			
		}
	}
}
