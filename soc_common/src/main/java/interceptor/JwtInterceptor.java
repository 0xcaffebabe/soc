package interceptor;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MY
 * @date 2019/11/17 15:41
 */
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader("Authorization");

        if (!StringUtils.isEmpty(header)) {
            try {
                Claims claims = jwtUtil.parseJWT(header);
                String roles = claims.get("roles").toString();
                String id = claims.getId();

                if (!StringUtils.isEmpty(roles)) {
                    request.setAttribute("roles",roles);
                    request.setAttribute("id",id);
                }
            } catch (Exception e) {
                System.out.println("解析令牌产生异常："+e.getMessage());

            }
        }
        return true;
    }
}
