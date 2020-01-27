package kr.co.test.cli.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class A {
    private B b;

    @Autowired
    public void setB(B b) {
        this.b = b;
    }
}
