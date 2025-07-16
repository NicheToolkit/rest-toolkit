package io.github.nichetoolkit.rest;

import java.io.Serializable;
import java.util.Set;

/**
 * <code>RestI18nBasename</code>
 * <p>The rest i 18 n basename interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk1.8
 */
public interface RestI18nBasename extends Serializable {
    /**
     * <code>getBaseNames</code>
     * <p>The get base names getter method.</p>
     * @return {@link java.util.Set} <p>The get base names return object is <code>Set</code> type.</p>
     * @see java.util.Set
     */
    Set<String> getBaseNames();
}

