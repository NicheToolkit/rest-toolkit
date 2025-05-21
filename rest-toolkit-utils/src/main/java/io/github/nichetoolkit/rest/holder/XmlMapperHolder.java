package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

@Slf4j
public class XmlMapperHolder {

    private static XmlMapper XML_MAPPER;

    static {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XmlFactory xmlFactory = new XmlFactory(xmlInputFactory, xmlOutputFactory);
        XML_MAPPER = new XmlMapper(xmlFactory);
    }

    @Autowired
    public XmlMapperHolder(@Nullable XmlMapper xmlMapper) {
        if (GeneralUtils.isNotEmpty(xmlMapper)) {
            XML_MAPPER = xmlMapper;
            log.debug("The xml mapper holder has be initiated");
        }
    }

    public static XmlMapper xmlMapper() {
        return XML_MAPPER;
    }
}
