package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class NLValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}B[0-9]{2}");

    public Pattern getPattern(){
            return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            return (sum % 11) == Integer.parseInt(tin.substring(8, 9));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
