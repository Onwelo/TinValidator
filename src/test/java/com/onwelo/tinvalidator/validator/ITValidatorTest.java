package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Italian TIN should")
class ITValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven digits")
    @CsvSource(value = {
            "11111111111, true",
            "1111111111, false",
            "111111111111111, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new ITValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "11111111111, true",
            "11111111a11, false",
            "111c1b11b11, false",

    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new ITValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("assert c8 c9 c10")
    @CsvSource(value = {
            "00000010215, true",
            "01654960473, true",
            "10000100015, true",
            "00000017775, false",
            "01654961773, false",
            "10000101235, false",


    })
    void tinShouldAssertc8c9c10(final String tin, final boolean expectedResult){
        Validator v = new ITValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }


    @ParameterizedTest
    @DisplayName("compute control sum")
    @CsvSource(value = {
            "00000010215, true",
            "01654960473, true",
            "10000100015, true",
            "08146570018, true",
            "02458160245, true",
            "00144659992, true",
            "03084300171, true",
            "03454300171, false",
            "00502441208, false",
            "23502441608, false",

    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new ITValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }


}
