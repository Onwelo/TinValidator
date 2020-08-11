package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class NLValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}B[0-9]{2}");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        //TODO implement new algorithm to calculate checksum
        return true;
    }
}
