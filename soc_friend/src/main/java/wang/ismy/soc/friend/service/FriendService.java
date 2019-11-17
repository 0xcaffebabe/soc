package wang.ismy.soc.friend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
            friendDao.updateIsLike(userId,friendId);
            friendDao.updateIsLike(friendId,userId);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        // 判断是否已经是非好友
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
}
