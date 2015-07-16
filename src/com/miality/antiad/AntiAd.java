package com.miality.antiad;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.miality.antiad.listeners.ChatListener;
import com.miality.antiad.sql.Installer;

public class AntiAd extends JavaPlugin {
	
	// Some chat prefixes I include in most of my projects.
	public final static String warn = ChatColor.DARK_RED + "[" + ChatColor.YELLOW + "Warning" + ChatColor.DARK_RED + "]";
    public final static String info = ChatColor.WHITE + "[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "]";
    public final static String remind = ChatColor.WHITE + "[" + ChatColor.GREEN + "Reminder" + ChatColor.WHITE + "]";
    
    // makes the variable pluginDir available for every other class in the project.
    public static String pluginDir = null;
    
    // The logger than logs to the console window.
	public final Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		logger.info("["+getName().toString()+"] has been enabled.");
				
		// Creates the plugin folder in the plugins directory.
		getDataFolder().mkdirs();
		pluginDir = getDataFolder().toString(); // Gets this plugins directory.

		this.getConfig().options().copyDefaults(); // Gets the default config file.
		this.saveConfig(); // Saves the config file to the plugin directory.
		
		if(this.getConfig().getString("UseMySQL") == "true") {
			// Use database saving.
			try {
				new Installer(this);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Use flat file saving.
		}
		
		new ChatListener(this); // Starts the chat listener.
	}
	
	@Override
	public void onDisable() {
		logger.info("["+getName().toString()+"] has been disabled.");
		this.saveConfig();
	}
}
