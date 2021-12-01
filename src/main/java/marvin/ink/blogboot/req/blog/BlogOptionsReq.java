package marvin.ink.blogboot.req.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import marvin.ink.blogboot.model.common.PageRequest;

@Data
public class BlogOptionsReq extends PageRequest {
    /**
     * 作者id
     */
    @ApiModelProperty("博客作者id")
    private Integer authorId;
    /**
     * 是否是推荐博客
     */
    @ApiModelProperty("是否是推荐博客")
    private boolean isRecommend;
    /**
     * 是否是草稿
     */
    @ApiModelProperty("是否是草稿")
    private boolean isDraft;
    /**
     * 是否是收藏博客
     */
    @ApiModelProperty("是否是收藏博客")
    private boolean isCollect;
}
