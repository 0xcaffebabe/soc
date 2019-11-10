package wang.ismy.soc.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import wang.ismy.soc.article.pojo.Article;


/**
 * 文章数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE tb_article SET state = :state WHERE id = :id")
    void updateState(String id,String state);

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE tb_article SET thumbup = thumbup + 1 WHERE id = :id")
    void addThumbup(String id);
}
