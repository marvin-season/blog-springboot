package marvin.ink.blogboot.model.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import marvin.ink.blogboot.model.common.BaseEntity;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 15:43
 */
@Data
@Accessors(chain = true)
public class BlogTag extends BaseEntity {
    private Integer blogId;
    private Integer tagId;
}
