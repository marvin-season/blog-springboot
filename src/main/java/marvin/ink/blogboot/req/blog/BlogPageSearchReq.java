package marvin.ink.blogboot.req.blog;

import lombok.Data;
import marvin.ink.blogboot.model.common.PageRequest;

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
     * 博客状态
     */
    private Boolean locked;

    /**
     * 标签的id
     */
    private List<Integer> tagIds;

}
