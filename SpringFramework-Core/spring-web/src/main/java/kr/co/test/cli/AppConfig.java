package kr.co.test.cli;

import kr.co.test.cli.annotation.A;
import kr.co.test.cli.annotation.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;

@Configuration
@Profile("default | dev")
public class AppConfig {
    @Bean
    public B b() {
        return new B();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public A a(B b) { // 의존성 주입
        return new A(b);
    }

    @Bean
    public Connection connection(ConnectionFactory connectionFactory) {
        return connectionFactory.getConnection();
    }

    @Bean
    public Dao dao(Connection connection) {
        return new Dao(connection);
    }
}
