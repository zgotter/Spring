package test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class BeanScopeTest {
    @Test
    public void testIdentity() {
        // 동일성 (Identity) : 객체의 주소가 같다. -> (obj1 == obj2 == obj3) == true / hashcode()
        A a1 = new A();
        A a2 = a1;
        Assert.assertTrue(a1 == a2); // a1의 주소와 a2의 주소는 동일 (동일성)
    }

    @Test
    public void testEquals() {
        // 동등성 (equals) : 객체의 값이 같다. -> (obj1.equals(obj2)) == true        / equals()
        B b1 = new B(10, "Hello World");
        B b2 = new B(10, "Hello World");
        B b3 = new B(5, "Hello");

        Assert.assertTrue(b1.equals(b2));
        Assert.assertFalse(b1.equals(b3));
    }
}

class A {

}

class B {
    private int b1;
    private String b2;

    public B(int b1, String b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    // Alt + insert -> equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof B)) return false;
        B b = (B) o;
        return b1 == b.b1 &&
                Objects.equals(b2, b.b2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b1, b2);
    }
}