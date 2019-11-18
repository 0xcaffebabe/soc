package wang.ismy.soc.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MY
 * @date 2019/11/18 13:17
 */
@FeignClient("soc-user")
@RequestMapping("user")
public interface UserClient {

    @PutMapping("/{userId}/{friendId}/{count}")
    void updateFansAndFollow(@PathVariable("userId") String userId,
                             @PathVariable("friendId") String friendId, @PathVariable("count") int count);
}
