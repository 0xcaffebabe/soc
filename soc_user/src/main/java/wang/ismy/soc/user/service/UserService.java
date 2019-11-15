package wang.ismy.soc.user.service;


import entity.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import wang.ismy.soc.user.dao.UserDao;
import wang.ismy.soc.user.pojo.User;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserDao userDao;
    private RedisTemplate redisTemplate;
    private RabbitTemplate rabbitTemplate;
    private IdWorker idWorker;
    private BCryptPasswordEncoder passwordEncoder;

    public void sendSms(String phone) {
        String code = RandomStringUtils.randomNumeric(6);

        // 放入缓存
        redisTemplate.opsForValue().set("checkcode_" + phone, code, 6, TimeUnit.HOURS);
        // 发给用户
        rabbitTemplate.convertAndSend("sms", Map.of("phone", phone, "code", code));
        log.info("发送短信:{},{}", phone, code);

    }

    public Result register(String code, User user) {
        String s = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if (StringUtils.isEmpty(s)) {
            return Result.error("请先获取手机验证码");
        }

        if (!s.equals(code)) {
            return Result.error("验证码错误");
        }
        user.setId(idWorker.nextId() + "");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return Result.success("注册成功");
    }

    public User login(User user) {
        User result = userDao.findByMobile(user.getMobile());
        if (result != null && passwordEncoder.matches(user.getPassword(),result.getPassword())){
            return result;
        }
        return null;
    }
}
