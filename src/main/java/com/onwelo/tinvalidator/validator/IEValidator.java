package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class IEValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d[0-9A-Z+*]\\d{5}[A-W]");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        if(!tin.substring(1, 2).matches("\\d")){
            return computePreviousControlSum(tin);
        }
        int[] weights = {8, 7, 6, 5, 4, 3, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            char remainder = (char)(sum % 23);
            return (remainder == 0 ? 'W' : remainder + 64) == tin.charAt(7);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean computePreviousControlSum(String tin){
        int[] weights = {2, 0, 7, 6, 5, 4, 3};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                if (i == 1) continue;
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            char remainder = (char)(sum % 23);
            return (remainder == 0 ? 'W' : remainder + 64) == tin.charAt(7);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
