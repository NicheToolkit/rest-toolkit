package io.github.nichetoolkit.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>RestI18nResources</code>
 * <p>The rest i 18 n resources class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
public class RestI18nResources implements RestI18n {
    /**
     * <code>baseNames</code>
     * {@link java.util.Set} <p>The <code>baseNames</code> field.</p>
     * @see java.util.Set
     */
    private Set<String> baseNames;

    /**
     * <code>addBaseName</code>
     * <p>The add base name method.</p>
     * @param baseName {@link java.lang.String} <p>The base name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void addBaseName(String baseName) {
        this.baseNames.add(baseName);
    }

    /**
     * <code>addBaseNames</code>
     * <p>The add base names method.</p>
     * @param baseNames {@link java.lang.String} <p>The base names parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void addBaseNames(String... baseNames) {
        this.baseNames.addAll(Arrays.asList(baseNames));
    }

    /**
     * <code>addBaseNames</code>
     * <p>The add base names method.</p>
     * @param baseNames {@link java.util.Collection} <p>The base names parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public void addBaseNames(Collection<String> baseNames) {
        this.baseNames.addAll(baseNames);
    }

    /**
     * <code>removeBaseName</code>
     * <p>The remove base name method.</p>
     * @param baseName {@link java.lang.String} <p>The base name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void removeBaseName(String baseName) {
        this.baseNames.remove(baseName);
    }

    /**
     * <code>removeBaseNames</code>
     * <p>The remove base names method.</p>
     * @param baseNames {@link java.lang.String} <p>The base names parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void removeBaseNames(String... baseNames) {
        Arrays.asList(baseNames).forEach(this.baseNames::remove);
    }

    /**
     * <code>removeBaseNames</code>
     * <p>The remove base names method.</p>
     * @param baseNames {@link java.util.Collection} <p>The base names parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public void removeBaseNames(Collection<String> baseNames) {
        this.baseNames.removeAll(baseNames);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param baseNames {@link java.lang.String} <p>The base names parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18nResources} <p>The of return object is <code>RestI18nResources</code> type.</p>
     * @see java.lang.String
     */
    public static RestI18nResources of(String... baseNames) {
        return RestI18nResources.builder().baseNames(new HashSet<>(Arrays.asList(baseNames))).build();
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param baseNames {@link java.util.Collection} <p>The base names parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18nResources} <p>The of return object is <code>RestI18nResources</code> type.</p>
     * @see java.util.Collection
     */
    public static RestI18nResources of(Collection<String> baseNames) {
        return RestI18nResources.builder().baseNames(new HashSet<>(baseNames)).build();
    }
}
