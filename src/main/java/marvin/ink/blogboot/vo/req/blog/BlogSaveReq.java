package marvin.ink.blogboot.vo.req.blog;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 21:19
 */
@Data
@ApiModel
public class BlogSaveReq {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属类目
     */
    @NotNull(message = "请指定博客分类")
    private Integer categoryId;
    /**
     * 所属作者 or 用户
     */
    @NotNull(message = "作者id不能为空")
    private Integer authorId;

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
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
    private Boolean locked = false;
    /**
     * 发布状态
     */
    private Boolean published = false;

    /**
     * 博客对应选中了那些标签
     */
    private List<Integer> tagIds;
}
