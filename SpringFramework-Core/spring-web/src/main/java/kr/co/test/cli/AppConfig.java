package kr.co.test.cli;

import kr.co.test.cli.annotation.A;
import kr.co.test.cli.annotation.B;
import kr.co.test.cli.service.MyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.sql.Connection;

@Configuration
@Profile({"default", "dev"})
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public Connection connection(ConnectionFactory connectionFactory) {
        return connectionFactory.getConnection();
    }

    @Bean
    public Dao dao(Connection connection) {
        return new Dao(connection);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConnectionFactory connectionFactory(@Value("${jdbc.driver-class}") String driverClass,
                                               @Value("${jdbc.url}") String url,
                                               @Value("${jdbc.username}") String username,
                                               @Value("${jdbc.password}") String password) {
        return new ConnectionFactory(driverClass, url, username, password);
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @ Bean
    public MyService myService() {
        return new MyService();
    }
}
