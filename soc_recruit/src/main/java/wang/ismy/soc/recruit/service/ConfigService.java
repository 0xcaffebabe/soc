package wang.ismy.soc.recruit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import utils.IdWorker;
import wang.ismy.soc.recruit.dao.ConfigDao;
import wang.ismy.soc.recruit.pojo.Config;

/**
 * config服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ConfigService {

	@Autowired
	private ConfigDao configDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Config> findAll() {
		return configDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Config> findSearch(Map whereMap, int page, int size) {
		Specification<Config> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return configDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Config> findSearch(Map whereMap) {
		Specification<Config> specification = createSpecification(whereMap);
		return configDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param variable
	 * @return
	 */
	public Config findById(String variable) {
		return configDao.findById(variable).get();
	}

	/**
	 * 增加
	 * @param config
	 */
	public void add(Config config) {
		// config.setVariable( idWorker.nextId()+"" ); 雪花分布式ID生成器
		configDao.save(config);
	}

	/**
	 * 修改
	 * @param config
	 */
	public void update(Config config) {
		configDao.save(config);
	}

	/**
	 * 删除
	 * @param variable
	 */
	public void deleteById(String variable) {
		configDao.deleteById(variable);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Config> createSpecification(Map searchMap) {

		return new Specification<Config>() {

			@Override
			public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // variable
                if (searchMap.get("variable")!=null && !"".equals(searchMap.get("variable"))) {
                	predicateList.add(cb.like(root.get("variable").as(String.class), "%"+(String)searchMap.get("variable")+"%"));
                }
                // value
                if (searchMap.get("value")!=null && !"".equals(searchMap.get("value"))) {
                	predicateList.add(cb.like(root.get("value").as(String.class), "%"+(String)searchMap.get("value")+"%"));
                }
                // set_by
                if (searchMap.get("setBy")!=null && !"".equals(searchMap.get("setBy"))) {
                	predicateList.add(cb.like(root.get("setBy").as(String.class), "%"+(String)searchMap.get("setBy")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
