package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class ESValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9A-Z]\\d{7}[0-9A-Z]");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            Pattern assertFirstCharactersAllowedIfLastIsALetter = Pattern.compile("[A-HK-NPQ-SW-Z0-9]");
            Pattern assertFirstCharactersAllowedIfLastIsADigit = Pattern.compile("[A-HJUV]");
            Pattern firstCharactersForNonNationalJuridicalEntities = Pattern.compile("[A-HNPQ-SW]");
            Pattern firstCharactersForPhysicalPersons = Pattern.compile("[K-MX-Z0-9]");

            String lastCharacter = tin.substring(tin.length() - 1);
            String firstCharacter = tin.substring(0, 1);
            boolean isLastCharacterNumeric = isNumeric(lastCharacter);

            assert (isLastCharacterNumeric && assertFirstCharactersAllowedIfLastIsADigit.matcher(firstCharacter).matches())
                    || (!isLastCharacterNumeric && assertFirstCharactersAllowedIfLastIsALetter.matcher(firstCharacter).matches());

            if (!isLastCharacterNumeric && firstCharactersForNonNationalJuridicalEntities.matcher(firstCharacter).matches()) {
                return computeControlSumForNonNationalJuridicalEntities(tin);
            } else if (!isLastCharacterNumeric && firstCharactersForPhysicalPersons.matcher(firstCharacter).matches()) {
                return computeControlSumForPhysicalPersons(tin);
            } else {
                return computeControlSumForNationalJuridicalEntities(tin);
            }
        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }

    private boolean computeControlSumForNonNationalJuridicalEntities(String tin) {
        final String[] checkCharacters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String lastCharacter = tin.substring(tin.length() - 1);

        return checkCharacters[calculateRForJuridicalEntities(tin) - 1].equals(lastCharacter);
    }


    private boolean computeControlSumForNationalJuridicalEntities(String tin) {
        String lastCharacter = tin.substring(tin.length() - 1);

        return Integer.parseInt(lastCharacter) == calculateRForJuridicalEntities(tin) % 10;
    }

    private boolean computeControlSumForPhysicalPersons(String tin) {
        final String[] checkCharacters = new String[]{"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N",
                "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        String lastCharacter = tin.substring(tin.length() - 1);
        String firstCharacter = tin.substring(0, 1);
        int c1 = 0;

        if (firstCharacter.equals("Y")) {
            c1 = 1;
        } else if (firstCharacter.equals("Z")) {
            c1 = 2;
        }

        int startIndex = 1;
        if (!isNumeric(firstCharacter) && c1 > 0) {
            firstCharacter = String.valueOf(c1);
            tin = tin.substring(1);
            tin = firstCharacter.concat(tin);
            startIndex = 0;
        } else if(isNumeric(firstCharacter)) {
            startIndex = 0;
        }

        int r = Integer.parseInt(tin.substring(startIndex, tin.length() - 1)) % 23 + 1;

        return checkCharacters[r - 1].equals(lastCharacter);
    }

    private int calculateRForJuridicalEntities(String tin) {
        char[] tinArray = tin.toCharArray();
        int sum1 = Character.getNumericValue(tinArray[2]) + Character.getNumericValue(tinArray[4])
                + Character.getNumericValue(tinArray[6]);
        int sum2 = 0;

        for (int i = 1; i <= tinArray.length - 1; i += 2) {
            int currentNumber = Character.getNumericValue(tinArray[i]);
            sum2 += currentNumber / 5 + (currentNumber * 2) % 10;
        }

        return 10 - (sum1 + sum2) % 10;
    }


    private boolean isNumeric(String subject) {
        return Pattern.matches("\\d", subject);
    }
}