package kr.co.test.cli;

import kr.co.test.cli.config.AppConfig;
import kr.co.test.cli.controller.MemberController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@Slf4j
class Main {

     public static void main(String[] args) throws SQLException {
        log.info("Hello World!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        createTable(context.getBean(DataSource.class).getConnection());

         System.out.println("====================== 사용자의 username, password를 입력해주세요. ======================");

         Scanner scanner = new Scanner(System.in);
         System.out.println("username: ");
         String username = scanner.nextLine();
         System.out.println("password: ");
         String password = scanner.nextLine();

         MemberController controller = context.getBean(MemberController.class);
         controller.insert(username, password);
         controller.print();

         context.close();

     }

    public static void createTable(Connection connection) throws SQLException {
        connection.createStatement().execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
    }
}