package base;

import lombok.Data;

@Data
public class PageRequest {
    private Integer page;
    private Integer pageSize;
}
