package kr.co.test.cli.di;

public class A {
    // DI 원칙은 코드 레벨에서 객체간의 관계를 맺지 않는 것이다.
    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public void print() {
        System.out.println(b.calc());
    }
}
