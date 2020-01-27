# Ch01. IoC Container

# v07. Annotation-based Container Configuration

- Java 파일에서 Annotation을 추가함으로써 Spring Container 설정을 추가하는 방법에 대해 학습

<br>

## 7.1 Spring 설정 시 Annotation 방식이 XML 방식 보다 좋은가?

- 상황에 따라 다르다

<br>

### 7.1.1 XML 방식

- 장점
  - 소스 코드 외에 XML 만 보고 Spring의 설정을 볼 수 있다.
  - 설정과 소스 코드의 분리
  - POJOs
- 단점
  - XML에 모든 설정을 넣다보니 XML의 덩어리가 너무 커진다. (XML Hell)
  - XML의 관리에 대한 부담감이 커짐

<br>

### 7.1.2 Annotation 방식

- 장점
  - `@Required`, `@Autowired` 등의 Annotation이 붙은 Java Annotation figure 을 통해 설정
- 단점
  - 소스 코드 안에 설정과 관련된 Java 코드가 추가됨
  - 설정과 소스 코드 분리의 한계
  - 완벽하게 POJOs 라고 볼 수 없음

<br>

## 7.2 Annotation 설정 방식

### 7.2.1 `<beans>` 에 `context` 설정

- XML 설정 방식의 `<beans>` 속성 중에는 `context` 라는 namespace가  존재하지 않는다.

- 아래와 같이 `context` 가 포함된 namespace로 이루어진 `<beans>` 사용

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          https://www.springframework.org/schema/context/spring-context.xsd">
  ```

- 다음 코드를 xml 파일에 추가하면 annotation 방식의 spring 설정이 가능해진다.

  ```xml
  <context:annotation-config/>
  ```

<br>

### 7.2.2 `@Required`

- 자주 사용되지 않으므로 생략

<br>

### 7.2.3 `@Autowired`

- bean과 bean간의 관계에서 spring container가 자동으로 관계를 연결시켜주는 기능을 한다.
  (JSR 330의 `@Inject` 와 유사한 기능을 하는 annotation)
- 관계를 연결시켜주는 방법
  - bean의 이름(`name`)을 사용
  - bean의 유형(`type`)을 사용
- `annotation` 패키지 하위의 소스 코드 참고