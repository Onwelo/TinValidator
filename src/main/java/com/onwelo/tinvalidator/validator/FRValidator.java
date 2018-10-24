package com.onwelo.tinvalidator.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FRValidator implements Validator {

	private static final Pattern PATTERN = Pattern.compile("[0-9A-Z]{2}\\d{9}");

	public Pattern getPattern() {
		return PATTERN;
	}

	public boolean computeControlSum(String tin) {
		try {
			if(isNumeric(tin.substring(0, 2))) {
				return computeControlSumOldStyle(tin);
			} else {
				return computeControlSumNewStyle(tin);
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean computeControlSumOldStyle(String tin) {
		int checkNumber = Integer.parseInt(tin.substring(0, 2));
		long r = (Long.parseLong(tin.substring(2)) * 100 + 12) % 97;

		return checkNumber == r;
	}

	private boolean computeControlSumNewStyle(String tin) {
		Map<String, Integer> checkCharactersMap = createCheckCharactersMap();
		String firstCharacter = tin.substring(0, 1);
		String secondCharacter = tin.substring(1, 2);
		int checkCharacter1 = checkCharactersMap.get(firstCharacter);
		int checkCharacter2 = checkCharactersMap.get(secondCharacter);
		int s;

		if(isNumeric(firstCharacter)) {
			s = checkCharacter1 * 24 + (checkCharacter2 - 10);
		} else {
			s = checkCharacter1 * 34 + (checkCharacter2 - 100);
		}

		int p = s/11 + 1;
		int r1 = s % 11;
		int r2 = (Integer.parseInt(tin.substring(2)) + p) % 11;

		return r1 == r2;
	}

	private boolean isNumeric(String subject) {
		return Pattern.matches("\\d{1,2}", subject);
	}

	private Map<String, Integer> createCheckCharactersMap() {
		Map<String, Integer> map = new HashMap<>();

		for(int i = 0; i < 10; i++) {
			map.put(String.valueOf(i), i);
		}

		for(int i = 65, j = 10; i < 73; i++, j++) {
			map.put(Character.toString((char) i), j);
		}

		for(int i = 74, j = 18; i < 79; i++, j++) {
			map.put(Character.toString((char) i), j);
		}

		for(int i = 80, j = 23; i < 91; i++, j++) {
			map.put(Character.toString((char) i), j);
		}

		return map;
	}
}