package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class ITValidator implements Validator {

    // alternative pattern for individuals TIN
   /* private static final Pattern PATTERN =
            Pattern.compile("[A-Z]{6}[0-9L-NP-V]{2}[A-EHLMPRST][0-7L-NP-V][0-9L-NP-V][A-Z][0-9L-NP-V]{3}[A-Z]");*/

    private static final Pattern PATTERN = Pattern.compile("\\d{11}");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean computeControlSum(String tin) {
        try {
            int c8c9c10 = Integer.parseInt(tin.substring(7, 10));
            assert (c8c9c10 > 0 && c8c9c10 < 101)
                    || c8c9c10 == 121 || c8c9c10 == 888 || c8c9c10 == 999;


            char[] tinArray = tin.toCharArray();
            int sum = 0;
            int temp;
            int checkNumber = Character.getNumericValue(tinArray[tinArray.length - 1]);

            for(int i = 1; i < tinArray.length; i++) {
                if(i % 2 == 0) {
                    temp = 2 * Character.getNumericValue(tinArray[i - 1]);
                    if(temp > 9) {
                        temp -= 9;
                    }
                    sum += temp;
                } else {
                    sum += Character.getNumericValue(tinArray[i - 1]);

                }
            }

            return (10 - sum % 10) % 10 == checkNumber;
        } catch (NumberFormatException | AssertionError e) {
            return false;
        }
    }

}
