package wang.ismy.soc.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @author MY
 * @date 2019/11/10 21:53
 */
@SpringBootApplication
@EnableCaching
public class GatheringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
