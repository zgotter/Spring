package kr.co.test.cli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;

@Configuration
@Profile("default")
public class AppDefaultConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConnectionFactory connectionFactory() {
        return new ConnectionFactory("org.h2.Driver", "jdbc:h2:mem:test", "sa", "");
    }
}
