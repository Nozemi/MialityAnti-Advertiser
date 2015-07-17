package com.miality.antiad.util;

import org.bukkit.command.CommandSender;

import com.miality.antiad.AntiAd;

public class PluginHelp {
	public PluginHelp(CommandSender sender, String command) {
		if(command.equalsIgnoreCase("adplayer")) {
			sender.sendMessage(AntiAd.info+" This is a unfinished help message for /adplayer.");
		} else if(command.equalsIgnoreCase("adtoggle")) {
			sender.sendMessage(AntiAd.info+" This is a unfinished help message for /adtoggle.");			
		} else if(command.equalsIgnoreCase("adblacklist")) {
			sender.sendMessage(AntiAd.info+" This is a unfinished help message for /adtoggle.");					
		}
	}
}
