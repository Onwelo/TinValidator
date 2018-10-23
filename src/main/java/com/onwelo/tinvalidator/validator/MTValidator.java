package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class MTValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{8}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            assert Long.parseLong(tin.substring(0, 6)) > 100000;

            int a1 = 0;
            int checkNumber = Integer.parseInt(tin.substring(6));
            int[] multipliers = new int[]{3, 4, 6, 7, 8, 9};
            char[] tinArray = tin.substring(0, 6).toCharArray();

            for (int i = 0; i < tinArray.length; i++) {
                a1 += multipliers[i] * tinArray[i];
            }

            int r = 37 - a1 % 37;
            return checkNumber == r;
        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }
}
