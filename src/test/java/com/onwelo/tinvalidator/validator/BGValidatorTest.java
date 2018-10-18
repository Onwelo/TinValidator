package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Bulgarian TIN should")
public class BGValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten digits")
    @CsvSource(value = {
            "1111111110, true",
            "11111111111, false",
            "1, false",
            "999999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "1111111110, true",
            "111111a111, false",
            "xxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "1111111110, true",
            "7501010010, true",
            "1111111210, false",
            "7501110010, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
