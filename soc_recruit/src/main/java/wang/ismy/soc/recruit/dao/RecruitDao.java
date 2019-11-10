package wang.ismy.soc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.recruit.pojo.Recruit;

import java.util.List;

/**
 * 职位数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 根据状态查询(前6个)
     * @param state
     * @return 职位列表
     */
    List<Recruit> findTop6ByStateOrderByCreatetime(String state);


    /**
     * 根据状态取反查询（前6个）
     * @param state
     * @return 职位列表
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetime(String state);
}
