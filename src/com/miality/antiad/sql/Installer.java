package com.miality.antiad.sql;

import java.sql.SQLException;

import com.miality.antiad.AntiAd;
import com.mysql.jdbc.PreparedStatement;

public class Installer extends Connector {
	public Installer(AntiAd plugin) throws SQLException {
		String prefix = plugin.getConfig().getString("prefix");
		String statement = "CREATE TABLE IF NOT EXISTS %prefix%";
		statement = statement.replaceAll("%prefix%", prefix);
		Connector.open();
		PreparedStatement installer = (PreparedStatement) connection.
				prepareStatement(statement);
		installer.execute();
		installer.close();
		Connector.close();
	}
}
