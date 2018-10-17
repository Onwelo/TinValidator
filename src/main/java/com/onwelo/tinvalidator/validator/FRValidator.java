package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class FRValidator implements Validator {

	private static final Pattern PATTERN = Pattern.compile("[0-4]\\d{12}");

	public Pattern getPattern() {
		return PATTERN;
	}

	public boolean computeControlSum(String tin) {
		try {
			long num = Long.parseLong(tin.substring(0, 10)), rem = num % 511;
			if (rem >= 100) {
				return false;
			} else {
				String c = ((rem < 10) ? "00" : "0") + String.valueOf(rem);
				return tin.substring(10).equals(c);
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
}