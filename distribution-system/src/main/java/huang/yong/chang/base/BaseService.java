package huang.yong.chang.base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

    Boolean save(T t);

    Boolean update(T t);

    Boolean delete(String id);

    T selectOne(Serializable id);

    List<T> findAll();
}
