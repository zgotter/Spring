package kr.co.test.cli.res;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("http://spring.io/img/homepage/icon-spring-cloud.svg");
        String daoStr = new String(resource.getInputStream().readAllBytes());
        System.out.println(daoStr);
        context.close();
    }
}
