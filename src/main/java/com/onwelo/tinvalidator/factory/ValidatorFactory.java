package com.onwelo.tinvalidator.factory;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import com.onwelo.tinvalidator.validator.Validator;

public class ValidatorFactory {

    private static final String PACKAGE_PATH = "com.onwelo.tinvalidator.validator.";

    public static Validator findValidatorForCountryCode(String code) throws NoSuchValidatorException {
        try {
            return (Validator) Class.forName(PACKAGE_PATH + code + "Validator").newInstance();
        } catch (Exception e){
            throw new NoSuchValidatorException("Validator for country code: " + code + " cannot be fetched.", e);
        }
    }
}
