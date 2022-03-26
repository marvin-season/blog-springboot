package marvin.ink.blogboot.res.blog;

import marvin.ink.blogboot.model.entity.Blog;
import marvin.ink.blogboot.model.entity.Tag;
import marvin.ink.blogboot.model.entity.User;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 21:05
 */

public class BlogInfoRes extends Blog {
    private List<Tag> tags;

    private String username;

    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
