package wang.ismy.soc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.recruit.pojo.Gathering;
/**
 * 活动数据访问接口
 * @author Administrator
 *
 */
public interface GatheringDao extends JpaRepository<Gathering,String>,JpaSpecificationExecutor<Gathering>{
	
}
