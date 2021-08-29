package marvin.ink.blogboot.model.entity;

import lombok.Data;
import marvin.ink.blogboot.model.common.BaseEntity;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:22
 */
@Data
public class Blog extends BaseEntity {

    /**
     * 所属类目
     */
    private Integer categoryId;
    /**
     * 所属作者 or 用户
     */
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 浏览次数
     */
    private Integer viewCount;
    /**
     * 收藏次数
     */
    private Integer collectCount;
    /**
     * 点赞次数
     */
    private Integer likeCount;
    /**
     * 封禁状态
     * false 未封禁
     */
    private Boolean locked;
    /**
     * 发布状态
     * false: 未发布
     */
    private Boolean published;
}
