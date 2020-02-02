package kr.co.test.cli.res;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream("dao.xml");
        byte[] bytes = is.readAllBytes();
        String daoStr = new String(bytes);
        System.out.println(daoStr);
    }
} 
