package de.Banko.RankAPI.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.Banko.RankAPI.Main;
import de.Banko.RankAPI.Coins.Coins;
import de.Banko.RankAPI.Groups.Rank;

public class Command_Coins implements CommandExecutor {

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
							int coins = Coins.getCoinsFromPlayer(Bukkit.getPlayer(args[1]).getName());
							sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat §e" + coins + " Coins");
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								int coins = Coins.getCoinsFromPlayer(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]));
								sender.sendMessage(Main.getPrefix() + "§7Der Spieler §e" + args[1] + " §7hat §e" + coins + " Coins");
							} else {								
								sender.sendMessage(Main.getPrefix() + "§cDer Spieler konnte nicht in der Datenbank gefunden werden!");								
							}
							
						}
					} else {
						sender.sendMessage(Main.getPrefix() + "§cDas Argument 2 muss ein Spielername sein!");
					}
				} else if(args[0].equalsIgnoreCase("reset")) {
					if(args[1] != null) {
						if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {						
							if(Coins.setCoinsToPlayer(Bukkit.getPlayer(args[1]).getName(), 0)) {
								sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden zurückgesetzt");
							} else {
								sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
							}											
								
						} else {
							
							if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
								UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
								if(Coins.setCoinsToPlayer(uuid, 0)) {
									sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden zurückgesetzt");
								} else {
									sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
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
						try {
							if(Integer.valueOf(args[2]) >= 0) {
								int coins = Integer.valueOf(args[2]);
								if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
									if(Coins.setCoinsToPlayer(Bukkit.getPlayer(args[1]).getName(), coins)) {
										sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
									} else {
										sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
									}
								} else {
									
									if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
										UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
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
				} else if(args[0].equalsIgnoreCase("add")) {
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
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(args[1] != null) {
						try {
							if(Integer.valueOf(args[2]) >= 0) {
								int coins = Integer.valueOf(args[2]);
								if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
									coins = Coins.getCoinsFromPlayer(Bukkit.getPlayer(args[1]).getName()) - coins;
									if(coins >= 0) {
										if(Coins.setCoinsToPlayer(Bukkit.getPlayer(args[1]).getName(), coins)) {
											sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
										} else {
											sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
										}
									} else {
										coins = 0;
										if(Coins.setCoinsToPlayer(Bukkit.getPlayer(args[1]).getName(), coins)) {
											sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
										} else {
											sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
										}
									}
								} else {
									
									if(de.Banko.RankAPI.Utils.UUID.getUUID(args[1]) != null) {
										UUID uuid = de.Banko.RankAPI.Utils.UUID.getUUID(args[1]);
										coins = Coins.getCoinsFromPlayer(uuid) - coins;
										if(coins >= 0) {
											if(Coins.setCoinsToPlayer(uuid, coins)) {
												sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
											} else {
												sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
											}
										} else {
											coins = 0;
											if(Coins.setCoinsToPlayer(uuid, coins)) {
												sender.sendMessage(Main.getPrefix() + "§7Die Coins des Spielers §e" + args[1] + " §7wurden auf §e" + coins + " Coins §7gesetzt!");
											} else {
												sender.sendMessage(Main.getPrefix() + "§cDie Coins konnten nicht geändert werden!");
											}
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
				} else {
					sendUsage(sender);
				}
			}
			
		} else {
			sender.sendMessage(Main.getPrefix() + "§7Deine Coins betragen §e" + Coins.getCoinsFromPlayer(sender.getName()) + " Coins");
		}
		
		return false;
	}
	
	private static void sendUsage(CommandSender sender) {
		
		sender.sendMessage(Main.getPrefix() + "§4Coins §8〡 §6Hilfestellung");
		sender.sendMessage(" ");
		sender.sendMessage(Main.getPrefix() + "§c/coins [help] §8» §eZeigt diese Hilfestellung an");
		sender.sendMessage(Main.getPrefix() + "§c/coins get <Player> §8» §eZeigt dem Spieler die Coins zurück");
		sender.sendMessage(Main.getPrefix() + "§c/coins add <Player> <Anzahl> §8» §eFügt dem Spieler die Coins");
		sender.sendMessage(Main.getPrefix() + "§c/coins set <Player> <Anzahl> §8» §eSetzt dem Spieler die Coins");
		sender.sendMessage(Main.getPrefix() + "§c/coins remove <Player> <Anzahl> §8» §eEntfernt dem Spieler die Coins");
		sender.sendMessage(Main.getPrefix() + "§c/coins reset <Player> §8» §eSetzt dem Spieler die Coins zurück");
	}
	
}
