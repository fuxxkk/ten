package huang.yong.chang.base;

import lombok.Data;

@Data
public class PageRequest {
    private Integer page;
    private Integer pageSize;
    private String order;
}
