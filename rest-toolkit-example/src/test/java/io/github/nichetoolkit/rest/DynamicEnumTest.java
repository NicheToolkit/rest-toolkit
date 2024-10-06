package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.userlog.LoggingType;

/**
 * <code>DynamicEnumTest</code>
 * <p>The type dynamic enum test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class DynamicEnumTest {


    /**
     * <code>main</code>
     * <p>The entry point of application.</p>
     * @param args {@link java.lang.String} <p>The input arguments.</p>
     * @see java.lang.String
     */
    public static void main(String[] args) {
        LoggingType a3 = RestEnum.resolve(LoggingType.class, "A_3", new Class<?>[]{String.class, String.class}, new String[]{"default", "Active"});
        System.out.println(a3);

    }
}
