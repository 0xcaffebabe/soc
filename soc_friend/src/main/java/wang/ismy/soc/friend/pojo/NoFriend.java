package wang.ismy.soc.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MY
 * @date 2019/11/17 21:45
 */
@Entity
@Table(name = "tb_nofriend")
@Data
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

}
