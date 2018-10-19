package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

public class LUValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]{13}");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            return computeControlSumC12(tin)
                    && computeControlSumC13(tin);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean computeControlSumC12(String tin) {
        int[] weights = {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int d = Integer.parseInt(tin.substring(i, i + 1));
            int n = d * weights[i];
            // if result has two digits we are adding them
            sum += n < 9 ? n : n % 10 + n / 10;
        }
        return sum % 10 == 0;
    }

    private boolean computeControlSumC13(String tin) {
        final int[][] d = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 0, 6, 7, 8, 9, 5},
                {2, 3, 4, 0, 1, 7, 8, 9, 5, 6},
                {3, 4, 0, 1, 2, 8, 9, 5, 6, 7},
                {4, 0, 1, 2, 3, 9, 5, 6, 7, 8},
                {5, 9, 8, 7, 6, 0, 4, 3, 2, 1},
                {6, 5, 9, 8, 7, 1, 0, 4, 3, 2},
                {7, 6, 5, 9, 8, 2, 1, 0, 4, 3},
                {8, 7, 6, 5, 9, 3, 2, 1, 0, 4},
                {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
        };
        final int[][] p = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 5, 7, 6, 2, 8, 3, 0, 9, 4},
                {5, 8, 0, 3, 7, 9, 6, 1, 4, 2},
                {8, 9, 1, 6, 0, 4, 3, 5, 2, 7},
                {9, 4, 5, 3, 1, 2, 6, 8, 7, 0},
                {4, 2, 8, 6, 5, 7, 3, 9, 0, 1},
                {2, 7, 9, 3, 8, 0, 6, 4, 1, 5},
                {7, 0, 4, 6, 9, 1, 3, 2, 5, 8}
        };

        //fill tin array
        int[] tinArray = new int[12];
        {
            int i = 12, j = 0;
            while (i >= 0) {
                //we skip 12th element
                if (i == 11) {
                    --i;
                    continue;
                }

                tinArray[j] = Integer.parseInt(tin.substring(i, i + 1));
                j++;
                --i;
            }
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum = d[sum][p[i % 8][tinArray[i]]];
        }

        return sum == 0;
    }
}
