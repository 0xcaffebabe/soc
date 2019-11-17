package wang.ismy.soc.friend.controller;

import entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wang.ismy.soc.friend.service.FriendService;

/**
 * @author MY
 * @date 2019/11/17 21:25
 */
@RestController
@RequestMapping("friend")
@AllArgsConstructor
public class FriendController {

    private FriendService friendService;

    @PutMapping("like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type,
                            @RequestAttribute(value = "id",required = false) String userId) {
        if (StringUtils.isEmpty(userId)){
            return Result.error("权限不足");
        }
        if (!StringUtils.isEmpty(type)) {
            if ("1".equals(type)){
                // 添加好友
                int f = friendService.addFriend(userId,friendId);
                if (f == 1){
                    return Result.success("添加成功");
                }else {
                    return Result.error("不能重复添加");
                }
            }else if ("2".equals(type)) {
                // 添加非好友
                int f = friendService.addNoFriend(userId,friendId);
                if (f == 0){
                    return Result.error("不能重复添加");
                }else {
                    return Result.success("添加成功");
                }
            }
        }
        return Result.error("参数异常");
    }
}
