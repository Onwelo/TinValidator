package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class LVValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-59]\\d[01]\\d{3}[0-2]\\d{4}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            if(32 <= Integer.parseInt(tin.substring(0,2))){
                return true;
            }
            assert Integer.parseInt(tin.substring(0,2)) <= 31;
            assert Integer.parseInt(tin.substring(0,2)) > 0;
            assert Integer.parseInt(tin.substring(2,4)) <= 12;
            return true;
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
