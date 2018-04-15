package de.Banko.RankAPI.BuyCraft;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Coins.Coins;

public class Command_Coins implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			
			if(args[0].equalsIgnoreCase("add")) {
				if(args[1] != null) {
					try {
						if(Integer.valueOf(args[2]) >= 0) {
							int coins = Integer.valueOf(args[2]);
							if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {			
								coins = Coins.getCoinsFromPlayer(Bukkit.getPlayer(args[1]).getName()) + coins;
								if(Coins.setCoinsToPlayer(Bukkit.getPlayer(args[1]).getName(), coins)) {
									sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
								} else {
									sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
								}
							} else {
								
								if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
									UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
									coins = Coins.getCoinsFromPlayer(uuid) + coins;
									if(Coins.setCoinsToPlayer(uuid, coins)) {
										sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
									} else {
										sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
									}
								} else {
									sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");
								}
							}
						} else {
							sender.sendMessage(Main.getPrefix() + "§cDas Argument 3 muss mindestens 0 sein!");
						}
					} catch (Exception e) {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 3 muss mindestens 0 sein!");
					}
				} else {
					sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
				}
			}
		} else {
			sender.sendMessage(Main.getPrefix() + "�cDieser Befehl ist nur von der Console ausf�hrbar!");
		}

		return true;
	}
	
}