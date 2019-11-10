package wang.ismy.soc.base.service;

import entity.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.IdWorker;
import wang.ismy.soc.base.dao.LabelRepository;
import wang.ismy.soc.base.pojo.Label;

import javax.persistence.criteria.Predicate;

import javax.transaction.Transactional;
import java.util.ArrayList;

import java.util.List;

/**
 * @author MY
 * @date 2019/11/10 12:16
 */
@Service
@Transactional(rollbackOn = Exception.class)
@AllArgsConstructor
public class LabelService {

    private LabelRepository labelRepository;
    private IdWorker idWorker;

    public List<Label> findList() {
        return labelRepository.findAll();
    }

    public Label findById(String id) {
        return labelRepository.findById(id).orElse(null);
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelRepository.save(label);
    }

    public void update(Label label) {
        labelRepository.save(label);
    }

    public void delete(String id) {
        labelRepository.deleteById(id);
    }

    public List<Label> search(Label condition) {
        return labelRepository.findAll(getLabelSpecification(condition));
    }

    public PageResult<Label> search(Label condition, Integer page, Integer size) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Page<Label> all = labelRepository.findAll(getLabelSpecification(condition), PageRequest.of(page, size));
        PageResult<Label> result = new PageResult<>();
        result.setTotal(all.getTotalElements());
        result.setData(all.getContent());
        return result;
    }

    private Specification<Label> getLabelSpecification(Label condition) {
        return (Specification<Label>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(condition.getLabelname())) {
                Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + condition.getLabelname() + "%");
                predicateList.add(predicate);
            }

            if (!StringUtils.isEmpty(condition.getState())) {
                Predicate predicate = cb.equal(root.get("state").as(String.class), condition.getState());
                predicateList.add(predicate);
            }

            return cb.and(predicateList.toArray(Predicate[]::new));
        };
    }


}
