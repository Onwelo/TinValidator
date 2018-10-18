package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class BGValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{10}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {2, 4, 8, 5, 10, 9, 7, 3, 6};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int reminder = sum % 11;
            return (reminder == 10 ? 0 : reminder) == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
