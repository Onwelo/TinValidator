package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class BEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{11}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            assert Integer.parseInt(tin.substring(2,4)) <= 12;
            assert Integer.parseInt(tin.substring(4,6)) <= 31;
            int sum = Integer.parseInt(tin.substring(0,9));
            return 97 - (sum % 97) == Integer.parseInt(tin.substring(9, 11));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
