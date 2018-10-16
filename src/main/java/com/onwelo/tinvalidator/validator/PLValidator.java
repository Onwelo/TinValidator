package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class PLValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{10}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            return (sum % 11) == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
