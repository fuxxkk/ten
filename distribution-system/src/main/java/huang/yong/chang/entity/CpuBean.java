package huang.yong.chang.entity;

import lombok.Data;

@Data
public class CpuBean {

    Double userUseRange;

    Double sysUseRange;

    Double waitRange;

    Double errorRange;

    Double freeRange;

    Double totalRange;
}
