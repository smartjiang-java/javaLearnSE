package jqk.learn.sofa_http.utils;

/**
 * @Author:JQK
 * @Date:2021/2/5 14:40
 * @Package:jqk.learn.sofa_http.utils
 * @ClassName:DefaultErrorCode
 **/

public enum DefaultErrorCode implements  IErrorCode{
    SUCCESS("200", "success"),
    NULL_POINTER("0x00000001", "null pointer exception"),
    INDEX_OUT_OF_BOUNDS("0x00000002", "index out of bounds"),
    ILLEGAL_ARGUEMENTS("0x00000003", "illegal arguments"),
    TYPE_CAST_ERROR("0x00000004", "type cast error"),
    FILE_NOT_EXIST("0x00000005", "file not exist"),
    THREAD_WAIT_TIMEOUT("0x00000006", "thread wait timeout"),
    ILLEGAL_CHARACTERS("0x00000007", "strings contains illegal characters"),
    EXTREME_SIZE("0x00000008", "strings length extreme 32 bits"),
    TYPE_ERROR("0x00000009", "type error"),
    SYS_ERROR("0x0000000a", "system error");

    private final String code;
    private final String message;

    private DefaultErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
