package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Spanish TIN should")
class ESValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine characters")
    @CsvSource(value = {
            "111111111, true",
            "A1111111B, true",
            "A1212341B, true",
            "1111111111, false",
            "1, false",
            "9999999999AAA, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new ESValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain digit or capitalized letter as first and last character and remaining as digits")
    @CsvSource(value = {
            "111111111, true",
            "A1111111B, true",
            "A1212341B, true",
            "A12123410, true",
            "21212341B, true",
            "a1212341b, false",
            "AA21A341B, false",
            "^AV1A34VB, false",
            "^AV1A34V%, false",
            "BBBCCBBBB, false",
    })
    void tinShouldContainDigitOrCapitalizedLetterAsFirstAndLastCharacterAndRemainingAsDigits(final String tin, final boolean expectedResult){
        Validator v = new ESValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }


    @ParameterizedTest
    @DisplayName("compute control sum for non national juridical entities")
    @CsvSource(value = {
            "A0011012B, true",
            "G0011444G, true",
            "G0011485J, true",
            "G3211455J, false",
            "B3200455W, false",
            "C969439SH, false",
    })
    void tinShouldComputeControlSumForNonNationalJuridicalEntities(final String tin, final boolean expectedResult){
        Validator v = new ESValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum for national juridical entities")
    @CsvSource(value = {
            "A08017535, true",
            "A08037236, true",
            "A31000268, true",
            "B17821679, true",
            "C23121515, false",
            "H67775334, false",
            "J21124899, false",
            "U53251557, false",
    })
    void tinShouldComputeControlSumForNationalJuridicalEntities(final String tin, final boolean expectedResult){
        Validator v = new ESValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }


    @ParameterizedTest
    @DisplayName("compute control sum for physical persons")
    @CsvSource(value = {
            "Y1234567X, true",
            "Z1234567R, true",
            "K1431237R, false",
            "L2423425R, false",
            "Z2745734R, false",
    })
    void tinShouldComputeControlSumForPhysicalPersons(final String tin, final boolean expectedResult){
        Validator v = new ESValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

}
