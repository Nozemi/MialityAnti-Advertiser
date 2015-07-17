package com.miality.antiad.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.miality.antiad.AntiAd;
import com.miality.antiad.detectors.CustomDetector;
import com.miality.antiad.detectors.DomainDetector;
import com.miality.antiad.detectors.IPv4Detector;
import com.miality.antiad.util.ThreePlusCharacters;

public class ChatListener implements Listener {

	public static int SuspectLevel = 0;
	
	AntiAd MyPlugin;
	
	public ChatListener(AntiAd plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		MyPlugin = plugin;
	}
	
	@EventHandler
	public void onAsycPlayerChatEvent(AsyncPlayerChatEvent event) {
		String message = event.getMessage(); // Gets the message that is sent from the player.
		message = ThreePlusCharacters.removeTripplesPlus(message, true); // Removes three or more characters in a row. For instance: heeeello! is heello!
		
		Player player = (Player) event.getPlayer();
		
		if(MyPlugin.getConfig().getString("UseMySQL") == "true") {	
		} else {
			if(MyPlugin.whitelistedPlayers.isWhitelisted(player.getUniqueId().toString())) {
				message = CustomDetector.filterMessage(IPv4Detector.filterIP(DomainDetector.filterDomain(message))); // Takes out any domain or IPs, or tries to.
			}
		}
		event.setMessage(message); // Sends the new message and filtered message.
	}
}
