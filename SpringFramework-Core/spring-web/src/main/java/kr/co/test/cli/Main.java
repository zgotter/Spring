package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@Slf4j
class Main {

     public static void main(String[] args) throws SQLException {
        log.info("Hello World!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class, AppDefaultConfig.class, AppDevConfig.class);
        context.refresh();
        Dao dao = context.getBean(Dao.class);
        dao.run();
        context.close();
    }
}