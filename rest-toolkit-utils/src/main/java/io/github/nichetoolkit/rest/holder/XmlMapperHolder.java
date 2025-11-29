package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.dataformat.xml.XmlMapper;


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
     * {@link tools.jackson.dataformat.xml.XmlMapper} <p>The constant <code>XML_MAPPER</code> field.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    private static XmlMapper XML_MAPPER;

    /**
     * <code>JACKXML_MAPPER</code>
     * {@link tools.jackson.dataformat.xml.XmlMapper} <p>The constant <code>JACKXML_MAPPER</code> field.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    private static final XmlMapper JACKXML_MAPPER;

    static {
        JACKXML_MAPPER = ofJackxmlMapper();
        XML_MAPPER = JACKXML_MAPPER;
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
     * @param xmlMapper {@link tools.jackson.dataformat.xml.XmlMapper} <p>The xml mapper parameter is <code>XmlMapper</code> type.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    public XmlMapperHolder(XmlMapper xmlMapper) {
        XML_MAPPER = xmlMapper;
        log.debug("The xml mapper holder has be initiated");
    }

    /**
     * <code>xmlMapper</code>
     * <p>The xml mapper method.</p>
     * @return {@link tools.jackson.dataformat.xml.XmlMapper} <p>The xml mapper return object is <code>XmlMapper</code> type.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper xmlMapper() {
        return XML_MAPPER;
    }

    /**
     * <code>jackxmlMapper</code>
     * <p>The jackxml mapper method.</p>
     * @return {@link tools.jackson.dataformat.xml.XmlMapper} <p>The jackxml mapper return object is <code>XmlMapper</code> type.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper jackxmlMapper() {
        return JACKXML_MAPPER;
    }

    /**
     * <code>ofJackxmlMapper</code>
     * <p>The of jackxml mapper method.</p>
     * @return {@link tools.jackson.dataformat.xml.XmlMapper} <p>The of jackxml mapper return object is <code>XmlMapper</code> type.</p>
     * @see tools.jackson.dataformat.xml.XmlMapper
     */
    public static XmlMapper ofJackxmlMapper() {
        XmlMapper.Builder builder = XmlMapper.builder();
        builder.changeDefaultPropertyInclusion((value) -> {
            value.withValueInclusion(JsonInclude.Include.NON_NULL);
            return value.withContentInclusion(JsonInclude.Include.NON_NULL);
        });
        return builder.build();
    }
}
