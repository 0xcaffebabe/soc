package wang.ismy.soc.qa.client;

import entity.Result;
import org.springframework.stereotype.Component;

/**
 * @author MY
 * @date 2019/11/18 22:07
 */
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String id) {
        return Result.error("服务不可用");
    }
}
