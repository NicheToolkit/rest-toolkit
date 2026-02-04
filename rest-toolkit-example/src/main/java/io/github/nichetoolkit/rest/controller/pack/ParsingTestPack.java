package io.github.nichetoolkit.rest.controller.pack;

import io.github.nichetoolkit.rest.parsing.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParsingTestPack implements Serializable {
    @JsonParsingIgnored
    private String id;
    @JsonParsingField("none")
    private String test;
    @JsonParsingNestedField
    @JsonParsingNestedIgnoredFields("id")
    @JsonParsingNestedMultiFields({"nameList","nameArray","keyList","keyArray","valueList","valueArray"})
    private ParsingNestedPack nested;
}
