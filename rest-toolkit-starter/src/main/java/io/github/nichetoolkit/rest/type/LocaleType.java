package io.github.nichetoolkit.rest.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Locale;
import java.util.Optional;

/**
 * <code>LocaleType</code>
 * <p>The locale type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum LocaleType implements RestValue<String, Locale> {
    /**
     * <code>ENGLISH</code>
     * <p>The english locale type field.</p>
     */
    ENGLISH("english", Locale.ENGLISH),
    /**
     * <code>FRENCH</code>
     * <p>The french locale type field.</p>
     */
    FRENCH("french", Locale.FRENCH),
    /**
     * <code>GERMAN</code>
     * <p>The german locale type field.</p>
     */
    GERMAN("german", Locale.GERMAN),
    /**
     * <code>ITALIAN</code>
     * <p>The italian locale type field.</p>
     */
    ITALIAN("italian", Locale.ITALIAN),
    /**
     * <code>JAPANESE</code>
     * <p>The japanese locale type field.</p>
     */
    JAPANESE("japanese", Locale.JAPANESE),
    /**
     * <code>KOREAN</code>
     * <p>The korean locale type field.</p>
     */
    KOREAN("korean", Locale.KOREAN),
    /**
     * <code>CHINESE</code>
     * <p>The chinese locale type field.</p>
     */
    CHINESE("chinese", Locale.CHINESE),
    /**
     * <code>FRANCE</code>
     * <p>The france locale type field.</p>
     */
    FRANCE("france", Locale.FRANCE),
    /**
     * <code>GERMANY</code>
     * <p>The germany locale type field.</p>
     */
    GERMANY("germany", Locale.GERMANY),
    /**
     * <code>ITALY</code>
     * <p>The italy locale type field.</p>
     */
    ITALY("italy", Locale.ITALY),
    /**
     * <code>JAPAN</code>
     * <p>The japan locale type field.</p>
     */
    JAPAN("japan", Locale.JAPAN),
    /**
     * <code>KOREA</code>
     * <p>The korea locale type field.</p>
     */
    KOREA("korea", Locale.KOREA),
    /**
     * <code>CHINA</code>
     * <p>The china locale type field.</p>
     */
    CHINA("china", Locale.CHINA),
    /**
     * <code>TAIWAN</code>
     * <p>The taiwan locale type field.</p>
     */
    TAIWAN("taiwan", Locale.TAIWAN),
    /**
     * <code>CANADA</code>
     * <p>The canada locale type field.</p>
     */
    CANADA("canada", Locale.CANADA),
    /**
     * <code>PRC</code>
     * <p>The prc locale type field.</p>
     */
    PRC("prc", Locale.PRC),
    /**
     * <code>UK</code>
     * <p>The uk locale type field.</p>
     */
    UK("uk", Locale.UK),
    /**
     * <code>US</code>
     * <p>The us locale type field.</p>
     */
    US("us", Locale.US),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.util.Locale} <p>The <code>value</code> field.</p>
     * @see java.util.Locale
     */
    private final Locale value;

    /**
     * <code>LocaleType</code>
     * <p>Instantiates a new locale type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.util.Locale} <p>The value parameter is <code>Locale</code> type.</p>
     * @see java.lang.String
     * @see java.util.Locale
     */
    LocaleType(String key, Locale value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @JsonValue
    @Override
    public Locale getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link LocaleType} <p>The parse key return object is <code>LocaleType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static LocaleType parseKey(String key) {
        LocaleType sortTypeEnum = RestValue.parseKey(LocaleType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(LocaleType.ENGLISH);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.util.Locale} <p>The value parameter is <code>Locale</code> type.</p>
     * @return {@link LocaleType} <p>The parse value return object is <code>LocaleType</code> type.</p>
     * @see java.util.Locale
     */
    public static LocaleType parseValue(Locale value) {
        LocaleType sortTypeEnum = RestValue.parseValue(LocaleType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(LocaleType.ENGLISH);
    }

}
