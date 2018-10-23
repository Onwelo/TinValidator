package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class ELValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {256, 128, 64, 32, 16, 8, 4, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            return (sum % 11) % 10 == Integer.parseInt(tin.substring(8, 9));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
