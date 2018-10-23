package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class BEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("0?[1-9][0-9]{8}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        if (tin.length() == 9){
            tin = "0" + tin;
        }
        try {
            int ctrlDigit = 97 - (Integer.parseInt(tin.substring(0,8)) % 97);
            return ctrlDigit == Integer.parseInt(tin.substring(8, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
