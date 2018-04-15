package de.Banko.RankAPI.Utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

	public static String getBetterString(String string, int length) {
		if(string.length() > length) {
			string = string.substring(0, length);
		} 
		
		return string;
	}
	
	public static void fillInventory(Inventory inv, ItemStack filling) {
		for (int i = 0; i < inv.getSize(); i++)
			inv.setItem(i, filling);
	}
}
