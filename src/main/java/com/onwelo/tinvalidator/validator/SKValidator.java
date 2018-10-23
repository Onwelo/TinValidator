package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class SKValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[1-9]\\d[2-47-9]\\d{7}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            assert Long.parseLong(tin) % 11 == 0;
            return true;
        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }
}
