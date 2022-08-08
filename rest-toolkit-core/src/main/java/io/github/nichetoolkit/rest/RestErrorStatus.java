package io.github.nichetoolkit.rest;

import lombok.Getter;

/**
 * <p>RestErrorStatus</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Getter
public enum RestErrorStatus implements RestStatus {

    SUCCESS(200, "成功"),
    FAILED(400, "失败"),

    /** httpclient */
    HTTP_CONFIG_ERROR(8000,"Http配置错误"),
    HTTP_ERROR(8001,"Http请求错误"),
    HTTP_RESPONSE_NULL(8011,"Http Response为空"),
    HTTP_RESULT_FAIL(8012,"Http Rest请求失败"),
    HTTP_RESULT_DATA_NULL(8013,"Http Response Data为空"),

    /** timeout */
    UNKNOWN_ERROR(8888,"未知错误"),

    BEAN_LACK_ERROR(9101,"Bean缺失错误"),
    INTERFACE_LACK_ERROR(9111,"实现缺失错误"),
    METHOD_LACK_ERROR(9112,"方法缺失错误"),

    UNSUPPORTED(9999,"不支持"),
    TIME_OUT(10000, "访问超时"),
    /** base */
    PARAM_ERROR(10010, "参数错误"),
    PARAM_INVALID(10012, "参数无效"),
    PARAM_MISSING(10011, "参数缺失"),
    /** json */
    PARSE_ERROR(10100, "解析错误"),
    JSON_PARSE_ERROR(10110, "JSON解析错误"),
    JSON_PARSE_BEAN(10111, "JSON解析为Bean出错"),
    JSON_PARSE_CONVERT(10112, "JSON解析转换类型出错"),
    JSON_PARSE_LIST(10113, "JSON解析为List出错"),
    JSON_PARSE_SET(10114, "JSON解析为Set出错"),
    JSON_PARSE_MAP(10115, "JSON解析为Map出错"),
    JSON_PARSE_RESULT(10116, "JSON解析为Result出错"),
    /** deserialize */
    JSON_DESERIALIZE_ERROR(10120, "JSON反序列化错误"),

    /** resource */
    RESOURCE_ERROR(10200, "资源错误"),
    RESOURCE_NOT_FOUND(10201, "资源不存在"),
    RESOURCE_UNAVAILABLE(10202, "资源不可用"),

    /** file */
    FILE_ERROR(10210, "文件错误"),
    FILE_NOT_EXIST(10211, "文件不存在"),
    FILE_IS_EXIST(10212, "文件已经存在"),
    FILE_UNAVAILABLE(10213, "文件不可用"),

    FILE_CREATE_ERROR(10214, "文件创建错误"),
    FILE_COPY_ERROR(10215, "文件复制错误"),

    /** access */
    AUTH_ERROR(10300, "权限错误"),
    AUTH_DENIED(10302, "拒绝访问"),
    AUTH_FORBIDDEN(10301, "权限不足"),

    /** token */
    TOKEN_ERROR(10310, "认证错误"),
    TOKEN_FAILED(10311, "认证失败"),
    TOKEN_SERVICE_INVALID(10312, "认证无效"),
    TOKEN_EXPIRED(10313, "认证过期"),

    /** login */
    LOGIN_ERROR(10320, "登录错误"),
    LOGIN_FAILED(10321, "登录失败"),
    LOGIN_INFO_INVALID(10322, "登录无效"),
    LOGIN_INFO_EXPIRED(10323, "登录过期"),

    /** service */
    SERVICE_ERROR(10400, "服务器错误"),
    SERVICE_UNAVAILABLE(10401, "服务不可用"),

    /** config */
    CONFIG_ERROR(10410, "配置错误"),
    CONFIG_INVALID(10411, "配置无效"),
    CONFIG_UNAVAILABLE(10412, "配置不可用"),


    CONVERT_ERROR(10420, "转换错误"),
    CONVERT_TYPE_UNSUPPORTED(10421, "转换类型不支持"),
    CONVERT_TYPE_UNKNOWN(10422, "转换类型未知"),

    /** class */
    CLASS_ERROR(10430, "类类型错误"),
    CLASS_TYPE_UNSUPPORTED(10431, "类类型不支持"),
    CLASS_TYPE_UNKNOWN(10432, "类类型未知"),
    CLASS_TYPE_UNRENEW(10433, "类类型无法实例化"),

    /** field */
    FIELD_ERROR(10500, "字段错误"),
    FIELD_IS_NULL(10501, "字段为空"),
    FIELD_REPEATED(10502, "字段重复"),
    FIELD_NOT_EXIST(10503, "对象不存在"),
    FIELD_IS_EXIST(10504, "对象已存在"),
    /** name */
    NAME_ERROR(10510, "名称错误"),
    NAME_IS_NULL(10511, "名称为空"),
    NAME_REPEATED(10512, "名称重复"),
    /** IDENTITY */
    IDENTITY_ERROR(10520, "Id错误"),
    IDENTITY_IS_NULL(10521, "Id为空"),
    IDENTITY_REPEATED(10522, "Id重复"),
    /** data */
    DATA_ERROR(10600, "数据错误"),
    DATA_CREATE_FAILED(10601, "数据创建失败"),
    DATA_UPDATE_FAILED(10602, "数据更新失败"),
    DATA_SAVE_FAILED(10603, "数据保存失败"),
    DATA_DELETE_FAILED(10604, "数据删除失败"),
    DATA_QUERY_FAILED(10605, "数据查询失败"),
    DATA_TRANSFORM_FAILED(10606, "数据转换失败"),

    /** data all */
    DATA_ALL_ERROR(10610, "数据批量处理错误"),
    DATA_INSERT_ALL_FAILED(10611, "数据批量插入失败"),
    DATA_UPDATE_ALL_FAILED(10612, "数据批量更新失败"),
    DATA_SAVE_ALL_FAILED(10613, "数据批量保存失败"),
    DATA_DELETE_ALL_FAILED(10614, "数据批量删除失败"),
    DATA_QUERY_ALL_FAILED(10615, "数据批量查询失败"),

    /** stream */
    STREAM_ERROR(10620, "数据流错误"),
    STREAM_READ_ERROR(10621, "数据流读取错误"),
    STREAM_WRITE_ERROR(10622, "数据流写入错误"),
    STREAM_TRANSFER_ERROR(10623, "数据流迁移错误"),

    /** zip */
    ZIP_ERROR(10630, "zip数据处理错误"),
    ZIP_READ_ERROR(10631, "zip数据读取错误"),
    ZIP_WRITE_ERROR(10632, "zip数据写入错误"),
    ;

    private final Integer status;
    private final String message;

    RestErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
