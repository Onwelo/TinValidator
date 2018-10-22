package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class GRValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        return true;
    }

}
