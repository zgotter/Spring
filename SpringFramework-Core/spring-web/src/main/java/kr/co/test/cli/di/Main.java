package kr.co.test.cli.di;

public class Main {
    public static void main(String[] args) {

        boolean condition = true; // Main 에서 true, false를 제어하기 위해서는 A 클래스와 B 클래스의 코드 복잡성이 높아진다.
        // DI를 통해 객체 간의 높은 의존성을 디커플링하여 유지보수의 효율성을 높일 필요가 있다.
        A a = new A(condition);
        a.print();
    }
}
