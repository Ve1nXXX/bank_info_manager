package com.bank_manager.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.bank_manager.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 封装后台查询的数据,统一格式化处理交给前端,前端按照对应的响应码,响应数据进行相应的操作,例如列表渲染,删除修改等
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private Long count;


    public static <T> ResponseVo<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> ResponseVo<T> error(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> ResponseVo<T> error(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    public static <T> ResponseVo<T> success() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> ResponseVo<T> success(String msg) {
        return restResult(null, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResponseVo<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> ResponseVo<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> ResponseVo<T> success(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResponseVo<T> error() {
        return restResult(null, CommonConstants.FAIL, null);
    }


    public static <T> ResponseVo<T> restResult(T data, int code, String msg) {
        ResponseVo<T> apiResult = new ResponseVo<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> ResponseVo<T> restResult(T data, int code, String msg, Long count) {
        ResponseVo<T> apiResult = new ResponseVo<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setCount(count);
        return apiResult;
    }
}
