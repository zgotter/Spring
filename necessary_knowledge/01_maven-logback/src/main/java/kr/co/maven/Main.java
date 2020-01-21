package kr.co.maven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//System.out.println("Hello World!");
		logger.info("Hello World!!");

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			// 드라이버 로딩
			Class.forName("org.h2.Driver");

			// 접속 url
			//String url = "jdbc:h2:~/test;MODE=MySQL"; // MODE=MySQL : MySQL 호환모드 설정
			String url = "jdbc:h2:mem:test;MODE=MySQL"; // 메모리 모드 사용

			// Connection 객체 생성
			connection = DriverManager.getConnection(url, "sa", "");

			// statement 정의
			statement = connection.createStatement();

			// MEMBER 테이블 생성
			statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

			// 데이터 입력
			statement.executeUpdate("insert into member (username, password) values ('shkim', '1234')");

			// MEMBER 테이블 조회
			resultSet = statement.executeQuery("select id, username, password from member");

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");

				logger.info("id: " + id + ", username: " + username + ", password: " + password);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}