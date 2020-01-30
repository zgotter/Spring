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
    private B b;

    public A(B b) {
        this.b = b;
    }

    @PostConstruct
    void init() {
        log.info("A post construct : " + b); // b가 null 인지 아닌 지 체크
    }

    @PreDestroy
    void destroy() {
        log.error("A pre destroy");
    }

}
