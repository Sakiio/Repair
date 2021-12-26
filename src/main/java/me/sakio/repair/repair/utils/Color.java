package me.sakio.repair.repair.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Color {
	
	public static String translate(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
