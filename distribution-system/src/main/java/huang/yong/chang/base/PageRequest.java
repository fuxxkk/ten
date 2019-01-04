package huang.yong.chang.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageRequest {
    private Integer page;

    private Integer pageSize;

    /*@ApiModelProperty(hidden = true)
    private String orderBy;*/

    private String orderByColumn;

    private Boolean isAsc;
}
