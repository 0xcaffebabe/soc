package wang.ismy.soc.search.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wang.ismy.soc.search.dao.ArticleDao;
import wang.ismy.soc.search.dao.ArticleIndexDao;
import wang.ismy.soc.search.pojo.ArticleIndex;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MY
 * @date 2019/11/13 13:03
 */
@Component
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class DataTransferTask {

    private ArticleIndexDao articleIndexDao;
    private ArticleDao articleDao;

    @Scheduled(fixedDelay = 60*1000)
    @Transactional(rollbackOn = Exception.class)
    public void transfer(){
        log.info("索引转储");
        List<ArticleIndex> collect = articleDao.findAll().stream().map(a -> {
            ArticleIndex index = new ArticleIndex();
            BeanUtils.copyProperties(a, index);
            return index;
        }).collect(Collectors.toList());
        articleIndexDao.deleteAll();

        articleIndexDao.saveAll(collect);
    }
}
