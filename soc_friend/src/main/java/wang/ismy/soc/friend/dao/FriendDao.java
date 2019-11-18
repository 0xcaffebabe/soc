package wang.ismy.soc.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wang.ismy.soc.friend.pojo.Friend;

/**
 * @author MY
 * @date 2019/11/17 21:45
 */
public interface FriendDao extends JpaRepository<Friend,String> {

    /**
     * 根据用户及朋友ID查询
     * @param userId
     * @param friendId
     * @return
     */
    Friend findByUseridAndFriendid(String userId,String friendId);

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE tb_friend SET islike = :isLike WHERE userid = :userid AND friendid = :friendid")
    int updateIsLike(@Param("userid") String userid, @Param("friendid") String friendid,@Param("isLike") String isLike);


    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM tb_friend WHERE userid = ? AND friendId = ?")
    int deleteFriend(String userId, String friendId);
}
