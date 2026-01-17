package com.funkey.config;

import com.funkey.status.ResultCodeEnum;
import com.funkey.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleRuntime(RuntimeException ex) {
        log.error("RuntimeException: {}", ex);
        Result<Object> result = Result.fail();
        return result.message(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception ex) {
        log.error("Exception: {}", ex);
        Result<Object> result = Result.fail();
        return result.message(ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result<Object> handleThrowable(Throwable ex) {
        log.error("Throwable: {}", ex);
        Result<Object> result = Result.fail();
        return result.message(ex.getMessage());
    }

    // Add Data Integrity Handler etc. for Detailed Exception
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handleDataIntegrity(DataIntegrityViolationException ex) {
        String detail = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : ex.getMessage();
        return Result.build(detail, ResultCodeEnum.ILLEGAL_REQUEST);
    }
}
