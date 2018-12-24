package huang.yong.chang.util;

import org.springframework.stereotype.Component;

@Component
public class IdUtil {
    private IdUtil() {
    }

    public static Long getId() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(5, 5);
        return snowflakeIdWorker.nextId();
    }
}
