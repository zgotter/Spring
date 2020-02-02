package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

@Slf4j
class Main {

     public static void main(String[] args) throws ClassNotFoundException, SQLException {
        log.info("Hello World!");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        Dao dao = context.getBean(Dao.class);
        dao.run();
        context.close();
    }
}