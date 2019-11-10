package wang.ismy.soc.article.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author MY
 * @date 2019/11/10 16:17
 */
@Service
@AllArgsConstructor
public class CacheService {

    private RedisTemplate redisTemplate;

    public <T> T get(String key,Class<T> klass){
        return klass.cast(redisTemplate.opsForValue().get(key));
    }

    public void set(String key,Object obj){
        redisTemplate.opsForValue().set(key,obj);
    }
}
