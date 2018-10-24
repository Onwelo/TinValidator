package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GBValidatorTest {

    @ParameterizedTest
    @DisplayName("contain five, nine or twelve characters")
    @CsvSource(value = {
            "GD111, true",
            "HA666, true",
            "111111111, true",
            "111111111111, true",
            "11111111, false",
            "1111111111, false",
            "11111111111, false",
            "1111111111111, false",
    })
    void tinShouldContainAllowedNumberOfCharacters(final String tin, final boolean expectedResult){
        Validator v = new GBValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("be in ranges: GD000-GD499 and HA500-HA999")
    @CsvSource(value = {
            "GD111, true",
            "GD555, false",
            "HA666, true",
            "HA333, false",
            "GA111, false",
            "HD776, false"
    })
    void shotTinRanges(final String tin, final boolean expectedResult){
        Validator v = new GBValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters in 9 and 12 characters variants")
    @CsvSource(value = {
            "111111111, true",
            "111111A11, false",
            "XXXXXXXXX, false",
            "111111111111, true",
            "111111A11111, false",
            "XXXXXXXXXXXX, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new GBValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters but first two in 5 characters variant")
    @CsvSource(value = {
            "GD123, true",
            "HA567, true",
            "GDA23, false",
            "HA5A7, false",
            "GD12A, false"
    })
    void tinShouldNotContainLettersButFirstTwo(final String tin, final boolean expectedResult){
        Validator v = new GBValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "GD103, true",
            "999000005, true",
            "434031494, true",
            "999000396, false",
            "000000000, false",
            "999000396, false",
            "010123456, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new GBValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
