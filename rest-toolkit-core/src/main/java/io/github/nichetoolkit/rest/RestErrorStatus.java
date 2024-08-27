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
     * <p>the Success rest error status field.</p>
     */
    SUCCESS(200, "成功"),
    /**
     * <code>MISTAKE</code>
     * <p>the Mistake rest error status field.</p>
     */
    MISTAKE(400, "失败"),
    /**
     * <code>FAILED</code>
     * <p>the Failed rest error status field.</p>
     * @see java.lang.Deprecated
     * @deprecated <p>the <code>FAILED</code> field has be deprecated.</p>
     */
    @Deprecated
    FAILED(400, "失败"),

    /**
     * <code>HTTP_CONFIG_ERROR</code>
     * <p>the Http config error rest error status field.</p>
     */
    HTTP_CONFIG_ERROR(8000,"Http配置错误"),
    /**
     * <code>HTTP_ERROR</code>
     * <p>the Http error rest error status field.</p>
     */
    HTTP_ERROR(8001,"Http请求错误"),
    /**
     * <code>HTTP_RESPONSE_NULL</code>
     * {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the <code>HTTP_RESPONSE_NULL</code> field.</p>
     */
    HTTP_RESPONSE_NULL(8011,"Http Response为空"),
    /**
     * <code>HTTP_RESULT_FAIL</code>
     * {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the <code>HTTP_RESULT_FAIL</code> field.</p>
     */
    HTTP_RESULT_FAIL(8012,"Http Rest请求失败"),
    /**
     * <code>HTTP_RESULT_DATA_NULL</code>
     * {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the <code>HTTP_RESULT_DATA_NULL</code> field.</p>
     */
    HTTP_RESULT_DATA_NULL(8013,"Http Response Data为空"),

    /**
     * <code>UNKNOWN_ERROR</code>
     * <p>the Unknown error rest error status field.</p>
     */
    UNKNOWN_ERROR(8888,"未知错误"),

    /**
     * <code>BEAN_LACK_ERROR</code>
     * <p>the Bean lack error rest error status field.</p>
     */
    BEAN_LACK_ERROR(9101,"Bean缺失错误"),
    /**
     * <code>INTERFACE_LACK_ERROR</code>
     * <p>the Interface lack error rest error status field.</p>
     */
    INTERFACE_LACK_ERROR(9111,"实现缺失错误"),
    /**
     * <code>METHOD_LACK_ERROR</code>
     * <p>the Method lack error rest error status field.</p>
     */
    METHOD_LACK_ERROR(9112,"方法缺失错误"),
    /**
     * <code>CONFIGURE_LACK_ERROR</code>
     * <p>the Configure lack error rest error status field.</p>
     */
    CONFIGURE_LACK_ERROR(9113,"配置缺失错误"),
    /**
     * <code>ACCESSIBLE_LACK_ERROR</code>
     * <p>the Accessible lack error rest error status field.</p>
     */
    ACCESSIBLE_LACK_ERROR(9114,"属性访问权限缺失错误"),

    /**
     * <code>UNSUPPORTED</code>
     * <p>the Unsupported rest error status field.</p>
     */
    UNSUPPORTED(9999,"不支持"),
    /**
     * <code>TIME_OUT</code>
     * <p>the Time out rest error status field.</p>
     */
    TIME_OUT(10000, "访问超时"),
    /**
     * <code>PARAM_ERROR</code>
     * <p>the Param error rest error status field.</p>
     */
    PARAM_ERROR(10010, "参数错误"),
    /**
     * <code>PARAM_INVALID</code>
     * <p>the Param invalid rest error status field.</p>
     */
    PARAM_INVALID(10012, "参数无效"),
    /**
     * <code>PARAM_MISSING</code>
     * <p>the Param missing rest error status field.</p>
     */
    PARAM_MISSING(10011, "参数缺失"),
    /**
     * <code>PARSE_ERROR</code>
     * <p>the Parse error rest error status field.</p>
     */
    PARSE_ERROR(10100, "解析错误"),
    /**
     * <code>JSON_PARSE_ERROR</code>
     * <p>the Json parse error rest error status field.</p>
     */
    JSON_PARSE_ERROR(10110, "JSON解析错误"),
    /**
     * <code>JSON_PARSE_BEAN</code>
     * <p>the Json parse bean rest error status field.</p>
     */
    JSON_PARSE_BEAN(10111, "JSON解析为Bean出错"),
    /**
     * <code>JSON_PARSE_CONVERT</code>
     * <p>the Json parse convert rest error status field.</p>
     */
    JSON_PARSE_CONVERT(10112, "JSON解析转换类型出错"),
    /**
     * <code>JSON_PARSE_LIST</code>
     * <p>the Json parse list rest error status field.</p>
     */
    JSON_PARSE_LIST(10113, "JSON解析为List出错"),
    /**
     * <code>JSON_PARSE_SET</code>
     * <p>the Json parse set rest error status field.</p>
     */
    JSON_PARSE_SET(10114, "JSON解析为Set出错"),
    /**
     * <code>JSON_PARSE_MAP</code>
     * <p>the Json parse map rest error status field.</p>
     */
    JSON_PARSE_MAP(10115, "JSON解析为Map出错"),
    /**
     * <code>JSON_PARSE_RESULT</code>
     * <p>the Json parse result rest error status field.</p>
     */
    JSON_PARSE_RESULT(10116, "JSON解析为Result出错"),
    /**
     * <code>JSON_DESERIALIZE_ERROR</code>
     * <p>the Json deserialize error rest error status field.</p>
     */
    JSON_DESERIALIZE_ERROR(10120, "JSON反序列化错误"),

    /**
     * <code>RESOURCE_ERROR</code>
     * <p>the Resource error rest error status field.</p>
     */
    RESOURCE_ERROR(10200, "资源错误"),
    /**
     * <code>RESOURCE_NOT_FOUND</code>
     * <p>the Resource not found rest error status field.</p>
     */
    RESOURCE_NOT_FOUND(10201, "资源不存在"),
    /**
     * <code>RESOURCE_UNAVAILABLE</code>
     * <p>the Resource unavailable rest error status field.</p>
     */
    RESOURCE_UNAVAILABLE(10202, "资源不可用"),

    /**
     * <code>FILE_ERROR</code>
     * <p>the File error rest error status field.</p>
     */
    FILE_ERROR(10210, "文件错误"),
    /**
     * <code>FILE_NOT_EXIST</code>
     * <p>the File not exist rest error status field.</p>
     */
    FILE_NOT_EXIST(10211, "文件不存在"),
    /**
     * <code>FILE_IS_EXIST</code>
     * <p>the File is exist rest error status field.</p>
     */
    FILE_IS_EXIST(10212, "文件已经存在"),
    /**
     * <code>FILE_UNAVAILABLE</code>
     * <p>the File unavailable rest error status field.</p>
     */
    FILE_UNAVAILABLE(10213, "文件不可用"),

    /**
     * <code>FILE_CREATE_ERROR</code>
     * <p>the File create error rest error status field.</p>
     */
    FILE_CREATE_ERROR(10214, "文件创建错误"),
    /**
     * <code>FILE_COPY_ERROR</code>
     * <p>the File copy error rest error status field.</p>
     */
    FILE_COPY_ERROR(10215, "文件复制错误"),

    /**
     * <code>AUTH_ERROR</code>
     * <p>the Auth error rest error status field.</p>
     */
    AUTH_ERROR(10300, "权限错误"),
    /**
     * <code>AUTH_DENIED</code>
     * <p>the Auth denied rest error status field.</p>
     */
    AUTH_DENIED(10302, "拒绝访问"),
    /**
     * <code>AUTH_FORBIDDEN</code>
     * <p>the Auth forbidden rest error status field.</p>
     */
    AUTH_FORBIDDEN(10301, "权限不足"),

    /**
     * <code>TOKEN_ERROR</code>
     * <p>the Token error rest error status field.</p>
     */
    TOKEN_ERROR(10310, "认证错误"),
    /**
     * <code>TOKEN_FAILED</code>
     * <p>the Token failed rest error status field.</p>
     */
    TOKEN_FAILED(10311, "认证失败"),
    /**
     * <code>TOKEN_SERVICE_INVALID</code>
     * <p>the Token service invalid rest error status field.</p>
     */
    TOKEN_SERVICE_INVALID(10312, "认证无效"),
    /**
     * <code>TOKEN_EXPIRED</code>
     * <p>the Token expired rest error status field.</p>
     */
    TOKEN_EXPIRED(10313, "认证过期"),

    /**
     * <code>LOGIN_ERROR</code>
     * <p>the Login error rest error status field.</p>
     */
    LOGIN_ERROR(10320, "登录错误"),
    /**
     * <code>LOGIN_FAILED</code>
     * <p>the Login failed rest error status field.</p>
     */
    LOGIN_FAILED(10321, "登录失败"),
    /**
     * <code>LOGIN_INFO_INVALID</code>
     * <p>the Login info invalid rest error status field.</p>
     */
    LOGIN_INFO_INVALID(10322, "登录无效"),
    /**
     * <code>LOGIN_INFO_EXPIRED</code>
     * <p>the Login info expired rest error status field.</p>
     */
    LOGIN_INFO_EXPIRED(10323, "登录过期"),

    /**
     * <code>SERVICE_ERROR</code>
     * <p>the Service error rest error status field.</p>
     */
    SERVICE_ERROR(10400, "服务器错误"),
    /**
     * <code>SERVICE_UNAVAILABLE</code>
     * <p>the Service unavailable rest error status field.</p>
     */
    SERVICE_UNAVAILABLE(10401, "服务不可用"),

    /**
     * <code>CONFIG_ERROR</code>
     * <p>the Config error rest error status field.</p>
     */
    CONFIG_ERROR(10410, "配置错误"),
    /**
     * <code>CONFIG_INVALID</code>
     * <p>the Config invalid rest error status field.</p>
     */
    CONFIG_INVALID(10411, "配置无效"),
    /**
     * <code>CONFIG_UNAVAILABLE</code>
     * <p>the Config unavailable rest error status field.</p>
     */
    CONFIG_UNAVAILABLE(10412, "配置不可用"),


    /**
     * <code>CONVERT_ERROR</code>
     * <p>the Convert error rest error status field.</p>
     */
    CONVERT_ERROR(10420, "转换错误"),
    /**
     * <code>CONVERT_TYPE_UNSUPPORTED</code>
     * <p>the Convert type unsupported rest error status field.</p>
     */
    CONVERT_TYPE_UNSUPPORTED(10421, "转换类型不支持"),
    /**
     * <code>CONVERT_TYPE_UNKNOWN</code>
     * <p>the Convert type unknown rest error status field.</p>
     */
    CONVERT_TYPE_UNKNOWN(10422, "转换类型未知"),

    /**
     * <code>CLASS_ERROR</code>
     * <p>the Class error rest error status field.</p>
     */
    CLASS_ERROR(10430, "类类型错误"),
    /**
     * <code>CLASS_TYPE_UNSUPPORTED</code>
     * <p>the Class type unsupported rest error status field.</p>
     */
    CLASS_TYPE_UNSUPPORTED(10431, "类类型不支持"),
    /**
     * <code>CLASS_TYPE_UNKNOWN</code>
     * <p>the Class type unknown rest error status field.</p>
     */
    CLASS_TYPE_UNKNOWN(10432, "类类型未知"),
    /**
     * <code>CLASS_TYPE_UNRENEW</code>
     * <p>the Class type unrenew rest error status field.</p>
     */
    CLASS_TYPE_UNRENEW(10433, "类类型无法实例化"),

    /**
     * <code>FIELD_ERROR</code>
     * <p>the Field error rest error status field.</p>
     */
    FIELD_ERROR(10500, "字段错误"),
    /**
     * <code>FIELD_IS_NULL</code>
     * <p>the Field is null rest error status field.</p>
     */
    FIELD_IS_NULL(10501, "字段为空"),
    /**
     * <code>FIELD_REPEATED</code>
     * <p>the Field repeated rest error status field.</p>
     */
    FIELD_REPEATED(10502, "字段重复"),
    /**
     * <code>FIELD_NOT_EXIST</code>
     * <p>the Field not exist rest error status field.</p>
     */
    FIELD_NOT_EXIST(10503, "对象不存在"),
    /**
     * <code>FIELD_IS_EXIST</code>
     * <p>the Field is exist rest error status field.</p>
     */
    FIELD_IS_EXIST(10504, "对象已存在"),
    /**
     * <code>NAME_ERROR</code>
     * <p>the Name error rest error status field.</p>
     */
    NAME_ERROR(10510, "名称错误"),
    /**
     * <code>NAME_IS_NULL</code>
     * <p>the Name is null rest error status field.</p>
     */
    NAME_IS_NULL(10511, "名称为空"),
    /**
     * <code>NAME_REPEATED</code>
     * <p>the Name repeated rest error status field.</p>
     */
    NAME_REPEATED(10512, "名称重复"),
    /**
     * <code>IDENTITY_ERROR</code>
     * <p>the Identity error rest error status field.</p>
     */
    IDENTITY_ERROR(10520, "Id错误"),
    /**
     * <code>IDENTITY_IS_NULL</code>
     * <p>the Identity is null rest error status field.</p>
     */
    IDENTITY_IS_NULL(10521, "Id为空"),
    /**
     * <code>IDENTITY_REPEATED</code>
     * <p>the Identity repeated rest error status field.</p>
     */
    IDENTITY_REPEATED(10522, "Id重复"),
    /**
     * <code>DATA_ERROR</code>
     * <p>the Data error rest error status field.</p>
     */
    DATA_ERROR(10600, "数据错误"),
    /**
     * <code>DATA_CREATE_FAILED</code>
     * <p>the Data create failed rest error status field.</p>
     */
    DATA_CREATE_FAILED(10601, "数据创建失败"),
    /**
     * <code>DATA_UPDATE_FAILED</code>
     * <p>the Data update failed rest error status field.</p>
     */
    DATA_UPDATE_FAILED(10602, "数据更新失败"),
    /**
     * <code>DATA_SAVE_FAILED</code>
     * <p>the Data save failed rest error status field.</p>
     */
    DATA_SAVE_FAILED(10603, "数据保存失败"),
    /**
     * <code>DATA_DELETE_FAILED</code>
     * <p>the Data delete failed rest error status field.</p>
     */
    DATA_DELETE_FAILED(10604, "数据删除失败"),
    /**
     * <code>DATA_QUERY_FAILED</code>
     * <p>the Data query failed rest error status field.</p>
     */
    DATA_QUERY_FAILED(10605, "数据查询失败"),
    /**
     * <code>DATA_TRANSFORM_FAILED</code>
     * <p>the Data transform failed rest error status field.</p>
     */
    DATA_TRANSFORM_FAILED(10606, "数据转换失败"),

    /**
     * <code>DATA_ALL_ERROR</code>
     * <p>the Data all error rest error status field.</p>
     */
    DATA_ALL_ERROR(10610, "数据批量处理错误"),
    /**
     * <code>DATA_INSERT_ALL_FAILED</code>
     * <p>the Data insert all failed rest error status field.</p>
     */
    DATA_INSERT_ALL_FAILED(10611, "数据批量插入失败"),
    /**
     * <code>DATA_UPDATE_ALL_FAILED</code>
     * <p>the Data update all failed rest error status field.</p>
     */
    DATA_UPDATE_ALL_FAILED(10612, "数据批量更新失败"),
    /**
     * <code>DATA_SAVE_ALL_FAILED</code>
     * <p>the Data save all failed rest error status field.</p>
     */
    DATA_SAVE_ALL_FAILED(10613, "数据批量保存失败"),
    /**
     * <code>DATA_DELETE_ALL_FAILED</code>
     * <p>the Data delete all failed rest error status field.</p>
     */
    DATA_DELETE_ALL_FAILED(10614, "数据批量删除失败"),
    /**
     * <code>DATA_QUERY_ALL_FAILED</code>
     * <p>the Data query all failed rest error status field.</p>
     */
    DATA_QUERY_ALL_FAILED(10615, "数据批量查询失败"),

    /**
     * <code>STREAM_ERROR</code>
     * <p>the Stream error rest error status field.</p>
     */
    STREAM_ERROR(10620, "数据流错误"),
    /**
     * <code>STREAM_READ_ERROR</code>
     * <p>the Stream read error rest error status field.</p>
     */
    STREAM_READ_ERROR(10621, "数据流读取错误"),
    /**
     * <code>STREAM_WRITE_ERROR</code>
     * <p>the Stream write error rest error status field.</p>
     */
    STREAM_WRITE_ERROR(10622, "数据流写入错误"),
    /**
     * <code>STREAM_TRANSFER_ERROR</code>
     * <p>the Stream transfer error rest error status field.</p>
     */
    STREAM_TRANSFER_ERROR(10623, "数据流迁移错误"),

    /**
     * <code>XML_ERROR</code>
     * <p>the Xml error rest error status field.</p>
     */
    XML_ERROR(10630, "Xml错误"),
    /**
     * <code>XML_READ_ERROR</code>
     * <p>the Xml read error rest error status field.</p>
     */
    XML_READ_ERROR(10631, "Xml读取错误"),
    /**
     * <code>XML_WRITE_ERROR</code>
     * <p>the Xml write error rest error status field.</p>
     */
    XML_WRITE_ERROR(10632, "Xml写入错误"),
    /**
     * <code>XML_MARSHAL_ERROR</code>
     * <p>the Xml marshal error rest error status field.</p>
     */
    XML_MARSHAL_ERROR(10633, "Xml编辑错误"),

    /**
     * <code>ZIP_ERROR</code>
     * <p>the Zip error rest error status field.</p>
     */
    ZIP_ERROR(10640, "zip数据处理错误"),
    /**
     * <code>ZIP_READ_ERROR</code>
     * <p>the Zip read error rest error status field.</p>
     */
    ZIP_READ_ERROR(10641, "zip数据读取错误"),
    /**
     * <code>ZIP_WRITE_ERROR</code>
     * <p>the Zip write error rest error status field.</p>
     */
    ZIP_WRITE_ERROR(10642, "zip数据写入错误"),
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
