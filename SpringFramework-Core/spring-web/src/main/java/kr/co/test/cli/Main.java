package kr.co.test.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.DriverManager;
import java.sql.SQLException;

class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException {
        logger.info("Hello World!");

        ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        Dao dao = context.getBean("dao", Dao.class);
        Dao new_dao = context.getBean("new_dao", Dao.class);

        System.out.println(dao == new_dao); // 서로 다른 bean 객체로 생성됐기 때문에 해시값이 다르다.

        dao.run();
    }
}