package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Portuguese TIN should")
class PTValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "122456378, true",
            "623553678, true",
            "323131678, true",
            "563456744, true",
            "321467404, true",
            "12244, false",
            "244, false",
            "242341451244, false",
            "2423414512241515125, false",
            "2, false",
    })
    void tinShouldContainNineDigits(final String tin, final boolean expectedResult){
        Validator v = new PTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "122456378, true",
            "623553678, true",
            "323131678, true",
            "563456744, true",
            "321467404, true",
            "a2233v6s, false",
            "523ddfsa, false",
            "s2s1saag, false",
            "w^$#2s13, false"
    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new PTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain first digit greater than zero")
    @CsvSource(value = {
            "502757191, true",
            "100000142, true",
            "502790610, true",
            "002757191, false",
            "000000142, false",
            "002790610, false",
    })
    void tinShouldContainFirstDigitGreaterThanZero(final String tin, final boolean expectedResult){
        Validator v = new PTValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum")
    @CsvSource(value = {
            "502757191, true",
            "100000142, true",
            "502790610, true",
            "502757192, false",
            "100000012, false",
            "100000022, false",
            "904257133, false",
            "902165642, false",
            "239887424, false",
            "124188888, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new PTValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
