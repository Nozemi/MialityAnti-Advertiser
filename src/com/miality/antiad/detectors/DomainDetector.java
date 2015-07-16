package com.miality.antiad.detectors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.miality.antiad.listeners.ChatListener;

public class DomainDetector {
	public static String filterDomain(String message) {
		Pattern domainPattern = Pattern.compile("([-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+~#?&//=]*)?)|([-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\_[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+~#?&//=]*)?)|([-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\,[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+~#?&//=]*)?)|([-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\-[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+~#?&//=]*)?)");
		Matcher domainMatcher = domainPattern.matcher(message);

        while (domainMatcher.find()) {
            if (domainMatcher.group().length() != 0) {
                if (domainPattern.matcher(message).find()) {
                	String match = domainMatcher.group(0);
                	String replacement = "*";
                	message = message.replaceAll(match, replacement);
                	ChatListener.SuspectLevel = 3;
                }
            }
        }
        
        return message;
	}
}
