package wang.ismy.soc.friend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;
import wang.ismy.soc.friend.client.UserClient;
import wang.ismy.soc.friend.dao.FriendDao;
import wang.ismy.soc.friend.dao.NoFriendDao;
import wang.ismy.soc.friend.pojo.Friend;
import wang.ismy.soc.friend.pojo.NoFriend;

import javax.transaction.Transactional;

/**
 * @author MY
 * @date 2019/11/17 21:36
 */
@Service
@Transactional(rollbackOn = Exception.class)
@AllArgsConstructor
public class FriendService {

    private FriendDao friendDao;
    private NoFriendDao noFriendDao;
    private UserClient userClient;

    public int addFriend(String userId, String friendId) {
        // 如果存在userid与friendid关系，则是重复添加好友
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendId);
        if (friend != null) {
            return 0;
        }
        // 添加关系
        friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        Friend me = friendDao.save(friend);
        // 如果friendid与userid有数据，将双方的关系都改为1
        friend = friendDao.findByUseridAndFriendid(friendId, userId);
        if (friend != null) {
            friendDao.updateIsLike(userId,friendId,"1");
            friendDao.updateIsLike(friendId,userId,"1");
        }
        // 更新用户与其朋友粉丝数与关注数
        userClient.updateFansAndFollow(userId,friendId,1);
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        // 判断是否已经不是好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendId);

        if (noFriend != null){
            return 0;
        }
        noFriend  = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendId) {

        // 删除userId->friendId
        friendDao.deleteFriend(userId,friendId);
        // 更新friendId->userId的isLike 0
        friendDao.updateIsLike(userId, friendId,"0");

        // 非好友表添加userId->friendId
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        // 更新双方粉丝及关注数
        userClient.updateFansAndFollow(userId,friendId,-1);
    }
}
