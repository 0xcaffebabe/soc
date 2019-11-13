package wang.ismy.soc.search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.ismy.soc.search.pojo.Article;
import wang.ismy.soc.search.pojo.ArticleIndex;

/**
 * @author MY
 * @date 2019/11/13 13:10
 */

public interface ArticleDao extends JpaRepository<Article,String> { }
