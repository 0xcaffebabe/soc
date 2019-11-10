package wang.ismy.soc.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import wang.ismy.soc.article.pojo.Column;


/**
 * 专栏数据访问接口
 * @author Administrator
 *
 */
public interface ColumnDao extends JpaRepository<Column,String>,JpaSpecificationExecutor<Column>{
	
}
