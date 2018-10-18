package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public interface Validator {

    Pattern getPattern();

    default boolean matchRegex(String tin) {
        return getPattern().matcher(tin).matches();
    }

    default String trim(String tin){
        return tin.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "");
    }

    boolean computeControlSum(String tin);
}
