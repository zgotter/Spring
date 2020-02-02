package kr.co.test.cli.res;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C:\\shkim\\intellij\\Spring\\SpringFramework-Core\\spring-web\\src\\main\\resources\\bean.xml";
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        byte[] bytes = is.readAllBytes();
        String daoStr = new String(bytes);
        System.out.println(daoStr);
    }
}
