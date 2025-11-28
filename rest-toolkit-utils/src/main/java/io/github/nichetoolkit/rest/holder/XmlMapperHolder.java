package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;


/**
 * <code>XmlMapperHolder</code>
 * <p>The xml mapper holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public class XmlMapperHolder {

    /**
     * <code>XML_MAPPER</code>
     * {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The constant <code>XML_MAPPER</code> field.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    private static XmlMapper XML_MAPPER;

    /**
     * <code>PURITY_MAPPER</code>
     * {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The constant <code>PURITY_MAPPER</code> field.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    private static final XmlMapper PURITY_MAPPER;

    static {
        PURITY_MAPPER = ofPurityMapper();
        XML_MAPPER = PURITY_MAPPER;
    }

    /**
     * <code>XmlMapperHolder</code>
     * <p>Instantiates a new xml mapper holder.</p>
     */
    public XmlMapperHolder() {
    }

    /**
     * <code>XmlMapperHolder</code>
     * <p>Instantiates a new xml mapper holder.</p>
     * @param xmlMapper {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The xml mapper parameter is <code>XmlMapper</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    public XmlMapperHolder(XmlMapper xmlMapper) {
        XML_MAPPER = xmlMapper;
        log.debug("The xml mapper holder has be initiated");
    }

    /**
     * <code>xmlMapper</code>
     * <p>The xml mapper method.</p>
     * @return {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The xml mapper return object is <code>XmlMapper</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper xmlMapper() {
        return XML_MAPPER;
    }

    /**
     * <code>purityMapper</code>
     * <p>The purity mapper method.</p>
     * @return {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The purity mapper return object is <code>XmlMapper</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper purityMapper() {
        return PURITY_MAPPER;
    }

    /**
     * <code>ofPurityMapper</code>
     * <p>The of purity mapper method.</p>
     * @return {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The of purity mapper return object is <code>XmlMapper</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper ofPurityMapper() {
        return new XmlMapper(new XmlFactory());
    }
}
