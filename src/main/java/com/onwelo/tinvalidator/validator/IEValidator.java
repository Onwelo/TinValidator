package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class IEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{7}[A-W][A-IW]?");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {8, 7, 6, 5, 4, 3, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            if (tin.length() == 9 && !"W".equals(tin.substring(8, 9))){
                sum += (tin.charAt(8) - 64)*9;
            }
            char remainder = (char)(sum % 23);
            return (remainder == 0 ? 'W' : remainder + 64) == tin.charAt(7);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
