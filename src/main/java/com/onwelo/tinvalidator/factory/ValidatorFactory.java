package com.onwelo.tinvalidator.factory;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import com.onwelo.tinvalidator.validator.Validator;

public class ValidatorFactory {

    public static Validator findValidatorForCountryCode(String code) throws NoSuchValidatorException {
        try {
            return (Validator) Class.forName("com.onwelo.tinvalidator.validator." + code + "Validator").newInstance();
        } catch (Exception e){
            throw new NoSuchValidatorException("Validator for country code: " + code + "cannot be fetched.", e);
        }
    }
}
