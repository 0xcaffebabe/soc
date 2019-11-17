package wang.ismy.soc.user.controller;

import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;
import wang.ismy.soc.user.pojo.User;
import wang.ismy.soc.user.service.UserService;

import java.util.Map;


/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;

    @PostMapping("sendsms/{phone}")
    public Result sendSms(@PathVariable String phone){
        userService.sendSms(phone);
        return Result.success("发送成功");
    }

    @PostMapping("register/{code}")
    public Result register(@PathVariable String code, @RequestBody User user){
        return userService.register(code,user);
    }

    @PostMapping("login")
    public Result login(@RequestBody User user){
        user = userService.login(user);
        if (user == null){
            return new Result(false, StatusCode.LOGIN_ERROR,"登录失败");
        }
        String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
        Map<String, String> map = Map.of(
                "token", token,
                "role","user"
        );
        return new Result(true,StatusCode.OK,"登录成功",map);
    }
}
