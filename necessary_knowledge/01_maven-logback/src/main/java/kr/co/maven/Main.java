package kr.co.maven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws ClassNotFoundException {

		logger.info("Hello World!!");

		/*
			# AutoCloseable 인터페이스 활용
			  - Java 1.7 부터 지원
			  - AutoCloseable 인터페이스를 상속 받은 객체들은 자동으로 close() 메서드를 호출하게 된다.
			  - try-with-resources 문을 이용하여 코드 리펙토링
		 */

		Class.forName("org.h2.Driver");
		var url = "jdbc:h2:mem:test;MODE=MySQL"; // Java 11 부터는 String 대신 var 를 사용하면 컴파일러가 추론하고 String 타입을 사용하게 된다.

		try ( var connection = DriverManager.getConnection(url,  "sa", "");
			  var statement = connection.createStatement() ) {

			connection.setAutoCommit(false); // autoCommit 해제

			// MEMBER 테이블 생성
			statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

			// 데이터 입력
			try {
				statement.executeUpdate("insert into member (username, password) values ('shkim', '1234')");
				connection.commit();
			} catch (SQLException e) {
				connection.rollback(); // SQLException 발생 시 rollback
			}

			// MEMBER 테이블 조회
			var resultSet = statement.executeQuery("select id, username, password from member");

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");

				Member member = new Member(id, username, password);
				logger.info(member.toString());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}