package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Bulgarian TIN should")
public class BGValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine or ten digits")
    @CsvSource(value = {
            "111111111, true",
            "1111111110, true",
            "11111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainNineOrTenDigits(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111111, true",
            "1111111110, true",
            "111111a111, false",
            "xxxxxxxxxx, false",
            "xxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "101004508, true",
            "0041010002, true",
            "0000100159, true",
            "0000100153, true",
            "101014508, false",
            "0041010012, false",
            "0000101159, false",
            "0000100253, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new BGValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
