package marvin.ink.blogboot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 17:23
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResultEnum {

    SUCCESS(0, "success"),

    FAIL(1, "fail"),

    TOO_MANY(11, "系统繁忙"),

    OPERATION_MANY(12, "亲，操作太快啦"),

    PARAM_ERROR(400, "参数错误"),

    CAPTCHA_ERROR(432, "验证码错误"),

    AUTHEN_ERROR(401, "认证失败"),

    ACCESS_DENIED(403, "权限不足"),

    DATA_NO_FOUND(404, "数据不存在"),

    METHOD_NOT_ALLOWED(405, "请求方法不支持"),

    FILE_UPLOAD_ERROR(406, "上传文件错误"),

    ACCOUNT_EXCEPTION(407, "账户异常"),

    PRINTER_INVOKE_ERROR(408, "打印机调用错误"),

    ERROR(500, "网络异常，请稍后再试！"),

    UNEXPECTED_ERROR(501, "未定义错误"),

    REGISTERED(201, "当前账号未注册"),

    ;
    private final int code;
    private final String message;
}
