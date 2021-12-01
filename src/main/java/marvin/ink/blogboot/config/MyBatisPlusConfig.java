package marvin.ink.blogboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import marvin.ink.blogboot.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 14:45
 */
@Configuration
public class MyBatisPlusConfig {

    // 数据库字段填充策略
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }

    // 乐观锁 mybatis-plus:3.4.3.1
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); //分页插件
        return interceptor;
    }

/*    乐观锁旧版本
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
*/

    // 低版本分页插件， 好像不配也没报错

    /*
     * public PaginationInterceptor paginationInterceptor() {
     * return new PaginationInterceptor();
     * }
     */


}
