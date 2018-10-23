package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class BGValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{9,10}");

    public Pattern getPattern(){
        return PATTERN;
    }

    public boolean computeControlSum(String tin){
        if (tin.length() == 9) return computeLegalEntities(tin);
        return computePhysicalPerson(tin) || computeForeigners(tin) || computeOthers(tin);
    }

    private boolean computePhysicalPerson(String tin) {
        int[] weights = {2, 4, 8, 5, 10, 9, 7, 3, 6};
        try {
            int month = Integer.parseInt(tin.substring(2,4));
            int day = Integer.parseInt(tin.substring(4,6));
            assert (month >0 && month <= 12) || (month > 20 && month <=32) || (month > 40 && month <= 52);
            if (month == 2 || month == 22 || month == 42) {
                assert day > 0 && day <= 29;
            } else if (month == 4 || month == 24 || month == 44 ||
                    month == 6 || month == 26 || month == 46 ||
                    month == 9 || month == 29 || month == 49 ||
                    month == 11 || month == 31 || month == 51){
                assert day > 0 && day <= 30;
            } else {
                assert day > 0 && day <= 31;
            }

            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int reminder = sum % 11;
            return (reminder == 10 ? 0 : reminder) == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException|AssertionError e) {
            return false;
        }
    }

    private boolean computeLegalEntities(String tin) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int reminder = sum % 11;
            if (reminder != 10) {
                return reminder == Integer.parseInt(tin.substring(8, 9));
            }
            sum = 0;
            weights = new int[]{3, 4, 5, 6, 7, 8, 9, 10};
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            reminder = sum % 11;
            return (reminder == 10 ? 0 : reminder) == Integer.parseInt(tin.substring(8, 9));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean computeForeigners(String tin) {
        int[] weights = {21, 19, 17, 13, 11, 9, 7, 3, 1};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int reminder = sum % 10;
            return reminder == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean computeOthers(String tin) {
        int[] weights = {4, 3, 2, 7, 6, 5, 4, 3, 2};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += Integer.parseInt(tin.substring(i, i + 1)) * weights[i];
            }
            int reminder = sum % 11;
            return 11 - reminder == Integer.parseInt(tin.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
