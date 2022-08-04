package io.github.nichetoolkit.rest.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.JsonUtils;

import java.util.Optional;

/**
 * <p>RemarkType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum LogType implements RestField {
    NONE(0,null,null),
    QUERY(1,"query","查询"),
    QUERY_ID(2,"queryId","单个查询"),
    QUERY_ALL(3,"queryAll","批量查询"),
    QUERY_FILTER(4,"queryFilter","过滤器查询"),
    QUERY_CENSUS(5,"queryCensus","统计查询"),
    UPLOAD(6,"upload","上传"),
    DOWNLOAD(7,"download","下载"),
    CREATE(8,"create","创建"),
    UPDATE(9,"update","更新"),
    SAVE(10,"save","保存"),
    SAVE_ALL(11,"saveAll","批量保存"),
    DELETE(12,"delete","删除"),
    DELETE_ID(13,"deleteId","单个删除"),
    DELETE_ALL(14,"deleteAll","批量删除"),
    DELETE_FILTER(15,"deleteFilter","过滤器删除"),
    EXTEND(99,"extend","扩展操作"),
    ;

    private final Integer key;
    private final String value;
    private final String field;

    LogType(Integer key, String value, String field) {
        this.key = key;
        this.value = value;
        this.field = field;
    }

    @JsonValue
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getField() {
        return this.field;
    }

    @JsonCreator
    public static LogType parseKey(Integer key) {
        LogType sortTypeEnum = RestValue.parseKey(LogType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    public static LogType parseValue(String value) {
        LogType sortTypeEnum = RestValue.parseValue(LogType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    public static LogType parseField(String field) {
        LogType sortTypeEnum = RestField.parseField(LogType.class, field);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }
}
