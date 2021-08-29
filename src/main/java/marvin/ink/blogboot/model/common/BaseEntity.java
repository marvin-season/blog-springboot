package marvin.ink.blogboot.model.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:23
 */
@Data
public class BaseEntity {
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    /*
     * 注意是boolean或int, 如果是Boolean或者Integer则新增数据时，数据库中deleted字段未null
     */
    private int deleted;

    @TableField(fill = FieldFill.INSERT)
    private String uuid;
}
