package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class SEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{12}");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int r1 = Integer.parseInt(tin.substring(11));
        if (r1 == 0 || r1 > 94) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int elemtent = Integer.parseInt(tin.substring(i, i + 1));
            if (i % 2 == 1) {
                sum += elemtent;
            } else {
                sum += elemtent / 5 + ((elemtent * 2) % 10);
            }
        }

        return (10 - (sum % 10)) % 10 == Integer.parseInt(tin.substring(10, 11));
    }
}
