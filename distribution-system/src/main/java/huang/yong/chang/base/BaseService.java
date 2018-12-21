package huang.yong.chang.base;

import huang.yong.chang.excep.SystemException;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

    Boolean save(T t);

    Boolean update(T t);

    Boolean delete(Serializable id);

    T selectOne(Serializable id);

    List<T> findAll();
}
