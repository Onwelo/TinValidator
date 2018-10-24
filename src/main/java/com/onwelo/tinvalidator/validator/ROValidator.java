package com.onwelo.tinvalidator.validator;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ROValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[1-9]\\d{1,9}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        int[] multipliers = new int[]{7, 5, 3, 2, 1, 7, 5, 3, 2};
        char[] tinArray = tin.toCharArray();
        int checkNumber = Character.getNumericValue(tinArray[tinArray.length - 1]);
        multipliers = Arrays.copyOfRange(multipliers, 10 - tinArray.length, multipliers.length);
        int sum = 0;

        for(int i = 0; i < tinArray.length - 1; i++) {
            sum += Character.getNumericValue(tinArray[i]) * multipliers[i];
        }

        int r = sum * 10 % 11;

        if(r == 10) {
            r = 0;
        }

        return checkNumber == r;
    }
}
