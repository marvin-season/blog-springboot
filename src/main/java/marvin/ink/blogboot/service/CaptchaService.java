package marvin.ink.blogboot.service;


import marvin.ink.blogboot.res.captcha.CaptchaRes;

public interface CaptchaService {

    CaptchaRes get();

    boolean verify(String id, String input);

}
