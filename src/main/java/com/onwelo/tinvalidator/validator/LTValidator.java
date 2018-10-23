package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class LTValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{9}|\\d{10}1\\d");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2};
        int tinLength = tin.length();
        try {
            int sum = 0;
            for (int i = 0; i < tinLength - 1; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            if (sum % 11 != 10) {
                return (sum % 11) == Integer.parseInt(tin.substring(tinLength - 1, tinLength));
            }

            int[] weights2 = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4};
            sum = 0;
            for (int i = 0; i < tinLength - 1; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights2[i];
            }
            if (sum % 11 != 10) {
                return (sum % 11) == Integer.parseInt(tin.substring(tinLength - 1, tinLength));
            }
            return 0 == Integer.parseInt(tin.substring(tinLength - 1, tinLength));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
