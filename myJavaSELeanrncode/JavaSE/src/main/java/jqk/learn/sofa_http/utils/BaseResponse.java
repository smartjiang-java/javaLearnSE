package jqk.learn.sofa_http.utils;

/**
 * @Author:JQK
 * @Date:2021/2/5 14:38
 * @Package:jqk.learn.sofa_http
 * @ClassName:BaseResponse
 **/

public class BaseResponse<T> {
    private String code;
    private String msg;
    private T data;
    private Throwable e;

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this(DefaultErrorCode.SUCCESS.getCode(), DefaultErrorCode.SUCCESS.getMessage(), data);
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(String code, String msg, T data, Throwable e) {
        this(code, msg, data);
        this.e = e;
    }

    public BaseResponse(IErrorCode error, T data) {
        this(error.getCode(), error.getMessage(), data);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getE() {
        return this.e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }
}