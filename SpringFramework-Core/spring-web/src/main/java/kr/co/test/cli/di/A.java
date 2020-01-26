package kr.co.test.cli.di;

public class A {
    // A 클래스는 B 클래스에 의존성이 있다.
    private B b;

    public A(boolean condition) {
        this.b = new B(condition); // B 클래스를 수정하기 위해서 A 클래스의 코드 레벨을 변경해야 한다. (A와 B의 의존성 때문
    }

    public void print() {
        System.out.println(b.calc());
    }
}
