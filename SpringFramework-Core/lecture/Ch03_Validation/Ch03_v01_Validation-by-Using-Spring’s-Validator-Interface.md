# Ch03. Validation

# v01. Validation by Using Spring’s Validator Interface

 ## 1.1 JSR-303 / JSR-349 Bean Validation

- Spring에서는 "Bean Validation 1.0 (JSR-303)"과 "Bean Validation 1.1 (JSR-349)"를 지원한다.

<br>

### 1.1.1 JSR-349

- [JSR-349](https://beanvalidation.org/1.1/)
- Bean Validation 1.1 은 스펙(spec)이다.
- 자바쪽에서 스펙만 정의해놓은 것이다.
- 벤더들에서 JSR-349을 구현한 구현체들은 아래 2가지가 있다.
  - Hibernate Validator
  - Apache BVal
- 이 중 Spring에서는 Hibernate Validator를 많이 사용한다.

<br>

## 1.2 Spring Validator Interface

- JSR-303, JSR-349 이외에 Spring Validator가 별도로 존재한다.

<br>

- 아래와 같이 `Person` 클래스가 있고 각각의 필드들이 존재한다.

- 이 필드들에 데이터가 제대로 들어갔는 지 validation할 필요가 있다.

  ```java
  public class Person {
  
      private String name;
      private int age;
  
      // the usual getters and setters...
  }
  ```

- Validation을 하기위해서는 `Validator` Interface를 구현한 클래스를 만들어야 한다.

  ```java
  public class PersonValidator implements Validator {
  
      /**
       * This Validator validates only Person instances
       */
      // supports() : 해당 클래스가 Person인 지 체크
      public boolean supports(Class clazz) {
          return Person.class.equals(clazz);
      }
  
      // validate() : validate하는 로직
      public void validate(Object obj, Errors e) {
          // obj가 Null이면 reject하면서 에러 코드를 던짐
          ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
          Person p = (Person) obj;
          // age의 범위를 validate
          if (p.getAge() < 0) {
              e.rejectValue("age", "negativevalue");
          } else if (p.getAge() > 110) {
              e.rejectValue("age", "too.darn.old");
          }
      }
  }
  ```

- 좀 더 복잡한 로직의 validate도 할 수 있다.

  ```java
  public class CustomerValidator implements Validator {
  
      private final Validator addressValidator;
  
      public CustomerValidator(Validator addressValidator) {
          if (addressValidator == null) {
              throw new IllegalArgumentException("The supplied [Validator] is " +
                  "required and must not be null.");
          }
          if (!addressValidator.supports(Address.class)) {
              throw new IllegalArgumentException("The supplied [Validator] must " +
                  "support the validation of [Address] instances.");
          }
          this.addressValidator = addressValidator;
      }
  
      /**
       * This Validator validates Customer instances, and any subclasses of Customer too
       */
      public boolean supports(Class clazz) {
          return Customer.class.isAssignableFrom(clazz);
      }
  
      public void validate(Object target, Errors errors) {
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
          Customer customer = (Customer) target;
          try {
              errors.pushNestedPath("address");
              ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
          } finally {
              errors.popNestedPath();
          }
      }
  }
  ```

<br>

## 1.3 `Errors`

- 보통 Error를 다룰 때에는 `validate()` 메서드의 반환 값을 `boolean`으로 지정하여 validation이 제대로 됐는 지 확인하거나, Exception throws로 한다.
- 위 방법 대신, `validate()` 메서드의 인자로 `Errors`를 받고, 이 `Errors`의 값을 변경하는 방식으로 validate가 진행된다.
- [Replacing Throwing Exceptions with Notification in Validations](https://martinfowler.com/articles/replaceThrowWithNotification.html)
  - Exception을 throwing하는 것 보다 Validation을 Notification하는 것으로 바꾸는 것이 좋다. 

<br>

## 1.4 Spring Validator의 단점

- validate에 대한 비즈니스 로직을 하나하나 구현해줘야 한다.
- 간단한 비즈니스 로직들은 annotation을 통해서 validation 가능하다. (v03. Spring Validation 참고)