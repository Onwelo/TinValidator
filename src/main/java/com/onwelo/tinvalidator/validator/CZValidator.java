package com.onwelo.tinvalidator.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CZValidator implements Validator {

    private static final Pattern PATTERN =
            Pattern.compile("([0-8]\\d{7})|^6\\d{8}|(\\d{2}[0156]\\d[0-3]\\d{4})|(\\d{2}[0-35-8]\\d[0-3]\\d{5})");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            if(tin.length() == 8) {
                return assertEightDigitsTin(tin);
            } else if(tin.length() == 9) {
                return checkNineDigitsTin(tin);
            } else {
                return assertTenDigitsTin(tin);
            }

        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }

    private boolean checkNineDigitsTin(String tin) {
        try {
            return assertNineDigitsTin(tin);
        } catch (NumberFormatException | AssertionError e) {
            return assertNineDigitsSpecialCaseTin(tin);
        }
    }

    private boolean assertNineDigitsTin(String tin) {
        int year = Integer.parseInt(tin.substring(0,2));
        int month = Integer.parseInt(tin.substring(2,4));
        int day = Integer.parseInt(tin.substring(4,6));

        assert year >= 0 && year <= 53;
        assert (month >= 1 && month <= 12) || (month >= 51 && month <= 62);
        assert day >= 1 && day <= 31;
        return true;
    }

    private boolean assertNineDigitsSpecialCaseTin(String tin) {
        assert Integer.parseInt(tin.substring(0,1)) == 6;
        int checkNumber = Integer.parseInt(tin.substring(tin.length() - 1));

        int a1 = 0;
        int multiplier = 8;
        char[] tinArray = tin.toCharArray();
        for(int i = 1; i < tinArray.length - 1; i++) {
            a1 += Character.getNumericValue(tinArray[i]) * multiplier;
            multiplier--;
        }

        return createSpecialCaseNineDigitsCheckNumberMap().get(calculateCheckNumberDifference(a1)) == checkNumber;
    }


    private boolean assertTenDigitsTin(String tin) {
        int year = Integer.parseInt(tin.substring(0,2));
        int month = Integer.parseInt(tin.substring(2,4));
        int day = Integer.parseInt(tin.substring(4,6));

        int a1 = 0;
        for (int i = 2; i <= tin.length(); i += 2) {
            a1 += Integer.parseInt(tin.substring(i-2, i));
        }

        assert Long.parseLong(tin) % 11 == 0;
        assert a1 % 11 == 0;
        assert year == 0 || (year >= 54 && year <= 99);
        assert (month >= 1 && month <= 12) || (month >= 21 && month <= 32)
                || (month >= 51 && month <= 62) || (month >= 71 && month <= 82);
        assert day >= 1 && day <= 31;
        return true;
    }

    private boolean assertEightDigitsTin(String tin) {
        int checkNumber = Integer.parseInt(tin.substring(tin.length() - 1));

        int a1 = 0;
        int multiplier = 8;
        char[] tinArray = tin.toCharArray();
        for(int i = 0; i < tinArray.length - 1; i++) {
            a1 += Character.getNumericValue(tinArray[i]) * multiplier;
            multiplier--;
        }

        return calculateCheckNumberDifference(a1) % 10 == checkNumber;

    }


    private int calculateCheckNumberDifference(int a1) {
        int a2;
        if(a1 % 11 == 0) {
            a2 = a1 + 11;
        } else {
            a2 = (int) (Math.ceil(a1/11.0) * 11);
        }

        return Math.abs(a2 - a1);
    }

    private Map<Integer, Integer> createSpecialCaseNineDigitsCheckNumberMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 8);
        map.put(2, 7);
        map.put(3, 6);
        map.put(4, 5);
        map.put(6, 3);
        map.put(7, 2);
        map.put(8, 1);
        map.put(9, 0);
        map.put(10, 9);
        map.put(11, 8);

        return map;
    }

}
