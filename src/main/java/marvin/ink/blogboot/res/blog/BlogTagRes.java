package marvin.ink.blogboot.res.blog;

import marvin.ink.blogboot.model.entity.Blog;
import marvin.ink.blogboot.model.entity.Tag;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 21:05
 */

public class BlogTagRes extends Blog {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
