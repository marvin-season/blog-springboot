package marvin.ink.blogboot.model.entity;

import lombok.Data;
import marvin.ink.blogboot.model.common.BaseEntity;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:27
 */
@Data
public class Tag extends BaseEntity {
    /**
     * 标签名字
     */
    private String tagName;
}
