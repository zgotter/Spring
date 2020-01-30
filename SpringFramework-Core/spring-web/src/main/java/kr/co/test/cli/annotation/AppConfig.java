package kr.co.test.cli.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/*
    - @ComponentScan annotation 을 이용해 현재 경로의 패키지명을 basePackages로 등록
    - 그러면 해당 패키지 하위로 annotation component 또는 @Configuration이 붙은 annotation config를 가지고 Spring 설정을 할 수 있다.
    - @Configuration annotation 또한 내부적으로 @Component 를 가지고 있다.
 */
@Configuration
@ComponentScan(basePackages = "kr.co.test.cli.annotation")
public class AppConfig {

}
