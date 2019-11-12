package wang.ismy.soc.search.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wang.ismy.soc.search.pojo.Article;

/**
 * @author MY
 * @date 2019/11/12 22:10
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
