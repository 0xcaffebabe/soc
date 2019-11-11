package wang.ismy.soc.spt.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MY
 * @date 2019/11/11 21:11
 */
@Data
public class Spit implements Serializable {

    @Id
    private String _id;

    private String content;

    private Date publishtime;

    private String userId;

    private String nickname;

    private Integer visits;

    private Integer thumbup;

    private Integer share;

    private Integer comment;

    private String state;

    private String parentid;
}
