package com.onwelo.tinvalidator.facade;

import com.onwelo.tinvalidator.exception.NoSuchValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("TIN")
class TinValidatorTest {

    @ParameterizedTest(name = "{0} should be {1}")
    @CsvFileSource(resources = "/testtins.csv")
    void checkTinsFromFile(String tin, Boolean result) throws NoSuchValidatorException {
        Boolean isValid = TinValidator.validate(tin);
        Assertions.assertEquals(result, isValid);
    }
}
