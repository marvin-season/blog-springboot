package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.res.image.ImageUploadRes;
import marvin.ink.blogboot.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 18:28
 * Description: ImageController
 */
@RestController
@RequestMapping("image")
@Api(tags = "上传图片")
public class ImageController {
    @Resource
    private ImageService imageService;

    @ApiOperation("上传图片")
    @PostMapping("upload")
    public ImageUploadRes upload(MultipartFile multipartFile) {
        return imageService.upload(multipartFile);
    }

}
