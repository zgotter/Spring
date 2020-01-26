package kr.co.test.cli.di;

public class Main {
    public static void main(String[] args) {

        boolean condition = true;

        // A 객체의 B의 의존성 주입 (Dependency Injection)
        // - Main 클래스에서 객체 간의 의존성을 주입한다.
        B b = new B(condition);
        A a = new A(b); // A 클래스는 B 클래스가 가지는 condition 값을 몰라도 된다.
        a.print();
    }
}
