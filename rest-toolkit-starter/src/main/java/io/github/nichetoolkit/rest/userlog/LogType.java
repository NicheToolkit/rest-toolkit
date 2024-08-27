package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestEnum;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.JsonUtils;

import java.util.Optional;

/**
 * <code>LogType</code>
 * <p>The type log type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @since Jdk1.8
 */
public enum LogType implements RestField {
    /**
     * <code>NONE</code>
     * <p>the None log type field.</p>
     */
    NONE(0, null, null),
    /**
     * <code>QUERY</code>
     * <p>the Query log type field.</p>
     */
    QUERY(1, "query", "查询"),
    /**
     * <code>QUERY_ID</code>
     * <p>the Query id log type field.</p>
     */
    QUERY_ID(2, "queryId", "单个查询"),
    /**
     * <code>QUERY_ALL</code>
     * <p>the Query all log type field.</p>
     */
    QUERY_ALL(3, "queryAll", "批量查询"),
    /**
     * <code>QUERY_FILTER</code>
     * <p>the Query filter log type field.</p>
     */
    QUERY_FILTER(4, "queryFilter", "过滤器查询"),
    /**
     * <code>QUERY_CENSUS</code>
     * <p>the Query census log type field.</p>
     */
    QUERY_CENSUS(5, "queryCensus", "统计查询"),
    /**
     * <code>UPLOAD</code>
     * <p>the Upload log type field.</p>
     */
    UPLOAD(6, "upload", "上传"),
    /**
     * <code>DOWNLOAD</code>
     * <p>the Download log type field.</p>
     */
    DOWNLOAD(7, "download", "下载"),
    /**
     * <code>CREATE</code>
     * <p>the Create log type field.</p>
     */
    CREATE(8, "create", "创建"),
    /**
     * <code>UPDATE</code>
     * <p>the Update log type field.</p>
     */
    UPDATE(9, "update", "更新"),
    /**
     * <code>SAVE</code>
     * <p>the Save log type field.</p>
     */
    SAVE(10, "save", "保存"),
    /**
     * <code>SAVE_ALL</code>
     * <p>the Save all log type field.</p>
     */
    SAVE_ALL(11, "saveAll", "批量保存"),
    /**
     * <code>DELETE</code>
     * <p>the Delete log type field.</p>
     */
    DELETE(12, "delete", "删除"),
    /**
     * <code>DELETE_ID</code>
     * <p>the Delete id log type field.</p>
     */
    DELETE_ID(13, "deleteId", "单个删除"),
    /**
     * <code>DELETE_ALL</code>
     * <p>the Delete all log type field.</p>
     */
    DELETE_ALL(14, "deleteAll", "批量删除"),
    /**
     * <code>DELETE_FILTER</code>
     * <p>the Delete filter log type field.</p>
     */
    DELETE_FILTER(15, "deleteFilter", "过滤器删除"),
    /**
     * <code>STATUS</code>
     * <p>the Status log type field.</p>
     */
    STATUS(16, "status", "状态修改"),
    /**
     * <code>STATUS_SWITCH</code>
     * <p>the Status switch log type field.</p>
     */
    STATUS_SWITCH(17, "statusSwitch", "状态切换"),
    /**
     * <code>VALID_SWITCH</code>
     * <p>the Valid switch log type field.</p>
     */
    VALID_SWITCH(18, "validSwitch", "有效状态切换"),
    /**
     * <code>LOCKED_SWITCH</code>
     * <p>the Locked switch log type field.</p>
     */
    LOCKED_SWITCH(19, "lockedSwitch", "锁定状态切换"),
    /**
     * <code>USER</code>
     * <p>the User log type field.</p>
     */
    USER(20, "user", "用户操作"),
    /**
     * <code>USER_LOGON</code>
     * <p>the User logon log type field.</p>
     */
    USER_LOGON(21, "userLogon", "注册"),
    /**
     * <code>USER_LOGIN</code>
     * <p>the User login log type field.</p>
     */
    USER_LOGIN(22, "userLogin", "登录"),
    /**
     * <code>USER_LOGOUT</code>
     * <p>the User logout log type field.</p>
     */
    USER_LOGOUT(23, "userLogout", "注销"),
    /**
     * <code>USER_REVISE</code>
     * <p>the User revise log type field.</p>
     */
    USER_REVISE(24, "userRevise", "修改密码"),
    /**
     * <code>USER_RETRIEVE</code>
     * <p>the User retrieve log type field.</p>
     */
    USER_RETRIEVE(25, "userRetrieve", "找回密码"),
    /**
     * <code>EXTEND</code>
     * <p>the Extend log type field.</p>
     */
    EXTEND(99, "extend", "扩展操作"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>the <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>the <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;
    /**
     * <code>field</code>
     * {@link java.lang.String} <p>the <code>field</code> field.</p>
     * @see java.lang.String
     */
    private final String field;

    /**
     * <code>LogType</code>
     * Instantiates a new log type.
     * @param key   {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
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

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the key return object is <code>LogType</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static LogType parseKey(Integer key) {
        LogType sortTypeEnum = RestValue.parseKey(LogType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the value return object is <code>LogType</code> type.</p>
     * @see java.lang.String
     */
    public static LogType parseValue(String value) {
        LogType sortTypeEnum = RestValue.parseValue(LogType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the field return object is <code>LogType</code> type.</p>
     * @see java.lang.String
     */
    public static LogType parseField(String field) {
        LogType sortTypeEnum = RestField.parseField(LogType.class, field);
        return Optional.ofNullable(sortTypeEnum).orElse(LogType.NONE);
    }

    /**
     * <code>isSave</code>
     * <p>the save method.</p>
     * @return {@link boolean} <p>the save return object is <code>boolean</code> type.</p>
     */
    public boolean isSave() {
        switch (this) {
            case CREATE:
            case UPDATE:
            case SAVE:
            case SAVE_ALL:
                return true;
            default:
                return false;
        }
    }

    /**
     * <code>isQuery</code>
     * <p>the query method.</p>
     * @return {@link boolean} <p>the query return object is <code>boolean</code> type.</p>
     */
    public boolean isQuery() {
        switch (this) {
            case QUERY:
            case QUERY_ID:
            case QUERY_ALL:
            case QUERY_FILTER:
            case QUERY_CENSUS:
                return true;
            default:
                return false;
        }
    }

    /**
     * <code>isDelete</code>
     * <p>the delete method.</p>
     * @return {@link boolean} <p>the delete return object is <code>boolean</code> type.</p>
     */
    public boolean isDelete() {
        switch (this) {
            case DELETE:
            case DELETE_ID:
            case DELETE_ALL:
            case DELETE_FILTER:
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(RestEnum.mapBean(this));
    }
}
