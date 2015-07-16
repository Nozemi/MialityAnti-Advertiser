package com.miality.antiad.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.miality.antiad.AntiAd;
import com.wolvencraft.yasp.Statistics;
import com.wolvencraft.yasp.StatisticsAPI;
import com.wolvencraft.yasp.session.OnlineSession;

public class StatisticsIntegration extends Statistics {
	/* Usage of the Statistics plugin API */
	public StatisticsIntegration(AntiAd plugin) {
		FileConfiguration config = plugin.getConfig(); // Getting configuration file for this plugin.
		Player player = null; // TODO: Add the usage of a player.
		OnlineSession StatPlayer = StatisticsAPI.getSession(player); // Gets the player data from Player player (which is currently NULL)
		int TotalPlaytime = StatPlayer.getPlayerTotals().getTotalPlaytime().getValue();
		
		if(config.getString("AutoWhitelist").toLowerCase() == "true") {
			if(TotalPlaytime >= Integer.parseInt(config.getString("WhitelistAfter"))) {
				
			}
		}
	}
}
