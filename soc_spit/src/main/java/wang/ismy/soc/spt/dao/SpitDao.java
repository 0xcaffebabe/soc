package wang.ismy.soc.spt.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import wang.ismy.soc.spt.pojo.Spit;

/**
 * @author MY
 * @date 2019/11/11 21:13
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    Page<Spit> findByParentid(String parentId, Pageable pageable);
}
