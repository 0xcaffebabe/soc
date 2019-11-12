package wang.ismy.soc.search.service;

import entity.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utils.IdWorker;
import wang.ismy.soc.search.dao.ArticleDao;
import wang.ismy.soc.search.pojo.Article;

/**
 * @author MY
 * @date 2019/11/12 22:40
 */
@Service
@AllArgsConstructor
public class ArticleService {

    private ArticleDao articleDao;
    private IdWorker idWorker;

    public void save(Article article) {
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    public PageResult<Article> find(String key, Integer page, Integer size) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Page<Article> all = articleDao.findByTitleOrContentLike(key, key, PageRequest.of(page, size));
        PageResult<Article> result = new PageResult<>();
        result.setTotal(all.getTotalElements());
        result.setData(all.getContent());
        return result;
    }
}
