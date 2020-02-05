package test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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

@EqualsAndHashCode
@AllArgsConstructor
class B {
    private int b1;
    private String b2;
}