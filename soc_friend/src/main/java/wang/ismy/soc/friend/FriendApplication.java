package wang.ismy.soc.friend;

import interceptor.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;
import utils.JwtUtil;

/**
 * @author MY
 * @date 2019/11/17 15:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public JwtInterceptor jwtInterceptor(JwtUtil jwtUtil){
        return new JwtInterceptor(jwtUtil);
    }
}
