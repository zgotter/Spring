package kr.co.test.cli.di;

public class B {
    A a = new A();

    public void print() {
        a.print();
    }
    /*
    boolean condition;

    public B(boolean condition) {
        this.condition = condition;
    }

    public int calc() {
        if (condition) {
            return 1;
        } else {
            return 0;
        }
    }
    */
}
