package kr.co.test.cli.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // DI 원칙을 이용해 IoC Container가 만들어 졌고, Container를 spring을 이용해 사용
        ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
        A a = context.getBean("a", A.class);
        a.print();
    }
}
