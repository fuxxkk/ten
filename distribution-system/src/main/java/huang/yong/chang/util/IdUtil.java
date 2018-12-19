package huang.yong.chang.util;

import org.springframework.stereotype.Component;

@Component
public class IdUtil {
    private IdUtil() {
    }

    public static Long getId() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        return snowflakeIdWorker.nextId();
    }
}
