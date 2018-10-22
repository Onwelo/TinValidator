package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class PTValidator implements Validator {

    private static final Pattern PATTERN =  Pattern.compile("\\d{9}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            assert Integer.parseInt(tin.substring(0, 1)) > 0;

            int a1 = 0;
            int checkNumber = Integer.parseInt(tin.substring(8));
            int[] multipliers = new int[] {9, 8, 7, 6, 5, 4, 3, 2};
            char[] tinArray = tin.substring(0, 8).toCharArray();

            for(int i = 0; i < tinArray.length; i++) {
                a1 += tinArray[i] * multipliers[i];
            }

            int r = 11 - a1 % 11;

            if(r == 10 || r == 11) {
                r = 0;
            }

            return checkNumber == r;

        } catch (NumberFormatException | AssertionError e) {
            return false;
        }

    }
}
