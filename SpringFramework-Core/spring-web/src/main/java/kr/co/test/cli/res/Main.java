package kr.co.test.cli.res;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("dao.xml");
        String daoStr = new String(resource.getInputStream().readAllBytes());
        System.out.println(daoStr);
        context.close();
    }
}
