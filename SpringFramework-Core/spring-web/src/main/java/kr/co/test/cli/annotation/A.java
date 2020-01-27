package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@Slf4j
public class A {
    @Autowired private B b;
    @Autowired private ApplicationContext context;

    @PostConstruct
    void init() {
        log.info("" + context);
    }

}
