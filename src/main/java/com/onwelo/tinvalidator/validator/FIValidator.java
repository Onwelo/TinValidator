package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class FIValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{6}[+\\-A]\\d{3}[0-9ABCDEFHJKLMNPRSTUVWXY]");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        Character[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J', 'K', 'L',
                             'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                             'Y'};
        try {
            assert Integer.parseInt(tin.substring(0,2)) <= 31;
            assert Integer.parseInt(tin.substring(0,2)) > 0;
            assert Integer.parseInt(tin.substring(2,4)) <= 12;
            assert Integer.parseInt(tin.substring(2,4)) > 0;

            Integer remainder = Integer.parseInt(tin.substring(0, 10).replaceAll("[^\\d]", "")) % 31;
            if (remainder < 10){
                return remainder.toString().equals(tin.substring(10,11));
            }
            return chars[remainder-10].toString().equals(tin.substring(10,11));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }

    public String trim(String tin){
        return tin.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\-+]", "");
    }
}
