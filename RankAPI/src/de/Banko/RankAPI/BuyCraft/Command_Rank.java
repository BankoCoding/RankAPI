package de.Banko.RankAPI.BuyCraft;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Groups.Rank;

public class Command_Rank implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("set")) {
					if(args[1] != null) {
						Rank rank;
						try {
							if(Integer.valueOf(args[2]) >= 0) {
								rank = Rank.getRankFromID(Integer.valueOf(args[2]));
							} else {
								rank = Rank.valueOf(args[2].toUpperCase());
							}
						} catch (Exception e) {
							rank = Rank.valueOf(args[2].toUpperCase());
						}
						if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
							Player target = Bukkit.getPlayer(args[1]);
							if(rank == Rank.DONATOR && !Rank.isRankAble(Rank.getRankFromPlayer(target.getName()), Rank.MEDIA)) {
								if(Rank.setRankToPlayer(target.getUniqueId(), rank)) {
									sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(rank) + rank.name() + " §7erhalten");
									target.kickPlayer(Main.getPrefix() + "§cDein Rang wurde über §6§lBuyCraft §cgeändert"
											+ "\n§cBitte betrete DIREKT das Netzwerk wieder!");
									Rank.setRankToPlayer(target.getUniqueId(), rank);								
								} else {
									sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
								}
							} else {
								sender.sendMessage(" ");
								sender.sendMessage(Main.getPrefix() + "§cDer Rang §7(" + Rank.getRankColor(rank) + rank.name() + "§7) §cfür §e" + Bukkit.getPlayer(args[1]).getName() + " §ckonnte nicht gesetzt werden, da er einen höheren Rang besitzt");
								sender.sendMessage(" ");
							}
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
								if(rank == Rank.DONATOR && !Rank.isRankAble(Rank.getRankFromPlayer(uuid), Rank.MEDIA)) {
									if(Rank.setRankToPlayer(uuid, rank)) {
										sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(rank) + rank.name() + " §7erhalten");
									} else {
										sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
									}
								} else {
									sender.sendMessage(" ");
									sender.sendMessage(Main.getPrefix() + "§cDer Rang §7(" + Rank.getRankColor(rank) + rank.name() + "§7) §cfür §e" + Bukkit.getPlayer(args[1]).getName() + " §ckonnte nicht gesetzt werden, da er einen höheren Rang besitzt");
									sender.sendMessage(" ");
								}
							} else {
								sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");
							}
						}
					} else {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
					}
				}
			}
		} else {
			sender.sendMessage(Main.getPrefix() + "§cDieser Befehl ist nur von der Console ausführbar!");
		}
		return false;
	}
	
}
