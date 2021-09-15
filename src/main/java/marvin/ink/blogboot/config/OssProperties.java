package marvin.ink.blogboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 18:26
 * Description: OssProperties
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    private static final String protocol = "https://";

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucket;

    public String getImageBaseUrl() {
        return OssProperties.protocol + this.bucket + "." + this.endpoint;
    }
}
