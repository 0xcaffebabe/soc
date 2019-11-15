package wang.ismy.soc.sms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author MY
 * @date 2019/11/15 13:21
 */
@Component
@RabbitListener(queues = "sms")
@Slf4j
public class SmsListener {

    @RabbitHandler
    public void process(Map<String,String> map){
        log.info("短信发送消费:{},{}",map.get("phone"),map.get("code"));
    }
}
