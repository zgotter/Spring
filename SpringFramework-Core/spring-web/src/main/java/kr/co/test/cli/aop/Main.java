package kr.co.test.cli.aop;

public class Main {
    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        pojo.foo();
    }
}

interface Pojo {
    void foo();
}

class SimplePojo implements Pojo {

    public void foo() {
        System.out.println("run foo");
    }
}