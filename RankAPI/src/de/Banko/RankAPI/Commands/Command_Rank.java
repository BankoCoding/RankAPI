package de.Banko.RankAPI.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Groups.Rank;
import de.Banko.RankAPI.Groups.Tablist;
import de.Banko.RankAPI.Utils.Utils;

public class Command_Rank implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender || (sender instanceof Player && (Rank.isRankAble(Rank.getRankFromPlayer(sender.getName()), Rank.OWNER)))) {
			
			if(args.length == 0)  {
				sendUsage(sender);
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("list")) {
					String ranks = "";
					for(Rank rank : Rank.values()) { 
						ranks = ranks + "§8» " + Rank.getRankColor(rank) + rank.name() + " §8(§9ID: " + Rank.getRankColor(rank) + Rank.getRankID(rank) + "§8)" + "\n";
					}
					sender.sendMessage(Main.getPrefix() + "§4Rank §8〡 §6Ränge" + "\n" + ranks);
				} else {
					sendUsage(sender);
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("get")) {
					if(args[1] != null) {
						if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
							Rank rank = Rank.getRankFromPlayer(Bukkit.getPlayer(args[1]).getName());
							sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat den Rang " + Rank.getRankColor(rank) + rank.name());
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								Rank rank = Rank.getRankFromPlayer(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]));
								sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat den Rang " + Rank.getRankColor(rank) + rank.name());
							} else {								
								sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");								
							}
							
						}
					} else {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
					}
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(args[1] != null) {
						if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
							if(sender instanceof Player) {
								if(!Rank.isRankAble(Rank.getRankFromPlayer(Bukkit.getPlayer(args[1]).getName()), Rank.OWNER) && Rank.getRankFromPlayer(sender.getName()) != Rank.OWNER) {
									sender.sendMessage(Main.getPrefix() + "§cDu darfst diesem Spieler den Rang nicht entfernen!");
								} else {
									if(Rank.setRankToPlayer(Bukkit.getPlayer(args[1]).getName(), Rank.SPIELER)) {
										sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(Rank.SPIELER) + Rank.SPIELER.name() + " §7erhalten");
										Bukkit.getPlayer(args[1]).setScoreboard(Tablist.getScoreboard());
										Bukkit.getPlayer(args[1]).setDisplayName(Utils.getBetterString(Rank.getPrefix(Rank.SPIELER) + Bukkit.getPlayer(args[1]).getName(), 16));
										
										Tablist.getScoreboard().getTeam(Tablist.getTeamOfRank(Rank.SPIELER).getName()).addEntry(Bukkit.getPlayer(args[1]).getName());
									} else {
										sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
									}
								}
							}
								
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
								if(!Rank.isRankAble(Rank.getRankFromPlayer(Bukkit.getPlayer(args[1]).getName()), Rank.OWNER) && Rank.getRankFromPlayer(sender.getName()) != Rank.OWNER) {
									sender.sendMessage(Main.getPrefix() + "§cDu darfst diesem Spieler den Rang nicht entfernen!");
								} else {
									if(Rank.setRankToPlayer(uuid, Rank.SPIELER)) {
										sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(Rank.SPIELER) + Rank.SPIELER.name() + " §7erhalten");
									} else {
										sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
									}								
								}
							} else {
								sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");
							}
						}
					} else {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
					}
				} else {
					sendUsage(sender);
				}
			} else if(args.length == 3) {
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
							try {
								rank = Rank.valueOf(args[2].toUpperCase());
							} catch (Exception e2) {
								sender.sendMessage(Main.getPrefix() + "§cDieser Rang existiert nicht!");
								return true;
							}
						}
						if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
							if(Rank.setRankToPlayer(Bukkit.getPlayer(args[1]).getName(), rank)) {
								sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(rank) + rank.name() + " §7erhalten");
								Bukkit.getPlayer(args[1]).setScoreboard(Tablist.getScoreboard());
								Bukkit.getPlayer(args[1]).setDisplayName(Utils.getBetterString(Rank.getPrefix(rank) + Bukkit.getPlayer(args[1]).getName(), 16));
								
								Tablist.getScoreboard().getTeam(Tablist.getTeamOfRank(rank).getName()).addEntry(Bukkit.getPlayer(args[1]).getName());
							} else {
								sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
							}
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
								if(Rank.setRankToPlayer(uuid, rank)) {
									sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat nun den Rang " + Rank.getRankColor(rank) + rank.name() + " §7erhalten");
								} else {
									sender.sendMessage(Main.getPrefix() + "§cDer Rang konnte nicht geändert werden!");
								}
							} else {
								sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");
							}
						}
					} else {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
					}
				} else {
					sendUsage(sender);
				}
			}
			
		} else {
			sender.sendMessage(Main.getPrefix() + "§cDieser Befehl ist nur von Administratoren oder der Console ausführbar!");
		}
		
		return false;
	}
	
	private static void sendUsage(CommandSender sender) {
		
		sender.sendMessage(Main.getPrefix() + "§4Rank §8〡 §6Hilfestellung");
		sender.sendMessage(" ");
		sender.sendMessage(Main.getPrefix() + "§c/rank [help] §8» §eZeigt diese Hilfestellung an");
		sender.sendMessage(Main.getPrefix() + "§c/rank set <Player> <Rank> §8» §eSetzt dem Spieler den Rang");
		sender.sendMessage(Main.getPrefix() + "§c/rank remove <Player> §8» §eSetzt dem Spieler den Rang §7'§eSpieler§7'");
		sender.sendMessage(Main.getPrefix() + "§c/rank get <Player> §8» §eZeigt den Rang des Spielers an");
		sender.sendMessage(Main.getPrefix() + "§c/rank list §8» §eZeigt dir die möglichen Ränge an");
		sender.sendMessage(Main.getPrefix() + "§c/rank whiteList <Rank> §8» §eLässt nur noch den Rang (oder auch höhere) auf den Server");
		sender.sendMessage(Main.getPrefix() + "§c/rank whiteList <true / false> §8» §eOb nur der eine Rang drauf darf");
		
	}

}
