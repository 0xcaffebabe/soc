package wang.ismy.soc.user.interceptor;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MY
 * @date 2019/11/15 16:40
 */
@Component
@Slf4j
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器经过");
        String header = request.getHeader("Authorization");

        if (!StringUtils.isEmpty(header)) {
            try {
                Claims claims = jwtUtil.parseJWT(header);
                String roles = claims.get("roles").toString();
                if (!StringUtils.isEmpty(roles)) {
                    request.setAttribute("roles",roles);
                }
            } catch (Exception e) {
                log.info("解析令牌产生异常：{}", e.getMessage());
            }
        }
        return true;
    }
}
