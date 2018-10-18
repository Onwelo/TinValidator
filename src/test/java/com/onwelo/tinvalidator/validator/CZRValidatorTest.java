package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Czech Republic TIN should")
class CZRValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten digits")
    @CsvSource(value = {
            "1111111111, true",
            "111111111, true",
            "1, false",
            "99999, false"
    })
    void tinShouldContainTenOrNineDigits(final String tin, final boolean expectedResult){
        Validator v = new CZRValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("be able to contain optional slash (/) between 6th and 7th character")
    @CsvSource(value = {
            "111111/1111, true",
            "1111111111, true",
            "111111/111, true",
            "111111111, true",
            "11/1111111, false",
            "1, false",
            "99999, false"
    })
    void tinShouldBeAbleToContainOptionalSlashBetween6thAnd7thCharacter(final String tin, final boolean expectedResult){
        Validator v = new CZRValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "1111111111, true",
            "111111111, true",
            "111111111a, false",
            "11111111b, false",
            "x, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new CZRValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "110501123, true",
            "1105011234, true",
            "110501/123, true",
            "110501/1234, true",
            "1121011234, true",
            "1177011234, true",
            "1155011234, true",
            "1155011234, true",
            "1116011234, false",
            "1140011234, false",
            "1199011234, false",
            "1199441234, false",
            "1199551234, false",
            "1199321234, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new CZRValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
