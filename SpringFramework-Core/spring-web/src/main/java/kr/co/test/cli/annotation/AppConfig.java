package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration // Spring 설정과 관련된 bean이라고 알려주는 annotation
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean // XML에서 Bean을 설정하는 것 대신 Java annotation을 통해 bean을 설정할 수 있게 해주는 annotation
    @Qualifier("b1")
    public B appBeanB1() {
        return new B(); // B type의 bean 추가
    }

    @Bean
    @Qualifier("b2")
    public B appBeanB2() {
        return new B();
    }

}
