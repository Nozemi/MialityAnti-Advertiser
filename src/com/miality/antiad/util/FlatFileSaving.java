package com.miality.antiad.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FlatFileSaving {
	
	private File saveFile;
	private ArrayList<String> players;
	
	public FlatFileSaving(File file) throws IOException {
		players = new ArrayList<String>();
		saveFile = file;
		
		if(this.saveFile.exists() == false) {
			this.saveFile.createNewFile();
		}
	}
	
	public void loadWhitelist() throws Exception {
		DataInputStream stream = new DataInputStream(new FileInputStream(this.saveFile));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		String line;
		while((line = reader.readLine()) != null) {
			if(players.contains(line) == false) {
				players.add(line);
			}
		}
		
		reader.close();
		stream.close();
	}
	
	public void saveWhitelist() throws IOException {
		FileWriter stream = new FileWriter(this.saveFile);
		BufferedWriter writer = new BufferedWriter(stream);
		
		for(String player : this.players) {
			writer.write(player);
			writer.newLine();
		}
		
		writer.close();
		stream.close();
	}
	
	public boolean isWhitelisted(String uuid) {
		if(players.contains(uuid)) {
			return true;
		}
		return false;
	}
	
	public void addToWhitelist(String uuid) {
		if(isWhitelisted(uuid) == false) {
			players.add(uuid);
		}
	}
	
	public void removeFromWhitelist(String uuid) {
		if(isWhitelisted(uuid)) {
			players.remove(uuid);
		}
	}
	
	public ArrayList<String> getWhitelist() {
		return players;
	}
}
