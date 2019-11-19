package wang.ismy.soc.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.EntityReference;

/**
 * @author MY
 * @date 2019/11/19 16:28
 */
@Component
@Slf4j
@AllArgsConstructor
public class ManagerFilter extends ZuulFilter {

    private JwtUtil jwtUtil;

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

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        if (request.getRequestURI().contains("admin/login")){
            return null;
        }
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization)) {
            try {
                Claims claims = jwtUtil.parseJWT(authorization);
                String roles = ((String) claims.get("roles"));
                if ("admin".equals(roles)){
                    currentContext.addZuulRequestHeader("Authorization",authorization);
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("access forbid");
        currentContext.setSendZuulResponse(false);
        log.info("非管理员角色访问");
        return null;
    }
}
