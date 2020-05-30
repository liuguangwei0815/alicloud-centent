package com.alibaba.cententmyali.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result() {
    }


    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result<T>}
     */
    public static <T>  Result<T> success(T data) {
        return new Result(data);
    }

    /**
     * 失败
     *
     * @param msgCode 错误代码
     * @return {@link Result<T>}
     */
    public static <T>  Result<T> fail(MsgCode msgCode) {
        return new Result(msgCode);
    }


    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(MsgCode msgCode) {
        if (msgCode == null) {
            return;
        }
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }


}
