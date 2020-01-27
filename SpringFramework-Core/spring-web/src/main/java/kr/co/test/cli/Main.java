package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        log.info("Hello World!");
        // 객체 close를 위해 ApplicationContext -> ConfigurableApplicationContext로 변경 (ApplicationContext 보다 더 많은 메서드를 포함하고 있기 때문)
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        ConnectionFactory factory = context.getBean(ConnectionFactory.class);
        Connection connection = factory.getConnection();
        log.info("" + (connection != null)); // true 객체가 만들어진 것 확인
        context.close();
        /*
            close() 메서드를 호출함으로써 IoC 컨테이너가 자신들이 가지고 있는 Bean들에 대해서 destroy 메서드가 있는 지
            확인해보고 있다면 destroy() 메서드를 실행하고 bean들에 대한 destruction 실시
         */
    }
}