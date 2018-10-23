package com.onwelo.tinvalidator.validator;

import java.util.regex.Pattern;

//11 and 11 digits format: https://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/tax-identification-numbers/Luxembourg-TIN.pdf
//8 digit format: https://www.braemoor.co.uk/software/vattestx.php
public class LUValidator implements Validator {

    private static final Pattern PATTERN = Pattern.compile("([0-9]{8})|([0-9]{11})|([0-9]{13})");

    public Pattern getPattern() {
        return PATTERN;
    }

    public boolean computeControlSum(String tin) {
        try {
            if(tin.length() == 8) {
                return computeControlSumC7And8(tin);
            } else if (tin.length() == 11) {
                return computeControlSumC11(tin);
            } else if (tin.length() == 13) {
                return computeControlSumC12(tin)
                        && computeControlSumC13(tin);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean computeControlSumC7And8(String tin) {
        return Integer.parseInt(tin.substring(0,6)) % 89 == Integer.parseInt(tin.substring(6,8));
    }

    private boolean computeControlSumC11(String tin) {
        int[] weights = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int d = Integer.parseInt(tin.substring(i, i + 1));
            sum += d * weights[i];
        }
        sum = sum % 11;
        sum = sum == 0 ? 0 : 11 - sum;
        return sum == Integer.parseInt(tin.substring(10, 11));
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
