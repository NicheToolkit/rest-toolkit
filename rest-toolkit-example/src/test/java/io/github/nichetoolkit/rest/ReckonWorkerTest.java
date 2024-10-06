package io.github.nichetoolkit.rest;

/**
 * <code>ReckonWorkerTest</code>
 * <p>The type reckon worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ReckonWorkerTest {
    /**
     * <code>DIGITS</code>
     * <p>The <code>DIGITS</code> field.</p>
     */
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


    /**
     * <code>ennex</code>
     * <p>The method.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
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

    /**
     * <code>denex</code>
     * <p>The method.</p>
     * @param code {@link java.lang.String} <p>The code parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Long} <p>The return object is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     */
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


    /**
     * <code>annex</code>
     * <p>The method.</p>
     * @param level {@link java.lang.Integer} <p>The level parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Long} <p>The return object is <code>Long</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Long
     */
    public static Long annex(Integer level) {
        long value = 0L;
        for (int index = 0; index < level; index ++) {
            long l = 1L << index;
            value = value | l;
        }
        return value;
    }


    /**
     * <code>main</code>
     * <p>The entry point of application.</p>
     * @param args {@link java.lang.String} <p>The input arguments.</p>
     * @see java.lang.String
     */
    public static void main(String[] args) {

//        Long max = Long.MAX_VALUE;
//        System.out.println("max: " +max);
//        Long v63 = annex(63);
//        System.out.println("v63: " +v63);
//
//
//        Long annex = annex(DIGITS.length);
//        System.out.println("annex: " +annex);
//        String ennex = ennex(annex);
//        System.out.println("ennex: " +ennex);
//        Long denex = denex(ennex);
//        System.out.println("denex: " +denex);



        String ennex = ennex(1833036771458285568L);
        System.out.println("ennex: " +ennex);


    }
}
