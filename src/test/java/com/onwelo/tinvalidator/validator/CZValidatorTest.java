package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Czech Republic TIN should")
class CZValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten or nine or eight digits")
    @CsvSource(value = {
            "1, false",
            "99999, false",
            "111111111111111111111111/1111111111115, false",
            "110103999, true",
            "1103043222, true",
            "11030432, true"
    })
    void tinShouldContainTenOrNineOrEightDigits(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain 9 at first position if it has eight digits")
    @CsvSource(value = {
            "11030432, true",
            "21030432, true",
            "31030432, true",
            "41030432, true",
            "51030432, true",
            "61030432, true",
            "71030432, true",
            "81030432, true",
            "91030432, false",
    })
    void tinShouldNotContain9AtFirstPostionIfHasEightDigits(final String tin, final boolean expectedResult){
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
    @DisplayName("should compute control sum for eight digits tin")
    @CsvSource(value = {
            "46505334, true",
            "46505336, false",
    })
    void tinShouldComputeControlSumForEightDigitsTin(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("should compute control sum for nine digits tin")
    @CsvSource(value = {
            "395601439, true",
            "640903926, true",
            "840903926, false",
    })
    void tinShouldComputeControlSumForNineDigitsTin(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("should compute control sum for ten digits tin")
    @CsvSource(value = {
            "7103192745, true",
            "6410140792, true",
            "6662251420, true",
            "7133192545, false",
            "1336692545, false",
    })
    void tinShouldComputeControlSumForTenDigitsTin(final String tin, final boolean expectedResult){
        Validator v = new CZValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
