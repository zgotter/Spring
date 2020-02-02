# Ch03. Validation

# v03. Spring Validation

- Spring Validation은 JSR-303 Bean Validation API를 완전히 지원한다.

<br>

## 3.1 Overview of the JSR-303 Bean Validation API

- Spring Validator와 달리 `@Notnull`, `@Size`, `@Min` annotation을 통해서 validation을 할 수 있다.

- JSR-303 Dependency를 추가해야 위의 annotation을 사용할 수 있다.

  - [maven Central : javax.validation](https://search.maven.org/artifact/javax.validation/validation-api/2.0.1.Final/jar)

  ```java
  public class PersonForm {
      private String name;
      private int age;
  }
  ```

  ```java
  public class PersonForm {
  
      @NotNull
      @Size(max=64)
      private String name;
  
      @Min(0)
      private int age;
  }
  ```

<br>

## 3.2 Configuring a Bean Validation Provider

- Spring에서 제공하는 JSR-303에 관련된 validate 기능을 사용하기 위해서는 "Bean Validation Provider"를 추가해줘야 한다.

  - XML 방식

    ```java
    <bean id="validator"
        class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"/>
    ```

  - Annotation 방식

    ```java
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
    ```

<br>

### 3.2.1 Injecting a Validator

- 특정 서비스에서 validator를 inject해줘야 한다.

- `hibernate.validator`의 Dependency를 추가해줘야 한다.
  - [maven search : hibernate validator 6.0.15](https://search.maven.org/artifact/org.hibernate/hibernate-validator/6.0.15.Final/jar)
  - 해당 dependency를 추가하면 JSR-303 Dependency가 함께 추가되므로 별도로 의존성을 추가하지 않아도 된다.

- `javax.validation.Validator` (JSR-303)

  ```java
  import javax.validation.Validator;
  
  @Service
  public class MyService {
  
      @Autowired
      private Validator validator;
  }
  ```

- `org.springframework.validation.Validator`

  ```java
  import org.springframework.validation.Validator;
  
  @Service
  public class MyService {
  
      @Autowired
      private Validator validator;
  }
  ```

  