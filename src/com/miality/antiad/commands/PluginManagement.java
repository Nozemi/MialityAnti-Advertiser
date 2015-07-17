package com.miality.antiad.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.miality.antiad.AntiAd;
import com.miality.antiad.util.PluginHelp;

public class PluginManagement implements CommandExecutor {
	private AntiAd plugin;
	
	public PluginManagement(AntiAd passedPlugin) {
		this.plugin = passedPlugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		plugin.getName();
		if(cmd.getName().equalsIgnoreCase("adtoggle")) {
			if(args.length < 1) {
				new PluginHelp(sender, cmd.getName());
			}
			return true;
		}
		return false;
	}
}
