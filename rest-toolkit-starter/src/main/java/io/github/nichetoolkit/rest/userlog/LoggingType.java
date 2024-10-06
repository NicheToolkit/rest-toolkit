package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.pack.EnumPack;
import io.github.nichetoolkit.rest.util.JsonUtils;

import java.util.Optional;

/**
 * <code>LoggingType</code>
 * <p>The type logging type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum LoggingType implements RestValue<String, String> {
    /**
     * <code>TEST</code>
     * <p>The test logging type field.</p>
     */
    TEST("default", "test"),
    /**
     * <code>QUERY</code>
     * <p>The query logging type field.</p>
     */
    QUERY("default", "query"),
    /**
     * <code>QUERY_ID</code>
     * <p>The query id logging type field.</p>
     */
    QUERY_ID("default", "queryId"),
    /**
     * <code>QUERY_ALL</code>
     * <p>The query all logging type field.</p>
     */
    QUERY_ALL("default", "queryAll"),
    /**
     * <code>QUERY_FILTER</code>
     * <p>The query filter logging type field.</p>
     */
    QUERY_FILTER("default", "queryFilter"),
    /**
     * <code>QUERY_CENSUS</code>
     * <p>The query census logging type field.</p>
     */
    QUERY_CENSUS("default", "queryCensus"),
    /**
     * <code>UPLOAD</code>
     * <p>The upload logging type field.</p>
     */
    UPLOAD("default", "upload"),
    /**
     * <code>DOWNLOAD</code>
     * <p>The download logging type field.</p>
     */
    DOWNLOAD("default", "download"),
    /**
     * <code>CREATE</code>
     * <p>The create logging type field.</p>
     */
    CREATE("default", "create"),
    /**
     * <code>UPDATE</code>
     * <p>The update logging type field.</p>
     */
    UPDATE("default", "update"),
    /**
     * <code>SAVE</code>
     * <p>The save logging type field.</p>
     */
    SAVE("default", "save"),
    /**
     * <code>SAVE_ALL</code>
     * <p>The save all logging type field.</p>
     */
    SAVE_ALL("default", "saveAll"),
    /**
     * <code>DELETE</code>
     * <p>The delete logging type field.</p>
     */
    DELETE("default", "delete"),
    /**
     * <code>DELETE_ID</code>
     * <p>The delete id logging type field.</p>
     */
    DELETE_ID("default", "deleteId"),
    /**
     * <code>DELETE_ALL</code>
     * <p>The delete all logging type field.</p>
     */
    DELETE_ALL("default", "deleteAll"),
    /**
     * <code>DELETE_FILTER</code>
     * <p>The delete filter logging type field.</p>
     */
    DELETE_FILTER("default", "deleteFilter"),
    /**
     * <code>STATUS</code>
     * <p>The status logging type field.</p>
     */
    STATUS("default", "status"),
    /**
     * <code>STATUS_SWITCH</code>
     * <p>The status switch logging type field.</p>
     */
    STATUS_SWITCH("default", "statusSwitch"),
    /**
     * <code>VALID_SWITCH</code>
     * <p>The valid switch logging type field.</p>
     */
    VALID_SWITCH("default", "validSwitch"),
    /**
     * <code>LOCKED_SWITCH</code>
     * <p>The locked switch logging type field.</p>
     */
    LOCKED_SWITCH("default", "lockedSwitch"),
    /**
     * <code>USER</code>
     * <p>The user logging type field.</p>
     */
    USER("default", "user"),
    /**
     * <code>USER_LOGON</code>
     * <p>The user logon logging type field.</p>
     */
    USER_LOGON("default", "userLogon"),
    /**
     * <code>USER_LOGIN</code>
     * <p>The user login logging type field.</p>
     */
    USER_LOGIN("default", "userLogin"),
    /**
     * <code>USER_LOGOUT</code>
     * <p>The user logout logging type field.</p>
     */
    USER_LOGOUT("default", "userLogout"),
    /**
     * <code>USER_REVISE</code>
     * <p>The user revise logging type field.</p>
     */
    USER_REVISE("default", "userRevise"),
    /**
     * <code>USER_RETRIEVE</code>
     * <p>The user retrieve logging type field.</p>
     */
    USER_RETRIEVE("default", "userRetrieve"),
    /**
     * <code>EXTEND</code>
     * <p>The extend logging type field.</p>
     */
    EXTEND("default", "extend"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;

    /**
     * <code>LoggingType</code>
     * <p>Instantiates a new logging type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    LoggingType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @JsonValue
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The key return object is <code>LoggingType</code> type.</p>
     * @see java.lang.String
     */
    public static LoggingType parseKey(String key) {
        LoggingType sortTypeEnum = RestValue.parseKey(LoggingType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(LoggingType.TEST);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The value return object is <code>LoggingType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static LoggingType parseValue(String value) {
        LoggingType sortTypeEnum = RestValue.parseValue(LoggingType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(LoggingType.TEST);
    }


    /**
     * <code>isSave</code>
     * <p>The save method.</p>
     * @return boolean <p>The save return object is <code>boolean</code> type.</p>
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
     * <p>The query method.</p>
     * @return boolean <p>The query return object is <code>boolean</code> type.</p>
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
     * <p>The delete method.</p>
     * @return boolean <p>The delete return object is <code>boolean</code> type.</p>
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
        return JsonUtils.parseJson(EnumPack.fromValue(this));
    }
}
