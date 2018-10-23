package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Greece TIN should")
class ELValidatorTest {

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "001231231, true",
            "11111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult) {
        Validator v = new ELValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111111E, false",
            "111111a11, false",
            "xxxxxxxxx, false",
            "999999999, true"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult) {
        Validator v = new ELValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("always return true, because there is no control sum")
    @CsvSource(value = {
            "000000024, true",
            "800420948, true",
            "000100024, false",
            "800420940, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult) {
        Validator v = new ELValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
