package wang.ismy.soc.base;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author MY
 * @date 2019/11/10 12:32
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        return new Result(false, StatusCode.ERROR.getCode(),e.getMessage(),null);
    }

}
