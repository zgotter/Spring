package kr.co.test.cli.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        /*
        ApplicationContext context = new ClassPathXmlApplicationContext("_dao.xml");
        A a = context.getBean("a", A.class);
        a.print();
        */
        new A().print();
    }
}
