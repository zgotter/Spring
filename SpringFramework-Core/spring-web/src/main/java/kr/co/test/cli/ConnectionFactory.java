package kr.co.test.cli;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionFactory {
    private String driverClass;
    private String url;
    private String user;
    private String password;
    @Getter private Connection connection = null;


    public ConnectionFactory(String driverClass, String url, String user, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection createConnection() throws SQLException {
        try {
            Class.forName(this.driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    public void init() throws Exception {
        log.info("init");
        this.connection = createConnection();
    }


    public void destroy() throws Exception {
        log.info("destroy");
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
