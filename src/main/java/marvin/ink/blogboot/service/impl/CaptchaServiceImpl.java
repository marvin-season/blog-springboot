package marvin.ink.blogboot.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.config.CaptchaProperties;
import marvin.ink.blogboot.res.captcha.CaptchaRes;
import marvin.ink.blogboot.service.CaptchaService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 马文澍
 * @Date: 2021/9/7 14:32
 */
@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService, InitializingBean {

    @Resource
    private CaptchaProperties captchaProperties;

    private Cache<String, String> cache;

    @Override
    public CaptchaRes get() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
        String id = IdUtil.fastSimpleUUID();
        cache.put(id, lineCaptcha.getCode());
        log.info("缓存中的验证码id={}, 验证码={}, 存储位置={}", id, cache.get(id), cache.hashCode());
        return new CaptchaRes().setId(id).setImage(lineCaptcha.getImageBase64Data());
    }

    @Override
    public boolean verify(String id, String input) {
        String code = cache.get(id);
        log.info("id: {}, input: {}, code: {},存储位置={}", id, input, code, cache.hashCode());
        cache.remove(id);

        return ObjectUtil.equals(code, input);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = new TimedCache<>(TimeUnit.SECONDS.toMillis(captchaProperties.getExpireTime()));
    }
}
