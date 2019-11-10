package wang.ismy.soc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 企业数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    /**
     * 查询热门企业
     * @param isHot 是否热门
     * @return 热门企业列表
     */
    List<Enterprise> findByIshot(String isHot);
}
