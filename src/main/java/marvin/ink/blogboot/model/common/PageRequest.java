package marvin.ink.blogboot.model.common;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PageRequest {
    @Max(500)
    @Min(5)
    private int pageSize=20;
    @Min(1)
    private int pageNo;

}
