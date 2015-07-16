package com.miality.antiad.detectors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miality.antiad.listeners.ChatListener;

public class IPv4Detector {
	public static String filterIP(String message) {
		Pattern ipPattern = Pattern.compile("((?:\\d{1,3}[.,-_:;\\/()=?}+ ]{1,4}){3}\\d{1,3})");
		Matcher ipMatcher = ipPattern.matcher(message);

        while (ipMatcher.find()) {
            if (ipMatcher.group().length() != 0) {
                if (ipPattern.matcher(message).find()) {
                	String match = ipMatcher.group(ipMatcher.groupCount());
                	String replacement = "*";
                	message = message.replaceAll(match, replacement);
                	ChatListener.SuspectLevel = 3;
                }
            }
        }
        return message;
	}
}
