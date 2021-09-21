package marvin.ink.blogboot;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * @Author: 马文澍
 * @Date: 2021/9/19 21:23
 * Description: Mybatis
 */
public class Mybatis {
    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession sqlSession = factory.openSession();
        User mapper = sqlSession.getMapper(User.class);
    }
}
