package wang.ismy.soc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.recruit.pojo.City;
/**
 * 城市数据访问接口
 * @author Administrator
 *
 */
public interface CityDao extends JpaRepository<City,String>,JpaSpecificationExecutor<City>{
	
}
