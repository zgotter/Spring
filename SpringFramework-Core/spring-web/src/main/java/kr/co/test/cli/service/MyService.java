package kr.co.test.cli.service;

import kr.co.test.cli.validation.PersonForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Service
@Slf4j
public class MyService {

    @Autowired
    private Validator validator;

    public void check() {
        PersonForm personForm = new PersonForm("shkim", -1);
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
