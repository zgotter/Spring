package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Dao { // DAO: Data Access Object

    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection; // Connection을 DI로 주입받는다.
    }

    void init() {
        log.info("dao init");
    }

    void destroy() {
        log.info("dao destroy");
    }

    public void run() throws SQLException {

        var statement = connection.createStatement();
        connection.setAutoCommit(false);
        statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
        try {
            statement.executeUpdate("insert into member (username, password) values ('shkim', '1234')");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        var resultSet = statement.executeQuery("select id, username, password from member");
        while (resultSet.next()) {
            var member = new Member(resultSet);
            log.info(member.toString());
        }

    }

}
