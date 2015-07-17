package com.miality.antiad;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.miality.antiad.commands.BlacklistManagement;
import com.miality.antiad.commands.PlayerManagement;
import com.miality.antiad.commands.PluginManagement;
import com.miality.antiad.listeners.ChatListener;
import com.miality.antiad.sql.Installer;
import com.miality.antiad.util.FlatFileSaving;

public class AntiAd extends JavaPlugin {
	
	// Some chat prefixes I include in most of my projects.
	public final static String warn = ChatColor.DARK_RED + "[" + ChatColor.YELLOW + "Warning" + ChatColor.DARK_RED + "]";
    public final static String info = ChatColor.WHITE + "[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "]";
    public final static String remind = ChatColor.WHITE + "[" + ChatColor.GREEN + "Reminder" + ChatColor.WHITE + "]";
    
    // makes the variable pluginDir available for every other class in the project.
    public static String pluginDir = null;
    
    // The logger than logs to the console window.
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public FlatFileSaving whitelistedPlayers;
	
	@Override
	public void onEnable() {
		logger.info("["+getName().toString()+"] has been enabled.");
				
		// Creates the plugin folder in the plugins directory.
		getDataFolder().mkdirs();
		pluginDir = getDataFolder().getAbsolutePath(); // Gets this plugins directory.

		this.getConfig().options().copyDefaults(); // Gets the default config file.
		this.saveConfig(); // Saves the config file to the plugin directory.

		this.saveResource("Blacklists"+File.separator+"blacklist.txt", true);
		this.saveResource("Blacklists"+File.separator+"splitwords.txt", true);
		
		if(this.getConfig().getString("UseMySQL") == "true") {
			// Use database saving.
			try {
				new Installer(this);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Use flat file saving.
			try {
				whitelistedPlayers = new FlatFileSaving(new File(pluginDir + File.separator + "Whitelist.txt"));
				this.whitelistedPlayers.loadWhitelist();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		new ChatListener(this); // Starts the chat listener.
		
		/* Get command classes */
		this.getCommand("adplayer").setExecutor(new PlayerManagement(this));
		this.getCommand("adtoggle").setExecutor(new PluginManagement(this));
		this.getCommand("adblacklist").setExecutor(new BlacklistManagement(this));
	}
	
	@Override
	public void onDisable() {
		logger.info("["+getName().toString()+"] has been disabled.");
		this.saveConfig();
		try {
			this.whitelistedPlayers.saveWhitelist();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
