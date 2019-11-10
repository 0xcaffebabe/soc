package wang.ismy.soc.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @author MY
 * @date 2019/11/10 14:25
 */
@SpringBootApplication
public class QaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){

        return new IdWorker(1, 1);
    }

}
