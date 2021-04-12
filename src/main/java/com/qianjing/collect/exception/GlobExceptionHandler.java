package com.qianjing.collect.exception;

import com.qianjing.collect.comm.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobExceptionHandler {


    @ExceptionHandler(value = RuntimeException.class)
    public Response<Void> exceptionHandler(RuntimeException e) {
        if (e instanceof OutException) {
            return Response.returnFail(null, e.getMessage());
        }
        if (e instanceof InException) {
            e.printStackTrace();
            return Response.returnFail(null, "系统错误");
        }
        throw new InException(e);
    }
}
