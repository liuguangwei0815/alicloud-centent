package com.alibaba.cententmyali.exception;

import com.alibaba.cententmyali.result.MsgCode;
import com.alibaba.cententmyali.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GolobleExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptioinHandler(HttpServletRequest request,Exception e){

        //业务异常
        if(e instanceof BussinessException){
            MsgCode msgCode = ((BussinessException) e).getMsgCode();
            log.error(msgCode.getMsg());
            return Result.fail(msgCode);
        }

        //校验异常
        if(e instanceof BindException){
            List<ObjectError> allErrors = ((BindException) e).getAllErrors();
            ObjectError objectError = allErrors.get(0);
            MsgCode msgCode = MsgCode.PARAMERROR.fillParam(objectError.getDefaultMessage());
            log.error(msgCode.getMsg());
            return Result.fail(msgCode);
        }

        if(e instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            MsgCode msgCode = MsgCode.PARAMERROR.fillParam(objectError.getDefaultMessage());
            log.error(msgCode.getMsg());
            return Result.fail(msgCode);
        }


        //其他异常
        log.error("异常:{}", e);
        return Result.fail(MsgCode.SERVER_ERROR);
    }

}
