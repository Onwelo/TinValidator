package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class CYValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[013459][0-9]{7}[A-Z]");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {1, 0, 5, 7, 9, 13, 15, 17, 19, 21};
        try {
            assert Integer.parseInt(tin.substring(0,2)) != 12;
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                int d = Integer.parseInt(tin.substring(i, i + 1));
                sum += ((i+1)%2==0 ? d : weights[d]);
            }
            return ((sum % 26) + 65) == tin.charAt(8);
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
