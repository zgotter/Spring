package kr.co.test.cli;

import lombok.extern.slf4j.Slf4j;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Dao { // DAO: Data Access Object

    public static Dao createDao() {
        return new Dao();
    }

    public void run() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        var url = "jdbc:h2:mem:test;MODE=MySQL";

        try ( var connection = DriverManager.getConnection(url, "sa", "");
              var statement = connection.createStatement() ) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
