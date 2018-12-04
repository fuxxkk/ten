package base;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T> {
    @Autowired
    protected T mapper;
}
