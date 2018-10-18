package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Czech Republic TIN should")
class CZValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten or nine digits")
    @CsvSource(value = {
            "1, false",
            "99999, false",
            "111111111111111111111111/1111111111115, false",
            "110103999, true",
            "1103043222, true"
    })
    void tinShouldContainTenOrNineDigits(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }


    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "1a0103a99, false",
            "110103999, true",
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain valid month number")
    @CsvSource(value = {
            "112503999, false",
            "112503999, false",
            "113503999, false",
            "112903999, false",
            "119903999, false",
            "117903999, false",
            "1149039999, false",
            "1199039999, false",
            "110103999, true",
            "111203999, true",
            "110103999, true",
            "111203999, true",
            "115203999, true",
            "116203999, true",
            "1182039999, true",
            "1172039999, true",

    })
    void tinShouldContainValidMonthNumber(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain valid day number")
    @CsvSource(value = {
            "110193999, false",
            "111283999, false",
            "110173999, false",
            "111263999, false",
            "1152539999, false",
            "1162439999, false",
            "110103999, true",
            "111203999, true",
            "110103999, true",
            "1182039999, true",
            "1172039999, true",

    })
    void tinShouldContainValidDayNumber(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "1116011234, false",
            "1140011234, false",
            "1199011234, false",
            "1199441234, false",
            "1199551234, false",
            "1199321234, false",
            "110501123, true",
            "1105011234, true",
            "110501/123, true",
            "110501/1234, true",
            "1121011234, true",
            "1177011234, true",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
