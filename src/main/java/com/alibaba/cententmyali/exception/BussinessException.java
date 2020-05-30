package com.alibaba.cententmyali.exception;

import com.alibaba.cententmyali.result.MsgCode;
import lombok.Data;

/**
 * 业务逻辑层异常
 *
 * @author liuwei
 * @date 2020/05/12
 */
@Data
public class BussinessException extends RuntimeException {
    private MsgCode msgCode;

    public BussinessException(MsgCode msgCode) {
        this.msgCode = msgCode;
    }
}
