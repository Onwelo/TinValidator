package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class DEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]\\d{9}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        //TODO zaimplementować nowy algorytm do wyliczania sumy kontrolnej gdy będzie on znany.
        return true;
    }
}
