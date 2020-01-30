package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class A {
    @Autowired private B b;
    @Autowired private ApplicationContext context;
    @Value("#{systemProperties['hello']}") String property2;

    @PostConstruct
    void init() {
        log.info("A post construct");
    }

    @PreDestroy
    void destroy() {
        log.error("A pre destroy");
    }

}
