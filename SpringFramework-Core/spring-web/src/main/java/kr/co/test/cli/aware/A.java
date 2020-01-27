package kr.co.test.cli.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public class A implements ApplicationContextAware {

    /*
        특정 Bean에서 ApplicationContext를 사용하고 싶을 때 ApplicationContextAware 인터페이스를 구현하고
        setApplicationContext 메서드를 overriding 하여 사용 가능
     */

    private ApplicationContext applicationContext;

    void init() {
        log.error(">> " + applicationContext); // null 여부 체크 ->
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
