package yoziming.ad.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yoziming.ad.common.result.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局異常
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    // 自訂異常
    @ExceptionHandler(YyghException.class)
    public Result error(YyghException e) {
        e.printStackTrace();
        return Result.build(201,e.toString());
    }
}
