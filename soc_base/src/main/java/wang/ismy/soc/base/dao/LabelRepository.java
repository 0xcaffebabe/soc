package wang.ismy.soc.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import wang.ismy.soc.base.pojo.Label;

/**
 * @author MY
 * @date 2019/11/10 12:15
 */
public interface LabelRepository extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> { }
