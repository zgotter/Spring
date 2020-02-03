package kr.co.test.cli.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class Main {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());
        System.out.println(">>>");
        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();
        System.out.println(">>>");
    }
}

class RetryAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Before");
        Object proceed = methodInvocation.proceed(); // Plain Object를 실행시키기 위해선 methodInvocation.proceed() 를 호출해야 한다.
        System.out.println("After");
        return proceed;
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