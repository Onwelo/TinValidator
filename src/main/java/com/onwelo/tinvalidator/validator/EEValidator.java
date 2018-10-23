package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class EEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[1-6]\\d{2}[01]\\d[0-3]\\d[0-7]\\d{3}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        try {
            assert Integer.parseInt(tin.substring(3,5)) <= 12;
            assert Integer.parseInt(tin.substring(3,5)) > 0;
            assert Integer.parseInt(tin.substring(5,7)) <= 31;
            assert Integer.parseInt(tin.substring(5,7)) > 0;
            assert Integer.parseInt(tin.substring(7,10)) <= 710;
            assert Integer.parseInt(tin.substring(7,10)) > 0;

            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            if (sum % 11 != 10) {
                return (sum % 11) == Integer.parseInt(tin.substring(10, 11));
            }

            int[] weights2 = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            sum = 0;
            for (int i = 0; i < weights2.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights2[i];
            }
            if (sum % 11 != 10) {
                return (sum % 11) == Integer.parseInt(tin.substring(10, 11));
            }
            return 0 == Integer.parseInt(tin.substring(10, 11));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
