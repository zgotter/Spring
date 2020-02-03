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

    public void insert() throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate("insert into member (username, password) values ('shkim', '1234')");
    }

    public void print() throws SQLException {
        var statement = connection.createStatement();
        var resultSet = statement.executeQuery("select id, username, password from member");
        while (resultSet.next()) {
            var member = new Member(resultSet);
            log.info(member.toString());
        }

    }

}
