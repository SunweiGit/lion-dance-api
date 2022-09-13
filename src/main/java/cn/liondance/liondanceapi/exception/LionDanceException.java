package cn.liondance.liondanceapi.exception;

import lombok.*;

import java.util.Locale;

/**
 * The type Lion dance exception.
 *
 * @author sunwei
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LionDanceException extends Exception {
    private String code;
    private Locale locale;

    public LionDanceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public LionDanceException(String message, String code, Locale locale) {
        super(message);
        this.code = code;
        this.locale = locale;
    }
}
