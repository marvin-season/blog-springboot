package marvin.ink.blogboot;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 17:47
 * Description: FileTest
 */
public class FileTest {


    @Test
    public void uploadFile() throws FileNotFoundException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tDq6Az6zJrBmcKoxsc4";
        String accessKeySecret = "U9gHUVcvk1M23ufFciB2O7uaBhqK87";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\mawenshu\\Desktop\\马文澍.jpg");
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dir = dateFormat.format(new Date());
        String prefix = IdUtil.fastUUID().toString().replace("-", "");


        PutObjectResult object = ossClient.putObject("marvin-blog", dir + prefix + "mawenshu.jpg", inputStream);

        System.out.println(object.getETag());
        System.out.println(object.getVersionId());

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
