package wang.ismy.soc.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MY
 * @date 2019/11/17 14:17
 */
@FeignClient(value = "soc-base",fallback = BaseClientImpl.class)
public interface BaseClient {

    @GetMapping("/label/{id}")
    Result findById(@PathVariable("id") String id);
}
