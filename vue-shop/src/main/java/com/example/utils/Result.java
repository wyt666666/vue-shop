package com.example.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * [2022-09-02] code变status; 顺序按照status-data-msg依次排列; 增加status枚举构造器
 *
 * @author jtt
 * @date [2022/8/11 15:43]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer status;
    private T data;
    private String msg;

    // 加一个只有状态码和信息的构造器


    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * success
     *
     * @param data
     * @return com.imooc.vo.common.Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), data, ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success() {
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * success
     *
     * @param data
     * @param msg
     * @return com.imooc.vo.common.Result<T>
     */
    public static <T> Result<T> success(T data, String msg) {
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), data, msg);
    }

    public static <T> Result<T> success(ResultCodeEnum resultCodeEnum, T data, String msg) {
        return new Result<T>(resultCodeEnum.getCode(), data, msg);
    }

    public static <T> Result<T> forbidden(String msg) {
        return new Result<T>(ResultCodeEnum.FORBIDDEN.getCode(), msg);
    }

    /**
     * forbidden 传递错误信息
     *
     * @param data
     * @return com.imooc.vo.common.Result<T>
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCodeEnum.FORBIDDEN.getCode(), data, ResultCodeEnum.FORBIDDEN.getMessage());
    }
}
