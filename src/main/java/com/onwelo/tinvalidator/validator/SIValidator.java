package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class SIValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[1-9]\\d{7}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            int multiplier = 8;
            int sum = 0;
            char[] tinArray = tin.toCharArray();
            int checkNumber = Character.getNumericValue(tinArray[tinArray.length - 1]);

            for (int i = 0; i < tinArray.length - 1; i++) {
                sum += Character.getNumericValue(tinArray[i]) * multiplier;
                multiplier--;
            }

            int r = 11 - sum % 11;

            if (r == 10) {
                r = 0;
            } else if (r == 11) {
                return false;
            }

            return checkNumber == r;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
