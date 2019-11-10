package wang.ismy.soc.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import wang.ismy.soc.qa.pojo.Problem;

import java.util.List;

/**
 * 问题数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(nativeQuery = true,value = "SELECT * FROM tb_problem INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE labelid = :labelId ORDER BY replytime DESC")
    Page<Problem> newList(String labelId, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM tb_problem INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE labelid = :labelId ORDER BY reply DESC")
    Page<Problem> hotList(String labelId, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM tb_problem INNER JOIN tb_pl ON tb_pl.problemid = tb_problem.id WHERE labelid = :labelId AND reply = 0 ORDER BY createtime DESC")
    Page<Problem> waitList(String labelId, Pageable pageable);
}
