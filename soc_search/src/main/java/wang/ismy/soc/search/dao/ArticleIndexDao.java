package wang.ismy.soc.search.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wang.ismy.soc.search.pojo.ArticleIndex;

/**
 * @author MY
 * @date 2019/11/12 22:10
 */
public interface ArticleIndexDao extends ElasticsearchRepository<ArticleIndex,String> {

    Page<ArticleIndex> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
