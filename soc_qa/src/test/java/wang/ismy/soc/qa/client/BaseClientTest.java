package wang.ismy.soc.qa.client;

import entity.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseClientTest {

    @Autowired
    BaseClient baseClient;

    @Test
    public void test(){
        Result result = baseClient.findById("1");
        System.out.println(result.getData());
    }
}