package com.miality.antiad.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.miality.antiad.AntiAd;
import com.miality.antiad.util.PluginHelp;

public class PlayerManagement implements CommandExecutor {
	
	private AntiAd plugin;
	
	public PlayerManagement(AntiAd passedPlugin) {
		this.plugin = passedPlugin;
	}
	
	@SuppressWarnings("deprecation")
	public void addToWhitelist(String playerName) throws IOException {
		Player player = (Player) Bukkit.getPlayer(playerName);
		String uuid;
		if(player == null) {
			uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();
		} else {
			uuid = player.getUniqueId().toString();
		}
		
		if(plugin.getConfig().getString("UseMySQL") == "true") {
			
		} else {
			plugin.whitelistedPlayers.addToWhitelist(uuid);
			plugin.whitelistedPlayers.saveWhitelist();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		plugin.getName();
		if(cmd.getName().equalsIgnoreCase("adplayer")) {
			if(args.length < 1) {
				new PluginHelp(sender, cmd.getName());
			} else {
				switch(args[0]) {
					case "whitelist":
						try {
							this.addToWhitelist(args[1]);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
				}
			}
			return true;
		}
		return false;
	}
}
