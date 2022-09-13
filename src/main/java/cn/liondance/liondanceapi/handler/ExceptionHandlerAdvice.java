package cn.liondance.liondanceapi.handler;

import cn.liondance.liondanceapi.exception.LionDanceException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

/**
 * The type Exception handler advice.
 *
 * @author sunwei
 */
@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerAdvice {

    private final MessageSource messageSource;

    /**
     * Handle lion dance exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(LionDanceException.class)
    public ResponseEntity<HashMap<String, String>> handleLionDanceException(LionDanceException e) {
        HashMap<String, String> map = new HashMap<>(10);
        map.put("code", e.getCode());
        map.put("exceptionInfo", messageSource.getMessage(e.getCode(), null, e.getLocale()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

}
