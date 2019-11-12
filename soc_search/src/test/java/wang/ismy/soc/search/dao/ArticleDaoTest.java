package wang.ismy.soc.search.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDaoTest {

    @Autowired
    ArticleDao articleDao;

    @Test
    public void test(){
        articleDao.findAll().forEach(System.out::println);
    }
}