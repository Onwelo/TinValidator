package com.onwelo.tinvalidator.facade;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import com.onwelo.tinvalidator.factory.ValidatorFactory;
import com.onwelo.tinvalidator.validator.Validator;

public class TinValidator {

    /**
     * <p>Entry point of validating mechanism.</p>
     * <p>Parameter must be full VAT ID number with leading country code
     * compatible with ISO 3166-1 alpha-2. Each number is validated with
     * both RegEx pattern and calculated control sum.</p>
     *
     * @param tin
     * @return if validation succeeded
     * @throws NoSuchValidatorException when not implemented validator
     * for provided country code
     */
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
