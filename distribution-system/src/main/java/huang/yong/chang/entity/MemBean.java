package huang.yong.chang.entity;

import lombok.Data;

@Data
public class MemBean {
    private Integer  totalMem;

    private Integer freeMem;

    private Integer usedMem;

    private String date;
}
