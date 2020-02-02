package kr.co.test.cli.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Person person = new Person("", 200);
        PersonValidator validator = new PersonValidator();
        if ( validator.supports(person.getClass()) ) {
            BindException error = new BindException(person, "person");
            validator.validate(person, error);

            log.error(">> " + error.hasErrors()); // 에러 존재 여부 확인
            log.error("" + error.getAllErrors()); // 에러 확인
        } else {
            log.error("invalid class");
        }
    }
}
