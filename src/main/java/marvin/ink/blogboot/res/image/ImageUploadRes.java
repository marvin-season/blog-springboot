package marvin.ink.blogboot.res.image;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: 马文澍
 * @Date: 2021/9/21 11:45
 * Description: ImageUploadRes
 */

@Data
@Accessors(chain = true)
public class ImageUploadRes {
    private String imageUrl;
}
