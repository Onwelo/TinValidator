package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class FIValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{8}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {7, 9, 10, 5, 8, 4, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int remainder = 11 - (sum % 11);
            return (remainder == 11 ? 0 : remainder) == Integer.parseInt(tin.substring(7, 8));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
