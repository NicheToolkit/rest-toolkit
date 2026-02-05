package io.github.nichetoolkit.rest.controller.pack;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ParsingInnerPack implements Serializable {
    private String id;
    private Long value;
    private List<Long> valueList;
    private Long[] valueArray;
}
