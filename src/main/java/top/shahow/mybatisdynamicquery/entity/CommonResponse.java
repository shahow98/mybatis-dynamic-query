package top.shahow.mybatisdynamicquery.entity;

import lombok.Data;

/**
 * 请求响应体
 *
 * @author shahow
 * @date 2023-02-18
 */
@Data
public class CommonResponse<T> {
    private Integer code;
    private T data;
    private String message;

    private CommonResponse(Integer code, T result, String errMsg) {
        this.code = code;
        this.data = result;
        this.message = errMsg;
    }

    public static <T> CommonResponse success(T result) {
        return new CommonResponse(ResultCode.DEF_SUCCESS_CODE, result, "");
    }

    public static <T> CommonResponse success(Integer code, T result) {
        return new CommonResponse(code, result, null);
    }

    public static CommonResponse success() {
        return new CommonResponse(ResultCode.DEF_SUCCESS_CODE, null, null);
    }

    public static CommonResponse fail(String errMsg) {
        return new CommonResponse(ResultCode.DEF_FAIL_CODE, null, errMsg);
    }

    public static <T> CommonResponse fail(int code, T result, String errMsg) {
        return new CommonResponse(code, result, errMsg);
    }

    public static CommonResponse fail(int code, String errMsg) {
        return new CommonResponse(code, null, errMsg);
    }

    interface ResultCode {
        Integer DEF_SUCCESS_CODE = 0;
        Integer DEF_FAIL_CODE = -1;
    }
}
