package marvin.ink.blogboot.model.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Data
public class PageData<T> {

    /**
     * 总记录条数
     */
    private long size;

    /**
     * 当前页码
     */
    private long pageNum;

    /**
     * 总记录条数
     */
    private long total;

    /**
     * 数据集合
     */
    private long totalPage;

    /**
     * 数据集合
     */
    private List<T> records;

    public PageData() {
    }

    public PageData(long total, List<T> records, long size) {
        this.total = total;
        this.records = records;
        this.size = size;
        this.totalPage = total / size + 1;
    }

    public static <T> PageData<T> of(IPage<T> page) {
        return new PageData<T>()
                .setPageNum(page.getCurrent())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setTotalPage(page.getPages())
                .setRecords(page.getRecords());
    }

    public static <T> PageData<T> of(IPage<?> page, Class<T> clazz) {
        List<T> collect = page.getRecords()
                .stream()
                .map(entity -> BeanUtil.copyProperties(entity, clazz))
                .collect(Collectors.toList());

        return new PageData<T>()
                .setPageNum(page.getCurrent())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setTotalPage(page.getPages())
                .setRecords(collect);
    }

}
