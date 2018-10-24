package com.onwelo.tinvalidator.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class LVValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("\\d{11}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin){
        try {
            if (Integer.parseInt(tin.substring(0, 1)) < 4) {
                return assertNaturalPerson(tin);
            }

            int[] weights = {9, 1, 4, 8, 3, 10, 2, 5, 7, 6};
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int remainder = 3 - (sum % 11);
            if (remainder > -1) return remainder == Integer.parseInt(tin.substring(10, 11));
            if (remainder < -1) return remainder + 11 == Integer.parseInt(tin.substring(10, 11));

            return false;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean assertNaturalPerson(String tin) {
        try {
            int century;
            switch (tin.charAt(6)){
                case '0': century = 1800;break;
                case '1': century = 1900;break;
                case '2': century = 2000;break;
                default: throw new AssertionError();
            }
            LocalDate.of(century + Integer.parseInt(tin.substring(4,6)), Integer.parseInt(tin.substring(2,4)), Integer.parseInt(tin.substring(0,2)));
            return true;
        } catch (NumberFormatException|AssertionError|DateTimeException e) {
            return false;
        }
    }
}
