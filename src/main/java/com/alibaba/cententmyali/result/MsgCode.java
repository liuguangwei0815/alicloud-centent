package com.alibaba.cententmyali.result;

import lombok.Data;

@Data
public class MsgCode {

    /**
     * 代码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;

    /**
     * 成功
     */
    public static MsgCode SUCCESS = new MsgCode(0, "sucess");
    /**
     * 服务器错误
     */
    public final static MsgCode SERVER_ERROR = new MsgCode(500100, "服务端异常");
    /**
     * 校验参数异常
     */
    public final static MsgCode PARAMERROR = new MsgCode(500101, "参数校验异常:%s");

    private MsgCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private MsgCode() {
    }

    /**
     * 填写参数
     */
    public MsgCode fillParam(String... msg) {
        String format = String.format(this.msg, msg);
        return new MsgCode(this.getCode(),format);
    }


}
