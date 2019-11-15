package wang.ismy.soc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import wang.ismy.soc.user.pojo.Admin;

/**
 * 管理员数据访问接口
 * @author Administrator
 *
 */
public interface AdminDao extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{


    /**
     * 根据登录名查询
     * @param loginName
     * @return
     */
    Admin findByLoginname(String loginName);
}
