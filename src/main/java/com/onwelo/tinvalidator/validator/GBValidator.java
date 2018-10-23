package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class GBValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("GD[0-4]\\d{2}|HA[5-9]\\d{2}|\\d{9}|\\d{12}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        if (tin.length() == 5){
            return true;
        }

        int[] weights = {8, 7, 6, 5, 4, 3, 2};
        try {
            int first7 = Integer.parseInt(tin.substring(0,7));
            assert Integer.parseInt(tin) != 0;
            assert tin.length() != 12 || Integer.parseInt(tin.substring(9, 12)) != 0;

            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            sum += Integer.parseInt(tin.substring(7,9));
            if (sum % 97 == 0){
                assert first7 < 100000 || first7 > 999999;
                assert first7 < 9490001 || first7 > 9700000;
                assert first7 < 9990001;
                return true;
            }
            if ((sum + 55) % 97 == 0){
                assert first7 > 1000000;
                return true;
            }
            return false;
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }
}
