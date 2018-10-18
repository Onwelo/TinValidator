package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class CZValidator implements Validator{

    private static final Pattern PATTERN = Pattern.compile("(\\d{2}[0156]\\d[0-3]\\d{4})|(\\d{2}[0-35-8]\\d[0-3]\\d{5})");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            int month = Integer.parseInt(tin.substring(2,4));
            int day = Integer.parseInt(tin.substring(4,6));
            assert (month >= 1 && month <= 12) || (month >= 21 && month <= 32)
                    || (month >= 51 && month <= 62) || (month >= 71 && month <= 82);
            assert day >= 1 && day <= 31;
            return true;
        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }
}
