package com.example.rest.config;

import com.example.common.Response;
import com.example.common.exception.AuthExcetption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zjianfa
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    private static final String BAD_REQUEST_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());
    @Value("${enable.debug}")
    private Boolean enableDev;

    /**
     * 处理 form data方式调用接口校验失败抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Response<List<String>> bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        return Response.getFail(BAD_REQUEST_CODE, BAD_REQUEST_MSG, collect);
    }

    /**
     * 处理 json 请求体调用接口校验失败抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        return Response.getFail(BAD_REQUEST_CODE, BAD_REQUEST_MSG, collect);
    }

    /**
     * 处理单个参数校验失败抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<List<String>> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        return Response.getFail(BAD_REQUEST_CODE, BAD_REQUEST_MSG, collect);
    }

    @ExceptionHandler(RuntimeException.class)
    public Response<List<String>> runtimeExceptionHandler(RuntimeException e) {
        // 当为debug模式式，开启异常
        if (enableDev != null && enableDev == true){
            throw e;
        }
        return Response.getFail(BAD_REQUEST_CODE, e.getMessage());
    }

    @ExceptionHandler(AuthExcetption.class)
    public Response<List<String>>authExceptionHandler(AuthExcetption e) {
        return Response.getFail(BAD_REQUEST_CODE, e.getMessage());
    }
}