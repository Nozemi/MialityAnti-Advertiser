package com.miality.antiad;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.miality.antiad.listeners.ChatListener;

public class AntiAd extends JavaPlugin {
	public final static String warn = ChatColor.DARK_RED + "[" + ChatColor.YELLOW + "Warning" + ChatColor.DARK_RED + "]";
    public final static String info = ChatColor.WHITE + "[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "]";
    public final static String remind = ChatColor.WHITE + "[" + ChatColor.GREEN + "Reminder" + ChatColor.WHITE + "]";
    
    public static String pluginDir = null;
    
	public static String pluginName = "MialityWorld";
	public final Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		logger.info("["+pluginName+"]"+" has been enabled.");
		getDataFolder().mkdirs();
		pluginDir = getDataFolder().toString();
		logger.info(pluginDir);
		
		new ChatListener(this);
	}
	
	@Override
	public void onDisable() {
		logger.info("["+pluginName+"]"+" has been disabled.");
		
	}
}
