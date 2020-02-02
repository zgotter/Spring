package kr.co.test.cli.res;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResourceExample.class);
        context.refresh();
        ResourceExample re = context.getBean(ResourceExample.class);
        re.print();
        context.close();
    }
}
