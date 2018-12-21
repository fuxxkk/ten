package huang.yong.chang.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, M extends BaseMapper<T>> implements BaseService<T> {
    @Autowired
    protected M mapper;

    @Override
    public Boolean save(T t){
        t.setId(IdUtil.getId());
        return mapper.insert(t) > 0 ? true : false;
    }

    @Override
    public Boolean update(T t){
        return mapper.updateById(t) > 0 ? true : false;
    }

    @Override
    public Boolean delete(Serializable id) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>();
        wrapper.eq("id", id);
        return mapper.delete(wrapper) > 0 ? true : false;
    }

    @Override
    public T selectOne(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectList(null);
    }
}
