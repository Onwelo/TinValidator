package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("French TIN should")
class FRValidatorTest {

	@ParameterizedTest
	@DisplayName("contain eleven characters")
	@CsvSource(value = {
			"AB234567890, true",
			"01234567890, true",
			"AB1111111, false",
			"DD11111111, false",
			"1, false",
			"999999999, false",})
	void tinShouldContainElevenCharacters(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.matchRegex(tin));
	}

	@ParameterizedTest
	@DisplayName("not contain letters except first two characters (only capitalized are allowed)")
	@CsvSource(value = {
			"AB234567890, true",
			"CD354124244, true",
			"FF543678888, true",
			"ff543678888, false",
			"XX41234444A, false",
			"XX41sa44A4S4, false"
	})
	void tinShouldNotContainLettersExceptFirstTwoCharacters(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.matchRegex(tin));
	}

	@ParameterizedTest
	@DisplayName("compute control sum correctly")
	@CsvSource(value = {
			"00300076965, true",
			"03552081317, true",
			"03784359069, true",
			"39192312345, false",
			"94716252522, false",
			"57772177372, false",
	})
	void tinShouldComputeControlSum(final String tin, final boolean expectedResult) {
		Validator v = new FRValidator();
		Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
	}
}