package wang.ismy.soc.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wang.ismy.soc.friend.pojo.Friend;
import wang.ismy.soc.friend.pojo.NoFriend;

/**
 * @author MY
 * @date 2019/11/17 21:45
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {

    /**
     * 根据用户及朋友ID查询
     * @param userId
     * @param friendId
     * @return
     */
    NoFriend findByUseridAndFriendid(String userId, String friendId);


}
