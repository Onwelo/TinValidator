package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class DKValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-3]\\d[01]\\d{7}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {4, 3, 2, 7, 6, 5, 4, 3, 2};
        try {
            assert Integer.parseInt(tin.substring(0,2)) <= 31;
            assert Integer.parseInt(tin.substring(0,2)) > 0;
            assert Integer.parseInt(tin.substring(2,4)) <= 12;
            assert Integer.parseInt(tin.substring(2,4)) > 0;

            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }

            return (sum % 11 == 0 ? 0 : 11-(sum % 11)) == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
