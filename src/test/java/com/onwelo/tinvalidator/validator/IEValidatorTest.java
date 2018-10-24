package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Irish TIN should")
public class IEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain seven digits and one or two letter")
    @CsvSource(value = {
            "1234567T, true",
            "1234577A, true",
            "1111111AA, true",
            "1111111AX, false",
            "1A, false",
            "99999999, false",
            "A9999999, false"
    })
    void tinShouldContainSevenDigitsAndLetter(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters but last one or two")
    @CsvSource(value = {
            "1234567T, true",
            "1234567TW, true",
            "1234577IA, true",
            "111111A1, false",
            "XXXXXXXX, false",
            "999A9999, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "0000002D, true",
            "6336982T, true",
            "1409095C, true",
            "8Z49289F, true",
            "9F70164P, true",
            "1111111B, false",
            "1245777W, false",
            "1234577E, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
