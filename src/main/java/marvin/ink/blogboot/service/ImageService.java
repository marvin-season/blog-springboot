package marvin.ink.blogboot.service;

import marvin.ink.blogboot.res.image.ImageUploadRes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 18:28
 * Description: ImageService
 */
public interface ImageService {
    ImageUploadRes upload(MultipartFile multipartFile);
}
