package io.github.nichetoolkit.rest.controller.pack;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ParsingNestedPack implements Serializable {
    private String id;
    private String name;
    private List<String> nameList;
    private String[] nameArray;
    private Integer key;
    private List<Integer> keyList;
    private Integer[] keyArray;
    private Long value;
    private List<Long> valueList;
    private Long[] valueArray;
}
