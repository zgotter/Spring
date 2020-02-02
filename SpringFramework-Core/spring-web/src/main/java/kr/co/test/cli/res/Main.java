package kr.co.test.cli.res;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("dao.xml");
        System.out.println(resource.exists()); // 파일이 있는 지 확인
        byte[] bytes = resource.getInputStream().readAllBytes();
        String daoStr = new String(bytes);
        System.out.println(daoStr);
    }
}
