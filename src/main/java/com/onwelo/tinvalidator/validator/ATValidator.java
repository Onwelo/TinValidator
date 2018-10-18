package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class ATValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        int[] weights = {1, 2, 1, 2, 1, 2, 1, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                int n = Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
                if (n > 9){
                    n = n%10 + n/10;
                }
                sum += n;
            }
            return ((100 - sum) % 10) == Integer.parseInt(tin.substring(8, 9));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
