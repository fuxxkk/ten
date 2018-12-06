package base;

public interface BaseService<T> {

    Boolean save(T t);

    Boolean update(T t);

    Boolean delete(String id);

    T selectOne(String id);
}
