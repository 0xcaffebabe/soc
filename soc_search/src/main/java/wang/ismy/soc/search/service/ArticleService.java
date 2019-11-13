package wang.ismy.soc.search.service;

import entity.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utils.IdWorker;
import wang.ismy.soc.search.dao.ArticleIndexDao;
import wang.ismy.soc.search.pojo.ArticleIndex;

/**
 * @author MY
 * @date 2019/11/12 22:40
 */
@Service
@AllArgsConstructor
public class ArticleService {

    private ArticleIndexDao articleDao;
    private IdWorker idWorker;

    public void save(ArticleIndex article) {
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    public PageResult<ArticleIndex> find(String key, Integer page, Integer size) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Page<ArticleIndex> all = articleDao.findByTitleOrContentLike(key, key, PageRequest.of(page, size));
        PageResult<ArticleIndex> result = new PageResult<>();
        result.setTotal(all.getTotalElements());
        result.setData(all.getContent());
        return result;
    }
}
