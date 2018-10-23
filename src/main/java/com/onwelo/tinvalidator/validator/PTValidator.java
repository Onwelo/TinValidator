package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class PTValidator implements Validator {

    private static final Pattern PATTERN =  Pattern.compile("[1-9]\\d{8}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            int sum = 0;
            int checkNumber = Integer.parseInt(tin.substring(8));
            int[] multipliers = new int[] {9, 8, 7, 6, 5, 4, 3, 2};
            char[] tinArray = tin.substring(0, 8).toCharArray();

            for(int i = 0; i < tinArray.length; i++) {
                sum += tinArray[i] * multipliers[i];
            }

            int r = 11 - sum % 11;

            if(r > 9) {
                r = 0;
            }

            return checkNumber == r;

        } catch (NumberFormatException | AssertionError e) {
            return false;
        }

    }
}
