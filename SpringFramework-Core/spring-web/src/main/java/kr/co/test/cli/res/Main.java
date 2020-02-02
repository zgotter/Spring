package kr.co.test.cli.res;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Resource resource = new FileSystemResource("C:\\shkim\\intellij\\Spring\\SpringFramework-Core\\spring-web\\src\\main\\resources\\dao.xml");
        System.out.println(resource.exists());
    }
}
