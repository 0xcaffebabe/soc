package wang.ismy.soc.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/**
 * @author MY
 * @date 2019/11/19 16:28
 */
@Component
@Slf4j
public class ManagerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("过滤器经过");
        return null;
    }
}
