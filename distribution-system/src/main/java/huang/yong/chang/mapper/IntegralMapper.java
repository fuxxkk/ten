package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.base.PageRequest;
import huang.yong.chang.entity.Integral;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IntegralMapper extends BaseMapper<Integral> {

    List<Integral>  findPage(PageRequest pageRequest);
}
