package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Dutch TIN should")
public class NLValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits plus Bxx")
    @CsvSource(value = {
            "005033019B01, true",
            "1111111111B01, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldHaveProperLength(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters before and after B")
    @CsvSource(value = {
            "005033019B99, true",
            "11111a111B01, false",
            "xxxxxxxxxBxx, false",
            "005033019BBB, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "005033019B01, true",
            "006292227B01, true",
            "121745417B01, true",
            "128297906B01, true",
            "147804668B01, true",
            "173389909B01, true",
            "208560129B01, true",
            "800272912B01, true",
            "822667800B01, true",
            "822754812B01, true",
            "823363247B01, true",
            "010000445B01, false",
            "000000025B01, false",
            "000000035B01, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new NLValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
