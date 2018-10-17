package com.onwelo.tinvalidator.factory;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import com.onwelo.tinvalidator.validator.FRValidator;
import com.onwelo.tinvalidator.validator.PLValidator;
import com.onwelo.tinvalidator.validator.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("ValidatorFactory should")
public class ValidatorFactoryTest {

    @Test
    @DisplayName("throw exception on instantiating Validator")
    void throwExceptionOnValidatorInstantiating(){
        Executable exe = () -> ValidatorFactory.findValidatorForCountryCode("");
        Assertions.assertThrows(NoSuchValidatorException.class, exe);
    }

    @Test
    @DisplayName("throw exception on not existing country code")
    void throwExceptionOnNotExistingCountryCode(){
        Executable exe = () -> ValidatorFactory.findValidatorForCountryCode("XX");
        Assertions.assertThrows(NoSuchValidatorException.class, exe);
    }

    @Test
    @DisplayName("fetch PLValidator by 'PL' string")
    void PLValidatorFetchingTest() throws Exception{
        Validator v = ValidatorFactory.findValidatorForCountryCode("PL");
        Assertions.assertTrue(v instanceof PLValidator);
    }

    @Test
    @DisplayName("fetch FRValidator by 'FR' string")
    void FRValidatorFetchingTest() throws Exception{
        Validator v = ValidatorFactory.findValidatorForCountryCode("FR");
        Assertions.assertTrue(v instanceof FRValidator);
    }
}
