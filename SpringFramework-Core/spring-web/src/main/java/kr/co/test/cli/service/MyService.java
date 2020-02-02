package kr.co.test.cli.service;

import kr.co.test.cli.validation.PersonForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;


@Service
@Slf4j
public class MyService {

    @Autowired
    private Validator validator;

    public void check() {
        Locale.setDefault(Locale.ENGLISH); // locale 설정을 영어로 바꾸면 에러 메세지가 영문으로 출력된다.
        PersonForm personForm = new PersonForm("  ", 120);
        Set<ConstraintViolation<PersonForm>> results = validator.validate(personForm);
        if (results.isEmpty()) { // validate 성공
            log.info("validate success");
        } else {
            log.error("validate fail");
            results.forEach(x -> {
                log.error(">> error message: " + x.getMessage());
            });
        }
    }

}
