package io.github.nichetoolkit.rest.controller.pack;

import io.github.nichetoolkit.rest.parsing.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ParsingTestPack implements Serializable {
    @JsonParsingIgnored
    private String id;
    @JsonParsingField("none")
    private String test;
    private String name;
    private List<String> nameList;
    private String[] nameArray;
    @JsonParsingNestedField
    @JsonParsingNestedIgnoredFields("id")
    @JsonParsingNestedMultiFields({"keyList","keyArray"})
    private ParsingNestedPack nested;
}
