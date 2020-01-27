package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello World");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        context.close();
    }
}
