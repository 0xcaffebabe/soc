package wang.ismy.soc.user.controller;

import entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.user.pojo.User;
import wang.ismy.soc.user.service.UserService;


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

    @PostMapping("sendsms/{phone}")
    public Result sendSms(@PathVariable String phone){
        userService.sendSms(phone);
        return Result.success("发送成功");
    }

    @PostMapping("register/{code}")
    public Result register(@PathVariable String code, @RequestBody User user){
        return userService.register(code,user);
    }
}
