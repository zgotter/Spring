package kr.co.test.cli.scope;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello World!");

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        A a1 = context.getBean("A", A.class);
        A a2 = context.getBean("A", A.class);

        log.info("result : " + (a1 == a2)); // true (동일성 확인)
    }
}
