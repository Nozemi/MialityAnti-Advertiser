package com.miality.antiad.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.CommandSender;

import com.miality.antiad.AntiAd;

public class Whitelister extends Connector {
	public Whitelister(String uuid, String action, CommandSender sender) {
		if(action.equalsIgnoreCase("remove")) {
			try {
				this.removeFromWhitelist(uuid, sender);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(action.equalsIgnoreCase("add")) {
			
		}
	}
	
	public void removeFromWhitelist(String uuid, CommandSender sender) throws SQLException {
		if(isWhitelisted(uuid) == true) {
			Connector.open();
			String statement = "DELETE FROM `%prefix%antiad_whitelist` WHERE `uuid` = ?".replace("%prefix%", Connector.prefix);
			PreparedStatement removePlayer = (PreparedStatement) connection.
					prepareStatement(statement);
			removePlayer.setString(1, uuid);
			removePlayer.execute();
			removePlayer.close();
			Connector.close();
		} else {
			sender.sendMessage(AntiAd.warn+" That user is not whitelisted.");
		}
	}
	
	public void addToWhitelist(String uuid, CommandSender sender) {
		
	}
	
	private boolean isWhitelisted(String uuid) throws SQLException {
		Connector.open();
		String statement = "SELECT `username` FROM `%prefix%antiad_whitelist` WHERE `uuid` = ?";
		PreparedStatement checkWhitelist = (PreparedStatement) connection.
				prepareStatement(statement);
		checkWhitelist.setString(1, uuid);
		ResultSet result = checkWhitelist.executeQuery();
		if(result.next() == true) {
			result.close();
			checkWhitelist.close();
			Connector.close();
			return true;
		}
		checkWhitelist.close();
		result.close();
		Connector.close();
		return false;
	}
}
