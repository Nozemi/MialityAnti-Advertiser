package com.miality.antiad.detectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.miality.antiad.AntiAd;
import com.miality.antiad.listeners.ChatListener;

public class CustomDetector {
	public static String filterMessage(String message) {

		ArrayList<String> blackList = new ArrayList<String>();
		ArrayList<String> splitList = new ArrayList<String>();
		
		Scanner a;
		Scanner b;
		try {
			a = new Scanner(new File(AntiAd.pluginDir+File.separator+"Blacklists"+File.separator+"blacklist.txt"));
			b = new Scanner(new File(AntiAd.pluginDir+File.separator+"Blacklists"+File.separator+"splitwords.txt"));
			while(a.hasNext()) {
				blackList.add(a.next());
			}
			while(b.hasNext()) {
				splitList.add(b.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> cWords = new ArrayList<String>();
		
		for(int i = 0; i < splitList.size(); i++) {
			message = message.replace(splitList.get(i), " ");
		}
		
		String[] words = message.split(" ");
		
		for(int i = 0; i < words.length; i++) {
			cWords.add(words[i]);
		}
		
		if(cWords.size() > 1) {
			for(int i = 0; i < cWords.size(); i++) {
				int prev1 = i-1;
				int prev2 = prev1-1;
				if(prev2 < 0) {
					prev2 = 0;
				}
				if(prev1 < 0) {
					prev1 = 0;
				}
				
				if(blackList.contains(cWords.get(i).toUpperCase()))
				{
					if(blackList.contains(cWords.get(prev1).toUpperCase()) ||
					blackList.contains(cWords.get(prev2).toUpperCase()) &&
					prev1 != 0 || prev2 != 0)
					{
						message = message.replaceAll(cWords.get(i), "*").replaceAll("\\(dot\\)", " ");
						ChatListener.SuspectLevel = 1;
					}
				}
			}
		}
		return message;
	}
}
