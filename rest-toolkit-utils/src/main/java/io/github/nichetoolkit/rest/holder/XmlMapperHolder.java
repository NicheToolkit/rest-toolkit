package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

/**
 * <code>XmlMapperHolder</code>
 * <p>The xml mapper holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class XmlMapperHolder {

    /**
     * <code>XML_MAPPER</code>
     * {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The constant <code>XML_MAPPER</code> field.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     */
    private static XmlMapper XML_MAPPER;

    static {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XmlFactory xmlFactory = new XmlFactory(xmlInputFactory, xmlOutputFactory);
        XML_MAPPER = new XmlMapper(xmlFactory);
    }

    public XmlMapperHolder() {
    }
    /**
     * <code>XmlMapperHolder</code>
     * <p>Instantiates a new xml mapper holder.</p>
     * @param xmlMapper {@link com.fasterxml.jackson.dataformat.xml.XmlMapper} <p>The xml mapper parameter is <code>XmlMapper</code> type.</p>
     * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
     * @see org.springframework.lang.Nullable
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
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
}
