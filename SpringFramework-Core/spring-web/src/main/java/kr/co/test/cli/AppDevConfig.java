package kr.co.test.cli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AppDevConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConnectionFactory connectionFactory() {
        return new ConnectionFactory("org.h2.Driver", "jdbc:h2:mem:file:~/test", "sa", "");
    }
}
