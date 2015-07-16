package com.miality.antiad.util;

public class ThreePlusCharacters {
    public static String removeTripplesPlus(String input, boolean doubleLetter) {
        String pattern = null;
        if(doubleLetter) pattern = "(.)(?=\\1{2})";
        else pattern = "(.)(?=\\1)";
        return input.replaceAll(pattern, "");
	}
}
