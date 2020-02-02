package kr.co.test.cli.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig {
    @Bean
    public B b() {
        return new B();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public A a(B b) { // 의존성 주입
        return new A(b);
    }
}