package com.miality.antiad.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.miality.antiad.AntiAd;
import com.miality.antiad.detectors.CustomDetector;
import com.miality.antiad.detectors.DomainDetector;
import com.miality.antiad.detectors.IPv4Detector;

public class ChatListener implements Listener {

	public static int SuspectLevel = 0;
	
	public ChatListener(AntiAd plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);		
	}
	
	@EventHandler
	public void onAsycPlayerChatEvent(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		message = CustomDetector.filterMessage(IPv4Detector.filterIP(DomainDetector.filterDomain(message)));
		event.setMessage(message);
	}
}
