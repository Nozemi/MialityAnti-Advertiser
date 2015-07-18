package com.miality.antiad.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.miality.antiad.AntiAd;
import com.mysql.jdbc.Connection;

public class Connector extends JavaPlugin {
	public static Connection connection;
	
	public static AntiAd plugin = new AntiAd();
	public static FileConfiguration config = plugin.getConfig();

	public static String host = config.getString("host"); // Database host (for instance: localhost)
	public static String name = config.getString("name"); // Database name
	public static String user = config.getString("user"); // Database username
	public static String pass = config.getString("pass"); // Database password
	public static String prefix = config.getString("prefix"); // Database table prefix
	
	public synchronized static void open() {
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+name, user, pass);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
