package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        log.info("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        ConnectionFactory factory = context.getBean(ConnectionFactory.class);
        Connection connection = factory.getConnection();
        log.info("" + (connection != null)); // true 객체가 만들어진 것 확인
    }
}