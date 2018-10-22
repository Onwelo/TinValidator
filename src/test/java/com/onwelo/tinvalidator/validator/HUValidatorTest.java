package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Greece TIN should")
class HUValidatorTest {

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("contain ten digits and first one should be 8 or contain 8 digits")
    @CsvSource(value = {
            "8012312311, true",
            "10672101, true",
            "1012312311, false",
            "11111111111, false",
            "1, false",
            "999999919, false",
            "9999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult) {
        Validator v = new HUValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111111E, false",
            "1111111a11, false",
            "xxxxxxxxxx, false",
            "8999999999, true",
            "1111111E, false",
            "11111a11, false",
            "xxxxxxxx, false",
            "99999999, true"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult) {
        Validator v = new HUValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest(name = "For nip {0} should return {1}")
    @DisplayName("always return true, because there is no control sum")
    @CsvSource(value = {
            "8071592153, true",
            "1234567890, false",
            "10672101, true",
            "10724318, true",
            "65730981, false",
            "65731980, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult) {
        Validator v = new HUValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
