package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <code>RestParsingProperties</code>
 * <p>The rest parsing properties class.</p>
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  org.springframework.boot.context.properties.ConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nichetoolkit.rest.parsing")
public class RestParsingProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean enabled = false;
    /**
     * <code>formData</code>
     * {@link java.lang.Boolean} <p>The <code>formData</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean formData = true;
    /**
     * <code>formFile</code>
     * {@link java.lang.Boolean} <p>The <code>formFile</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean formFile = false;
    /**
     * <code>multiFile</code>
     * {@link java.lang.Boolean} <p>The <code>multiFile</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean multiFile = false;

    /**
     * <code>ignored</code>
     * {@link io.github.nichetoolkit.rest.configure.RestParsingProperties.ParsingIgnored} <p>The <code>ignored</code> field.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties.ParsingIgnored
     * @see  org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ParsingIgnored ignored = new ParsingIgnored();

    /**
     * <code>ParsingIgnored</code>
     * <p>The parsing ignored class.</p>
     * @see  lombok.Setter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Setter
    public static class ParsingIgnored {
        /**
         * <code>fields</code>
         * {@link java.lang.String} <p>The <code>fields</code> field.</p>
         * @see  java.lang.String
         */
        private String[] fields;
        /**
         * <code>values</code>
         * {@link java.lang.String} <p>The <code>values</code> field.</p>
         * @see  java.lang.String
         */
        private String[] values = new String[]{"null", "undefined"};

        /**
         * <code>getFields</code>
         * <p>The get fields getter method.</p>
         * @return  {@link java.util.List} <p>The get fields return object is <code>List</code> type.</p>
         * @see  java.util.List
         */
        public List<String> getFields() {
            if (GeneralUtils.isNotEmpty(this.fields)) {
                return Arrays.asList(this.fields);
            }
            return Collections.emptyList();
        }

        /**
         * <code>getValues</code>
         * <p>The get values getter method.</p>
         * @return  {@link java.util.List} <p>The get values return object is <code>List</code> type.</p>
         * @see  java.util.List
         */
        public List<String> getValues() {
            if (GeneralUtils.isNotEmpty(this.values)) {
                return Arrays.asList(this.values);
            }
            return Collections.emptyList();
        }
    }


}
