package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class HUValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("(8[0-9]{9})|([0-9]{8})");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            if (tin.length() == 10) {
                //individual ten digit tin
                int sum = 0;
                for (int i = 0; i < 9; i++) {
                    int d = Integer.parseInt(tin.substring(i, i + 1));
                    sum += d * (i + 1);
                }
                return sum % 11 == Integer.parseInt(tin.substring(9,10));
            } else if (tin.length() == 8) {
                //EU VAT ID
                int[] weights = {9, 7, 3, 1, 9, 7, 3};
                int sum = 0;
                for (int i = 0; i < 7; i++) {
                    int d = Integer.parseInt(tin.substring(i, i + 1));
                    sum += d * weights[i];
                }

                sum = 10 - sum % 10;
                // module one more time, cause in case of 10 we want to compare with 0
                return sum % 10 == Integer.parseInt(tin.substring(7, 8));
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
