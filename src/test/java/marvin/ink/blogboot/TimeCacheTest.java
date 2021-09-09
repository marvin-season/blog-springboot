package marvin.ink.blogboot;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 马文澍
 * @Date: 2021/9/9 13:48
 */
public class TimeCacheTest {

    @Test
    public void test1() throws InterruptedException {
        Cache<String, String> cache = new TimedCache<>(2000);


        cache.put("1", "111111");
        System.out.println(cache.get("1"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache.get("1"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache.get("1"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache.get("1"));

        TimeUnit.SECONDS.sleep(3);
        System.out.println(cache.get("1"));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache.get("1"));
    }
}
