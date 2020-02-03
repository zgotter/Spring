package kr.co.test.cli.aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AopBean {

    public void beforeLog() {
        log.error(">>> before aop log");
    }

    public void afterLog() {
        log.error(">>> after aop log");
    }

    public void afterReturningLog() {
        log.error(">>> after returning aop log");
    }

    public void afterThrowingLog() {
        log.error(">>> after throwing aop log");
    }

}
