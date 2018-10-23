package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Slovakian TIN should")
class SKValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten digits")
    @CsvSource(value = {
            "1234567843, true",
            "6235567855, true",
            "5233167877, true",
            "4234564451, true",
            "3224564431, true",
            "12244, false",
            "244, false",
            "242341451244, false",
            "2423414512241515125, false",
            "2, false",
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new SKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "1234567841, true",
            "6235567822, true",
            "5233167833, true",
            "a2233v6s, false",
            "523ddfsa, false",
            "s2s1saag, false"
    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new SKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain first digit greater than zero")
    @CsvSource(value = {
            "1572133341, true",
            "1041431841, true",
            "1083053144, true",
            "0033167866, false",
            "0003167877, false",
            "0000067888, false"
    })
    void tinShouldContainFirstDigitGreaterThanZero(final String tin, final boolean expectedResult){
        Validator v = new SKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain third digit other than one or five or six")
    @CsvSource(value = {
            "1023053144, true",
            "1033053144, true",
            "1043053144, true",
            "1073053144, true",
            "1083053144, true",
            "1093053144, true",
            "1013053144, false",
            "1053053144, false",
            "1063053144, false",

    })
    void tinShouldContainThirdDigitOtherThanOneOrFiveOrSix(final String tin, final boolean expectedResult){
        Validator v = new SKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("be divisible by eleven without reminder")
    @CsvSource(value = {
            "2020350332, true",
            "2020329278, true",
            "1025529197, true",
            "7020000317, true",
            "2020255787, true",
            "7020001680, false",
            "5407062531, false",
            "4125513266, false",
            "1247999965, false",
            "6545257343, false",
    })
    void tinShouldBeDivisibleByElevenWithoutReminder(final String tin, final boolean expectedResult){
        Validator v = new SKValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
