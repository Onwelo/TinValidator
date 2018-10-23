package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class EEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{9}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {3, 7, 1, 3, 7, 1, 3, 7};
        try {
            assert Integer.parseInt(tin.substring(0, 2)) == 10;

            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            return (10 - (sum % 10)) % 10 == Integer.parseInt(tin.substring(8, 9));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
