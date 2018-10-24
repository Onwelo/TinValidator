package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class ATValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("U[0-9]{8}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            char[] tinArray = tin.toCharArray();
            for (int i = 0; i < tinArray.length; i++){
                tinArray[i] -= 48;
            }

            int R = (tinArray[2]/5 + tinArray[2]*2) % 10 +(tinArray[4]/5 + tinArray[4]*2) % 10 +(tinArray[6]/5 + tinArray[6]*2) % 10;

            return (10 - (R + tinArray[1] + tinArray[3] + tinArray[5] + tinArray[7] +4 ) % 10) % 10 == tinArray[8];
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
