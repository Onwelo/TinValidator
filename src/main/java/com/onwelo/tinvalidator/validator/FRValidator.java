package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class FRValidator implements Validator {

	private static final Pattern PATTERN = Pattern.compile("[0-9A-Z]{2}\\d{9}");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            String firstCharacter = tin.substring(0, 1);
            String secondCharacter = tin.substring(1, 2);

            if(!isNumeric(firstCharacter) || !isNumeric(secondCharacter)) {
                return true;
            }

            int checkNumber = Integer.parseInt(tin.substring(0, 2));
            long r = (Long.parseLong(tin.substring(2)) * 100 + 12) % 97;

            return checkNumber == r;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isNumeric(String subject) {
        return Pattern.matches("\\d", subject);
    }

}