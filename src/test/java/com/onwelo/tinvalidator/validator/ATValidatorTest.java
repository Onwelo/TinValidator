package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Austrian TIN should")
public class ATValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eight digits")
    @CsvSource(value = {
            "U31736581, true",
            "U1111111111, false",
            "U1, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "U11111118, true",
            "U11111a11, false",
            "Uxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "U10223006, true",
            "U31736583, true",
            "U10223005, false",
            "931736582, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
