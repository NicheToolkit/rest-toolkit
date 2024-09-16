package io.github.nichetoolkit.rest;

import lombok.Getter;

/**
 * <code>RestErrorStatus</code>
 * <p>The type rest error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum RestErrorStatus implements RestStatus {

    /**
     * <code>SUCCESS</code>
     * <p>the <code>SUCCESS</code> field.</p>
     */
    SUCCESS(200, "success"),
    /**
     * <code>MISTAKE</code>
     * <p>the <code>MISTAKE</code> field.</p>
     */
    MISTAKE(400, "mistake"),
    /**
     * <code>FAILED</code>
     * <p>the <code>FAILED</code> field.</p>
     * @see java.lang.Deprecated
     * @deprecated <p>the <code>FAILED</code> field has be deprecated.</p>
     */
    @Deprecated
    FAILED(400, "failed"),

    /**
     * <code>HTTP_CONFIG_ERROR</code>
     * <p>the http request has encountered a configuration related error.</p>
     */
    HTTP_CONFIG_ERROR(8000, "the http request has encountered a configuration related error"),
    /**
     * <code>HTTP_ERROR</code>
     * <p>the http request has encountered an error.</p>
     */
    HTTP_ERROR(8001, "the http request has encountered an error"),
    /**
     * <code>HTTP_RESPONSE_NULL</code>
     * <p>the response of the http request is empty.</p>
     */
    HTTP_RESPONSE_NULL(8011, "the response of the http request is empty"),
    /**
     * <code>HTTP_RESULT_FAILED</code>
     * <p>the result of the http request is failed.</p>
     */
    HTTP_RESULT_FAILED(8012, "the result of the http request is failed"),
    /**
     * <code>HTTP_RESULT_DATA_NULL</code>
     * <p>the result data of the http request is empty.</p>
     */
    HTTP_RESULT_DATA_NULL(8013, "the result data of the http request is empty"),

    /**
     * <code>UNKNOWN_ERROR</code>
     * <p>it has encountered an unknown error.</p>
     */
    UNKNOWN_ERROR(8888, "it has encountered an unknown error"),

    /**
     * <code>BEAN_LACK_ERROR</code>
     * <p>it has encountered a bean lack error.</p>
     */
    BEAN_LACK_ERROR(9101, "it has encountered a bean lack error"),
    /**
     * <code>INTERFACE_LACK_ERROR</code>
     * <p>it has encountered a interface lack error.</p>
     */
    INTERFACE_LACK_ERROR(9111, "it has encountered an interface lack error"),
    /**
     * <code>METHOD_LACK_ERROR</code>
     * <p>it has encountered a method lack error.</p>
     */
    METHOD_LACK_ERROR(9112, "it has encountered a method lack error"),
    /**
     * <code>CONFIGURE_LACK_ERROR</code>
     * <p>it has encountered a configuration lack error.</p>
     */
    CONFIGURE_LACK_ERROR(9113, "it has encountered a configuration lack error"),
    /**
     * <code>ACCESSIBLE_LACK_ERROR</code>
     * <p>it has encountered a field accessible lack error.</p>
     */
    ACCESSIBLE_LACK_ERROR(9114, "it has encountered a field accessible lack error"),
    /**
     * <code>INSTANCE_LACK_ERROR</code>
     * <p>it has encountered a instance lack error.</p>
     */
    INSTANCE_LACK_ERROR(9115, "it has encountered a instance lack error"),
    /**
     * <code>CLASS_LACK_ERROR</code>
     * <p>it has encountered a class lack error.</p>
     */
    CLASS_LACK_ERROR(9116, "it has encountered a class lack error"),
    /**
     * <code>FIELD_LACK_ERROR</code>
     * <p>it has encountered a field lack error.</p>
     */
    FIELD_LACK_ERROR(9117, "it has encountered a field lack error"),


    /**
     * <code>UNSUPPORTED</code>
     * <p>the <code>UNSUPPORTED</code> field.</p>
     */
    UNSUPPORTED(9999, "unsupported"),
    /**
     * <code>TIMEOUT</code>
     * <p>the <code>TIMEOUT</code> field.</p>
     */
    TIMEOUT(10000, "timeout"),
    /**
     * <code>PARAM_ERROR</code>
     * <p>it has encountered a param related error.</p>
     */
    PARAM_ERROR(10010, "it has encountered a param related error"),
    /**
     * <code>PARAM_INVALID</code>
     * <p>it has encountered a param invalid error.</p>
     */
    PARAM_INVALID(10012, "it has encountered a param invalid error"),
    /**
     * <code>PARAM_MISSING</code>
     * <p>it has encountered a param missing error.</p>
     */
    PARAM_MISSING(10011, "it has encountered a param missing error"),
    /**
     * <code>PARSE_ERROR</code>
     * <p>the <code>PARSE_ERROR</code> field.</p>
     * it has encountered a parse related error.
     */
    PARSE_ERROR(10100, "it has encountered a parse related error"),
    /**
     * <code>JSON_PARSE_ERROR</code>
     * <p>it has encountered a json parse related error.</p>
     */
    JSON_PARSE_ERROR(10110, "it has encountered a json parse related error"),
    /**
     * <code>JSON_PARSE_BEAN</code>
     * <p>it has encountered a json parse bean error.</p>
     */
    JSON_PARSE_BEAN(10111, "it has encountered a json parse bean error"),
    /**
     * <code>JSON_PARSE_CONVERT</code>
     * <p>it has encountered a json parse convert type error.</p>
     */
    JSON_PARSE_CONVERT(10112, "it has encountered a json parse convert type error"),
    /**
     * <code>JSON_PARSE_LIST</code>
     * <p>it has encountered a json parse <code>List</code> type error.</p>
     */
    JSON_PARSE_LIST(10113, "it has encountered a json parse list type error"),
    /**
     * <code>JSON_PARSE_SET</code>
     * <p>it has encountered a json parse <code>Set</code> type error.</p>
     */
    JSON_PARSE_SET(10114, "it has encountered a json parse set type error"),
    /**
     * <code>JSON_PARSE_MAP</code>
     * <p>it has encountered a json parse <code>Map</code> type error.</p>
     */
    JSON_PARSE_MAP(10115, "it has encountered a json parse map type error"),
    /**
     * <code>JSON_PARSE_RESULT</code>
     * <p>it has encountered a json parse <code>Result</code> type error.</p>
     */
    JSON_PARSE_RESULT(10116, "it has encountered a json parse result type error"),
    /**
     * <code>JSON_DESERIALIZE_ERROR</code>
     * <p>it has encountered a json deserialize related error.</p>
     */
    JSON_DESERIALIZE_ERROR(10120, "it has encountered a json deserialize related error"),

    /**
     * <code>RESOURCE_ERROR</code>
     * <p>it has encountered a resource related error.</p>
     */
    RESOURCE_ERROR(10200, "it has encountered a resource related error"),
    /**
     * <code>RESOURCE_NOT_FOUND</code>
     * <p>the resource required is no found.</p>
     */
    RESOURCE_NOT_FOUND(10201, "the resource required is no found"),
    /**
     * <code>RESOURCE_UNAVAILABLE</code>
     * <p>the resource required is unavailable.</p>
     */
    RESOURCE_UNAVAILABLE(10202, "the resource required is unavailable"),

    /**
     * <code>FILE_ERROR</code>
     * <p>it has encountered a file related error.</p>
     */
    FILE_ERROR(10210, "it has encountered a file related error"),
    /**
     * <code>FILE_NOT_EXIST</code>
     * <p>the file required is no exist.</p>
     */
    FILE_NOT_EXIST(10211, "the file required is no exist"),
    /**
     * <code>FILE_IS_EXIST</code>
     * <p>the file required is exist.</p>
     */
    FILE_IS_EXIST(10212, "the file required is exist"),
    /**
     * <code>FILE_UNAVAILABLE</code>
     * <p>the file required is unavailable.</p>
     */
    FILE_UNAVAILABLE(10213, "the file required is unavailable"),

    /**
     * <code>FILE_CREATE_ERROR</code>
     * <p>it has encountered a file created error.</p>
     */
    FILE_CREATE_ERROR(10214, "it has encountered a file created error"),
    /**
     * <code>FILE_COPY_ERROR</code>
     * <p>it has encountered a file copied error.</p>
     */
    FILE_COPY_ERROR(10215, "it has encountered a file copied error"),

    /**
     * <code>AUTH_ERROR</code>
     * <p>it has encountered a auth related error.</p>
     */
    AUTH_ERROR(10300, "it has encountered a auth related error"),
    /**
     * <code>AUTH_DENIED</code>
     * <p>the auth is denied.</p>
     */
    AUTH_DENIED(10302, "the auth is denied"),
    /**
     * <code>AUTH_FORBIDDEN</code>
     * <p>the auth is forbidden.</p>
     */
    AUTH_FORBIDDEN(10301, "the auth is forbidden"),

    /**
     * <code>TOKEN_ERROR</code>
     * <p>it has encountered a token related error.</p>
     */
    TOKEN_ERROR(10310, "it has encountered a token related error"),
    /**
     * <code>TOKEN_FAILED</code>
     * <p>the token verification is failed.</p>
     */
    TOKEN_FAILED(10311, "the token verification is failed"),
    /**
     * <code>TOKEN_SERVICE_INVALID</code>
     * <p>the token verification is invalid.</p>
     */
    TOKEN_INVALID(10312, "the token verification is invalid"),
    /**
     * <code>TOKEN_EXPIRED</code>
     * <p>the token verification is expired.</p>
     */
    TOKEN_EXPIRED(10313, "the token verification is expired"),

    /**
     * <code>LOGIN_ERROR</code>
     * <p>it has encountered a login related error.</p>
     */
    LOGIN_ERROR(10320, "it has encountered a login related error"),
    /**
     * <code>LOGIN_FAILED</code>
     * <p>the login verification is expired.</p>
     */
    LOGIN_FAILED(10321, "the login verification is expired"),
    /**
     * <code>LOGIN_INFO_INVALID</code>
     * <p>the login info verification is invalid.</p>
     */
    LOGIN_INFO_INVALID(10322, "the login info verification is invalid"),
    /**
     * <code>LOGIN_INFO_EXPIRED</code>
     * <p>the login info verification is expired.</p>
     */
    LOGIN_INFO_EXPIRED(10323, "the login info verification is expired"),

    /**
     * <code>SERVICE_ERROR</code>
     * <p>it has encountered an error.</p>
     */
    SERVICE_ERROR(10400, "it has encountered an error"),
    /**
     * <code>SERVICE_UNAVAILABLE</code>
     * <p>it is unavailable.</p>
     */
    SERVICE_UNAVAILABLE(10401, "it is unavailable"),

    /**
     * <code>CONFIG_ERROR</code>
     * <p>it has encountered a configuration related error.</p>
     */
    CONFIG_ERROR(10410, "it has encountered a configuration related error"),
    /**
     * <code>CONFIG_INVALID</code>
     * <p>the configuration is invalid.</p>
     */
    CONFIG_INVALID(10411, "the configuration is invalid"),
    /**
     * <code>CONFIG_UNAVAILABLE</code>
     * <p>the configuration is unavailable.</p>
     */
    CONFIG_UNAVAILABLE(10412, "the configuration is unavailable"),


    /**
     * <code>CONVERT_ERROR</code>
     * <p>it has encountered a convert type related error.</p>
     */
    CONVERT_ERROR(10420, "it has encountered a convert type related error"),
    /**
     * <code>CONVERT_TYPE_UNSUPPORTED</code>
     * <p>the Convert type unsupported rest error status field.</p>
     */
    CONVERT_TYPE_UNSUPPORTED(10421, "the convert type is unsupported"),
    /**
     * <code>CONVERT_TYPE_UNKNOWN</code>
     * <p>the convert type is unknown.</p>
     */
    CONVERT_TYPE_UNKNOWN(10422, "the convert type is unknown"),

    /**
     * <code>CLASS_ERROR</code>
     * <p>it has encountered a class type related error.</p>
     */
    CLASS_ERROR(10430, "it has encountered a class type related error"),
    /**
     * <code>CLASS_TYPE_UNSUPPORTED</code>
     * <p>the class type is unsupported.</p>
     */
    CLASS_TYPE_UNSUPPORTED(10431, "the class type is unsupported"),
    /**
     * <code>CLASS_TYPE_UNKNOWN</code>
     * <p>the class type is unknown.</p>
     */
    CLASS_TYPE_UNKNOWN(10432, "the class type is unknown"),
    /**
     * <code>CLASS_TYPE_UNRENEW</code>
     * <p>the class type is unrenew.</p>
     */
    CLASS_TYPE_UNRENEW(10433, "the class type is unrenew"),

    /**
     * <code>FIELD_ERROR</code>
     * <p>it has encountered a field related error.</p>
     */
    FIELD_ERROR(10500, "it has encountered a field related error"),
    /**
     * <code>FIELD_IS_NULL</code>
     * <p>the field is empty.</p>
     */
    FIELD_IS_NULL(10501, "the field is empty"),
    /**
     * <code>FIELD_REPEATED</code>
     * <p>the field is repeated.</p>
     */
    FIELD_REPEATED(10502, "the field is repeated"),
    /**
     * <code>FIELD_NOT_EXIST</code>
     * <p>the field is not exist.</p>
     */
    FIELD_NOT_EXIST(10503, "the field is not exist"),
    /**
     * <code>FIELD_IS_EXIST</code>
     * <p>the field is exist already.</p>
     */
    FIELD_IS_EXIST(10504, "the field is exist already"),
    /**
     * <code>NAME_ERROR</code>
     * <p>it has encountered a name related error.</p>
     */
    NAME_ERROR(10510, "it has encountered a name related error"),
    /**
     * <code>NAME_IS_NULL</code>
     * <p>the name is empty.</p>
     */
    NAME_IS_NULL(10511, "the name is empty"),
    /**
     * <code>NAME_REPEATED</code>
     * <p>the name is repeated.</p>
     */
    NAME_REPEATED(10512, "the name is repeated"),
    /**
     * <code>IDENTITY_ERROR</code>
     * <p>it has encountered a identity related error.</p>
     */
    IDENTITY_ERROR(10520, "it has encountered a identity related error"),
    /**
     * <code>IDENTITY_IS_NULL</code>
     * <p>the identity is empty.</p>
     */
    IDENTITY_IS_NULL(10521, "the identity is empty"),
    /**
     * <code>IDENTITY_REPEATED</code>
     * <p>the identity is repeated.</p>
     */
    IDENTITY_REPEATED(10522, "the identity is repeated"),
    /**
     * <code>DATA_ERROR</code>
     * <p>it has encountered a data related error.</p>
     */
    DATA_ERROR(10600, "it has encountered a data related error"),
    /**
     * <code>DATA_CREATE_FAILED</code>
     * <p>the data create failed.</p>
     */
    DATA_CREATE_FAILED(10601, "the data create failed"),
    /**
     * <code>DATA_UPDATE_FAILED</code>
     * <p>the data update failed.</p>
     */
    DATA_UPDATE_FAILED(10602, "the data update failed"),
    /**
     * <code>DATA_SAVE_FAILED</code>
     * <p>the data save failed.</p>
     */
    DATA_SAVE_FAILED(10603, "the data save failed"),
    /**
     * <code>DATA_DELETE_FAILED</code>
     * <p>the data delete failed.</p>
     */
    DATA_DELETE_FAILED(10604, "the data delete failed"),
    /**
     * <code>DATA_QUERY_FAILED</code>
     * <p>the data query failed.</p>
     */
    DATA_QUERY_FAILED(10605, "the data query failed"),
    /**
     * <code>DATA_TRANSFORM_FAILED</code>
     * <p>the data transform failed.</p>
     */
    DATA_TRANSFORM_FAILED(10606, "the data transform failed"),

    /**
     * <code>DATA_ALL_ERROR</code>
     * <p>it has encountered a data batch handle related error.</p>
     */
    DATA_ALL_ERROR(10610, "it has encountered a batch data related error"),
    /**
     * <code>DATA_INSERT_ALL_FAILED</code>
     * <p>the batch data insert failed.</p>
     */
    DATA_INSERT_ALL_FAILED(10611, "the batch data insert failed"),
    /**
     * <code>DATA_UPDATE_ALL_FAILED</code>
     * <p>the batch data update failed.</p>
     */
    DATA_UPDATE_ALL_FAILED(10612, "the batch data update failed"),
    /**
     * <code>DATA_SAVE_ALL_FAILED</code>
     * <p>the batch data save failed.</p>
     */
    DATA_SAVE_ALL_FAILED(10613, "the batch data save failed"),
    /**
     * <code>DATA_DELETE_ALL_FAILED</code>
     * <p>the batch data delete failed.</p>
     */
    DATA_DELETE_ALL_FAILED(10614, "the batch data delete failed"),
    /**
     * <code>DATA_QUERY_ALL_FAILED</code>
     * <p>the batch data query failed.</p>
     */
    DATA_QUERY_ALL_FAILED(10615, "the batch data query failed"),

    /**
     * <code>IO_STREAM_ERROR</code>
     * <p>it has encountered a io-stream related error.</p>
     */
    IO_STREAM_ERROR(10620, "it has encountered a io-stream related error"),
    /**
     * <code>IO_STREAM_READ_ERROR</code>
     * <p>it has encountered a io-stream read error.</p>
     */
    IO_STREAM_READ_ERROR(10621, "it has encountered a io-stream read error"),
    /**
     * <code>IO_STREAM_WRITE_ERROR</code>
     * <p>it has encountered a io-stream write error.</p>
     */
    IO_STREAM_WRITE_ERROR(10622, "it has encountered a io-stream write error"),
    /**
     * <code>IO_STREAM_TRANSFER_ERROR</code>
     * <p>it has encountered a io-stream transfer error.</p>
     */
    IO_STREAM_TRANSFER_ERROR(10623, "it has encountered a io-stream transfer error"),

    /**
     * <code>XML_ERROR</code>
     * <p>it has encountered a xml file related error.</p>
     */
    XML_ERROR(10630, "it has encountered a xml file related error"),
    /**
     * <code>XML_READ_ERROR</code>
     * <p>it has encountered a xml file read error.</p>
     */
    XML_READ_ERROR(10631, "it has encountered a xml file read error"),
    /**
     * <code>XML_WRITE_ERROR</code>
     * <p>it has encountered a xml file write error.</p>
     */
    XML_WRITE_ERROR(10632, "it has encountered a xml file write error"),
    /**
     * <code>XML_MARSHAL_ERROR</code>
     * <p>it has encountered a xml file marshal error.</p>
     */
    XML_MARSHAL_ERROR(10633, "it has encountered a xml file marshal error"),

    /**
     * <code>ZIP_ERROR</code>
     * <p>it has encountered a zip file related error.</p>
     */
    ZIP_ERROR(10640, "it has encountered a zip file related error"),
    /**
     * <code>ZIP_READ_ERROR</code>
     * <p>it has encountered a zip file read error.</p>
     */
    ZIP_READ_ERROR(10641, "it has encountered a zip file read error"),
    /**
     * <code>ZIP_WRITE_ERROR</code>
     * <p>it has encountered a zip file write error.</p>
     */
    ZIP_WRITE_ERROR(10642, "it has encountered a zip file write error"),
    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>the <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>the <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>RestErrorStatus</code>
     * Instantiates a new rest error status.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    RestErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
