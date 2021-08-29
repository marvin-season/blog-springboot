package marvin.ink.blogboot.model.common;

import lombok.Data;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.enums.ResultEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MyResponse<T> implements Serializable {

    private String message;

    private String hint;

    private int code;

    private LocalDateTime timestamp = LocalDateTime.now();

    private T result;

    private MyResponse() {
    }

    public MyResponse<T> code(int code) {
        this.code = code;
        return this;
    }

    public MyResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public MyResponse<T> hint(String hint) {
        this.hint = hint;
        return this;
    }

    public MyResponse<T> result(T result) {
        this.result = result;
        return this;
    }

    public static MyResponse success(Object result) {
        return is(ResultEnum.SUCCESS).result(result);
    }

    public static MyResponse error() {
        return is(ResultEnum.ERROR);
    }

    public static MyResponse error(int code, String message) {
        return new MyResponse().code(code).message(message);
    }

    public static MyResponse error(String message) {
        return error().message(message);
    }

    public static MyResponse is(ResultEnum resultEnum) {
        return new MyResponse().code(resultEnum.getCode()).message(resultEnum.getMessage());
    }

    public static MyResponse is(CustomizeException e) {
        return new MyResponse().code(e.getCode()).message(e.getMessage()).hint(e.getHint());
    }

}
