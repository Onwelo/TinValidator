package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class HRValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{11}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            int rest = 10;
            for (int i = 0; i < 10; i++) {
                rest = (Integer.parseInt(tin.substring(i, i + 1)) + rest) % 10;
                if (rest == 0){
                    rest = 10;
                }
                rest = rest * 2 % 11;
            }
            rest = 11 - rest;
            return (rest == 10 ? 0 : rest) == Integer.parseInt(tin.substring(10, 11));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
