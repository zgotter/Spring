package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello World");
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("_dao.xml");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("kr.co.test.cli.annotation");
        context.close();
    }
}

