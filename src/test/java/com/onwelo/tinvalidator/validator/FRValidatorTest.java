package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("French TIN should")
public class FRValidatorTest {

	@ParameterizedTest
	@DisplayName("contain thirteen digits")
	@CsvSource(value = { "1111111111, false", "11111111111, false", "1, false", "999999999, false",
			"0123456789012, true" })
	void tinShouldContainThirteenDigits(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.matchRegex(tin));
	}

	@ParameterizedTest
	@DisplayName("not contain letters")
	@CsvSource(value = { "1111111111111, true", "111111a111321, false", "x, false" })
	void tinShouldNotContainLetters(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.matchRegex(tin));
	}

	@ParameterizedTest
	@DisplayName("compute control sum correctly")
	@CsvSource(value = { "3023217600053, true", "1230967654123, false", "0001123456789, false" })
	void tinShouldComputeControlSum(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
	}
}