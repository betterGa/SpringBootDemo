package com.jia.SpringBootDemo.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BizExceptionHandler {

    Logger logger = LoggerFactory.getLogger(BizExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    // 以 Json 格式返回
    @ResponseBody
    public ResponseResult bizExceptionHandler(BizException bizException) {
        //{} 是占位符
        logger.error("发生业务异常：{}", bizException.getErrorMessage());
        return ResponseResult.error(bizException.getErrorCode(),bizException.getErrorMessage());
    }
}

