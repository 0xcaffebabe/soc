package wang.ismy.soc.spt.service;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.IdWorker;
import wang.ismy.soc.spt.dao.SpitDao;
import wang.ismy.soc.spt.pojo.Spit;

import java.util.Date;
import java.util.List;

/**
 * @author MY
 * @date 2019/11/11 21:14
 */
@Service
@AllArgsConstructor
public class SpitService {

    private SpitDao spitDao;
    private IdWorker idWorker;
    private MongoTemplate mongoTemplate;
    private RedisTemplate redisTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).orElse(null);
    }

    /**
     * 吐槽发布
     *
     * @param spit
     */
    public void save(Spit spit) {

        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");

        if (!StringUtils.isEmpty(spit.getParentid())) {
            // 更新父吐槽回复数
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void delete(String id) {
        spitDao.deleteById(id);
    }

    public PageResult<Spit> findByParentid(String parentId, Integer page, Integer size) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Page<Spit> all = spitDao.findByParentid(parentId, PageRequest.of(page, size));
        PageResult<Spit> result = new PageResult<>();
        result.setTotal(all.getTotalElements());
        result.setData(all.getContent());
        return result;
    }

    public Result thumbup(String sid) {
        // mock user id
        String userId = "0xcaffebabe";
        String key = "thumbup_" + userId + "_" + sid;
        if (redisTemplate.opsForValue().get(key) != null) {
            return new Result(false, StatusCode.ERROR, "不能重复点赞");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(sid));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
        redisTemplate.opsForValue().set(key, true);
        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
