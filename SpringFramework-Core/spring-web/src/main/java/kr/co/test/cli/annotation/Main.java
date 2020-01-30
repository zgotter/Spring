package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello World");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("kr.co.test.cli.annotation");
        context.refresh();
        B b = context.getBean(B.class);
        log.info("" + b);

        context.close();
    }
}

