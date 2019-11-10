package wang.ismy.soc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.recruit.pojo.Config;
/**
 * config数据访问接口
 * @author Administrator
 *
 */
public interface ConfigDao extends JpaRepository<Config,String>,JpaSpecificationExecutor<Config>{
	
}
