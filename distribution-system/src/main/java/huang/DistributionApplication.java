package huang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("huang.yong.chang.mapper")
@EnableScheduling
public class DistributionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributionApplication.class, args);
        System.out.println("http://localhost:8080/swagger-ui.html");
        System.out.println("Jenkins：--> http://23.234.53.46:8088/");
    }
}
