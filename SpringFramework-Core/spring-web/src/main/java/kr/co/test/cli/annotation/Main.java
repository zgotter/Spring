package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = Main.class, excludeFilters = @Filter(type= FilterType.REGEX, pattern = "kr.co.test.cli.annotation.B"))
public class Main {
    public static void main(String[] args) {
        log.info("Hello World");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Main.class); // 패키지 경로를 문자열보다 타입 값을 넣어주는 게 좋다.
        B b = context.getBean(B.class); // B 클래스가 excludeFilters로 지정됐기 때문에 b에 대한 bean이 생성되어 있지 않아 에러가 발생한다.
        log.info("" + b); // B 가 있는 지 확인

        context.close();
    }
}

