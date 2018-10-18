package com.onwelo.tinvalidator.facade;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import com.onwelo.tinvalidator.factory.ValidatorFactory;
import com.onwelo.tinvalidator.validator.Validator;

public class TinValidator {

    public static boolean validate(String tin) throws NoSuchValidatorException {
        String countryCode = extractCountryCode(tin);

        Validator nationalValidator = ValidatorFactory.findValidatorForCountryCode(countryCode);

        String number = nationalValidator.trim(extractActualNumber(tin));

        return nationalValidator.matchRegex(number) && nationalValidator.computeControlSum(number);
    }

    private static String extractCountryCode(String tin){
        return tin.substring(0,2);
    }

    private static String extractActualNumber(String tin){
        return tin.substring(2);
    }
}
