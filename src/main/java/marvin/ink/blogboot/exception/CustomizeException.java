package marvin.ink.blogboot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marvin.ink.blogboot.model.enums.ResultEnum;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 17:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomizeException extends RuntimeException {
    private int code = ResultEnum.FAIL.getCode();

    private String message;

    private String hint;

    public CustomizeException() {
    }

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(Throwable cause) {
        super(cause);
    }

    public CustomizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomizeException code(int code) {
        this.code = code;
        return this;
    }

    public CustomizeException message(String message) {
        this.message = message;
        return this;
    }

    public CustomizeException hint(String hint) {
        this.hint = hint;
        return this;
    }

    public static CustomizeException is(ResultEnum resultEnum) {
        return new CustomizeException().code(resultEnum.getCode()).message(resultEnum.getMessage());
    }
}
