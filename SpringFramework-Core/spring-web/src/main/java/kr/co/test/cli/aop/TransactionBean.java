package kr.co.test.cli.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class TransactionBean {

    private Connection connection;

    public TransactionBean(Connection connection) {
        this.connection = connection;
    }

    public Object aroundTransaction(ProceedingJoinPoint pjp) throws SQLException {
        log.error(">>> before aop transaction");
        connection.setAutoCommit(false);
        try {
            Object proceed = pjp.proceed();
            log.error(">>> commit");
            connection.commit();

            return proceed;

        } catch (Throwable throwable) {
            log.error(">>> rollback");
            connection.rollback();
        }
        log.error(">>> after aop transaction");
        return null;
    }

}
