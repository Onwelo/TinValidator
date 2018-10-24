package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Sweden TIN should")
class SEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain twelve digits")
    @CsvSource(value = {
            "123456784113, true",
            "132432543513, true",
            "1234567841131, false",
            "12345678411, false",
            "244, false",
            "2423414512241515125, false",
            "2, false",
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new SEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "123456784113, true",
            "132432543513, true",
            "z32432543513, false",
            "1asd32543513, false",
            "13243asdd513, false",
            "fdsfdsgfdgfd, false"
    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new SEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("check control sum")
    @CsvSource(value = {
            "556654042201,true",
            "556785615701,true",
            "556188840301,false",
            "000000002301,false",
            "000000003301,false"
    })
    void tinShouldBeDivisibleByElevenWithoutReminder(final String tin, final boolean expectedResult){
        Validator v = new SEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
