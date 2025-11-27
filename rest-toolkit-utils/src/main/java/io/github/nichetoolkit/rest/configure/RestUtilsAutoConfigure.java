package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.nichetoolkit.rest.holder.XmlMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * <code>RestUtilsAutoConfigure</code>
 * <p>The rest utils auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RestCoreAutoConfigure.class)
@ImportAutoConfiguration({RestWorkerAutoConfigure.class})
public class RestUtilsAutoConfigure {

    /**
     * <code>RestUtilsAutoConfigure</code>
     * <p>Instantiates a new rest utils auto configure.</p>
     */
    public RestUtilsAutoConfigure() {
        log.debug("The auto configuration for [rest-utils] initiated");
    }

    /**
     * <code>defaultXmlMapperHolder</code>
     * <p>The default xml mapper holder method.</p>
     * @param xmlMapper {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The xml mapper parameter is <code>XmlMapper</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.holder.XmlMapperHolder} <p>The default xml mapper holder return object is <code>XmlMapperHolder</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     * @see io.github.nichetoolkit.rest.holder.XmlMapperHolder
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see java.lang.SuppressWarnings
     */
    @Bean
    @Primary
    @ConditionalOnBean(XmlMapper.class)
    @ConditionalOnMissingBean(XmlMapperHolder.class)
    @SuppressWarnings("InstantiationOfUtilityClass")
    public XmlMapperHolder defaultXmlMapperHolder(XmlMapper xmlMapper) {
        return new XmlMapperHolder(xmlMapper);
    }


    /**
     * <code>autoXmlMapperHolder</code>
     * <p>The auto xml mapper holder method.</p>
     * @return {@link io.github.nichetoolkit.rest.holder.XmlMapperHolder} <p>The auto xml mapper holder return object is <code>XmlMapperHolder</code> type.</p>
     * @see io.github.nichetoolkit.rest.holder.XmlMapperHolder
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see java.lang.SuppressWarnings
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean({XmlMapperHolder.class, XmlMapper.class})
    @SuppressWarnings("InstantiationOfUtilityClass")
    public XmlMapperHolder autoXmlMapperHolder() {
        return new XmlMapperHolder();
    }
}
