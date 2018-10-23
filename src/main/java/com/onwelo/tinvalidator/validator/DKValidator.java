package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class DKValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{8}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {2, 7, 6, 5, 4, 3, 2, 1};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            return sum % 11 == 0 ;
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
