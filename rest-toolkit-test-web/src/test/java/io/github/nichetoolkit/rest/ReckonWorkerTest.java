package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.identity.IdentityUtils;

/**
 * <p>ReckonWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ReckonWorkerTest {
    static char[] DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };


    public static String ennex(Long value) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < DIGITS.length; index ++) {
            long l = 1L << index;
            if ((value & l) == l) {
                builder.append(DIGITS[index]);
            }
        }
        return builder.toString();
    }

    public static Long denex(String code) {
        long value = 0L;
        String digits = new String(DIGITS);
        char[] charArray = code.toCharArray();
        for (char valueChar: charArray) {
            if (digits.contains(String.valueOf(valueChar))) {
                int index = digits.indexOf(valueChar);
                long l = 1L << index;
                value = value | l;
            }
        }
        return value;
    }


    public static Long annex(Integer level) {
        long value = 0L;
        for (int index = 0; index < level; index ++) {
            long l = 1L << index;
            value = value | l;
        }
        return value;
    }


    public static void main(String[] args) {

        Long max = Long.MAX_VALUE;
        System.out.println("max: " +max);
        Long v63 = annex(63);
        System.out.println("v63: " +v63);
        Long annex = annex(DIGITS.length);
        System.out.println("annex: " +annex);
        String ennex = ennex(annex);
        System.out.println("ennex: " +ennex);
        Long denex = denex(ennex);
        System.out.println("denex: " +denex);


    }
}
