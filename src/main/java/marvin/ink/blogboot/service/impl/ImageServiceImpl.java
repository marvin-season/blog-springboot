package marvin.ink.blogboot.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.config.OssProperties;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.enums.ResultEnum;
import marvin.ink.blogboot.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 18:31
 * Description: ImageServiceImpl
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Resource
    private OssProperties ossProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile multipartFile) {

        String originalFilename = multipartFile.getOriginalFilename();


        String suffix = FileNameUtil.extName(originalFilename);
        String prefix = UUID.randomUUID().toString().replace("-", "");
        String dir = sdf.format(new Date());

        String realPath = dir + "/" + prefix + "." + suffix;

        String avatar = ossProperties.getImageBaseUrl() + "/" + realPath;

        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("获取上传文件输入流错误");
            throw CustomizeException.is(ResultEnum.FILE_UPLOAD_ERROR).hint("获取上传文件输入流错误");
        }

        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKey(), ossProperties.getSecretKey());

        try {
            ossClient.putObject(ossProperties.getBucket(), realPath, inputStream);

        } catch (ClientException e) {
            log.warn("上传文件到OSS出错");
            throw CustomizeException.is(ResultEnum.FILE_UPLOAD_ERROR).hint("上传到OSS出错");
        }

        return avatar;
    }
}
