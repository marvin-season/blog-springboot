package marvin.ink.blogboot.req.blog;

import lombok.Data;
import marvin.ink.blogboot.model.common.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 20:36
 */
@Data
public class BlogPageSearchReq extends PageRequest {
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
     * 博客状态
     */
    private Boolean locked;

    private LocalDateTime time;

    /**
     * 标签的id
     */
    private List<Integer> tagIds;

    private Boolean published;
}
