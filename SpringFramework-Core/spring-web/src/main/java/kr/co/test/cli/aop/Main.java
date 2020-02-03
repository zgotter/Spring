package kr.co.test.cli.aop;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Service service = context.getBean(Service.class);
        service.log();
        context.close();
    }
}