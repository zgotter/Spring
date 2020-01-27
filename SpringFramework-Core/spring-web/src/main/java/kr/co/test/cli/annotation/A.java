package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Slf4j
public class A {
    //@Autowired @Qualifier("b2") private B b;
    @Resource(name = "appBeanB1") private B b;
    @Autowired private ApplicationContext context;
    @Value("${catalog.name}") String catalogName;
    @Value("${catalog.name.unknown}") String catalogNameUnknown;
    @Value("#{systemProperties}") Map properties;
    @Value("#{systemProperties['java.home']}") String property;
    @Value("#{systemProperties['hello']}") String property2;

    @PostConstruct
    void init() {
        log.info("" + context);
    }

}
