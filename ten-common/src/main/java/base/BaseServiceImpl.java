package base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class BaseServiceImpl<T, M extends BaseMapper<T>> implements BaseService<T> {
    @Autowired
    protected M mapper;

    @Override
    public Boolean save(T t) {
        return mapper.insert(t) > 0 ? true : false;
    }

    @Override
    public Boolean update(T t) {
        return mapper.updateById(t) > 0 ? true : false;
    }

    @Override
    public Boolean delete(String id) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>();
        wrapper.eq("id", id);
        return mapper.delete(wrapper) > 0 ? true : false;
    }

    @Override
    public T selectOne(Serializable id) {
        return mapper.selectById(id);
    }
}
