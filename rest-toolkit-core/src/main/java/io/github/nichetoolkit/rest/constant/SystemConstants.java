package io.github.nichetoolkit.rest.constant;

/**
 * <code>SystemConstants</code>
 * <p>The type system constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface SystemConstants {
    /**
     * <code>NAME</code>
     * {@link java.lang.String} <p>the constant <code>NAME</code> field.</p>
     * @see java.lang.String
     */
    String NAME = "os.name";
    /**
     * <code>VERSION</code>
     * {@link java.lang.String} <p>the constant <code>VERSION</code> field.</p>
     * @see java.lang.String
     */
    String VERSION = "os.version";
    /**
     * <code>ARCH</code>
     * {@link java.lang.String} <p>the constant <code>ARCH</code> field.</p>
     * @see java.lang.String
     */
    String ARCH = "os.arch";
    /**
     * <code>LANGUAGE</code>
     * {@link java.lang.String} <p>the constant <code>LANGUAGE</code> field.</p>
     * @see java.lang.String
     */
    String LANGUAGE = "user.language";

    /**
     * <code>DIGITS</code>
     * <p>the constant <code>DIGITS</code> field.</p>
     */
    byte[] DIGITS = {
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
}
