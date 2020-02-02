package kr.co.test.cli.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class PersonForm {
    @NotNull
    @Size(max=3)
    private String name;

    @Min(0)
    private int age;
}
