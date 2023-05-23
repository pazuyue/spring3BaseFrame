package com.example.springdemo;

import com.alibaba.fastjson2.JSON;
import com.example.springdemo.Entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringDemoApplication.class })
public class AbcLogbackDemoApplicationTests {

    /** Logger实例 */
    static final Logger logger = LoggerFactory.getLogger(AbcLogbackDemoApplicationTests.class);

    /**
     * logback测试
     *
     * @date 2018年7月26日 下午4:12:56
     */
    @Test
    public void logbackTest() {
        logger.info("进入logbackTest方法了！");
        try {
            UserEntity user = new UserEntity(1L,"邓某",'男',"ces");
            logger.info("employee对象的相应参数为:" + JSON.toJSONString(user));
            Long id = user.getId();
            logger.info("向表中插入employee对象的数据后,自动获取到的主键为:" + id);
        } catch (Exception e) {
            logger.error("出错咯！错误信息:" + e.getMessage(), e.getCause());
            // 打印出错误堆栈信息
            e.printStackTrace();
        }
        logger.info("SpringBoot使用logback示例。");
        logger.info("logbackTest方法执行完毕!");
    }

}
