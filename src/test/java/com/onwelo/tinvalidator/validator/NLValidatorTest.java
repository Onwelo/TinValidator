package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Dutch TIN should")
public class NLValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "005033019, true",
            "1111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainNineDigits(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "005033019, true",
            "11111a111, false",
            "xxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "005033019, true",
            "006292227, true",
            "121745417, true",
            "128297906, true",
            "147804668, true",
            "173389909, true",
            "208560129, true",
            "800272912, true",
            "822667800, true",
            "822754812, true",
            "823363247, true",
            "010000445, false",
            "000000025, false",
            "000000035, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
