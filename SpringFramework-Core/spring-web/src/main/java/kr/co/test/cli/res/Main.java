package kr.co.test.cli.res;

import org.springframework.core.io.UrlResource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // UrlResource는 절대경로를 명시해줘야 한다.
        UrlResource resource = new UrlResource("file:C:\\shkim\\intellij\\Spring\\SpringFramework-Core\\spring-web\\src\\main\\resources\\dao.xml");
        System.out.println(resource.exists());
    }
}
