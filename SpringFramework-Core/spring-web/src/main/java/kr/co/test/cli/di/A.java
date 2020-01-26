package kr.co.test.cli.di;

public class A {
    private B b = new B();

    public void print() {
        b.print();
    }
    /*
    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public void print() {
        System.out.println(b.calc());
    }
    */
}
