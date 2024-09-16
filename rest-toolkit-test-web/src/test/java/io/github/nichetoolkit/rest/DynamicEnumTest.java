package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.userlog.LoggingType;

/**
 * <p>DynamicEnumTest</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DynamicEnumTest {


    public static void main(String[] args) {
        LoggingType a3 = RestEnum.resolve(LoggingType.class, "A_3", new Class<?>[]{String.class, String.class}, new String[]{"default", "Active"});
        System.out.println(a3);

    }
}
