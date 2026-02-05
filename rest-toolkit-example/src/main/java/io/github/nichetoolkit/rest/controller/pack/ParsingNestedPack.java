package io.github.nichetoolkit.rest.controller.pack;

import io.github.nichetoolkit.rest.parsing.JsonParsingIgnored;
import io.github.nichetoolkit.rest.parsing.JsonParsingNestedField;
import io.github.nichetoolkit.rest.parsing.JsonParsingNestedIgnoredFields;
import io.github.nichetoolkit.rest.parsing.JsonParsingNestedMultiFields;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ParsingNestedPack implements Serializable {
    private String id;
    private Integer key;
    private List<Integer> keyList;
    private Integer[] keyArray;
    @JsonParsingNestedField
    @JsonParsingNestedIgnoredFields("id")
    @JsonParsingNestedMultiFields({"valueList","valueArray"})
    private ParsingInnerPack inner;
}
