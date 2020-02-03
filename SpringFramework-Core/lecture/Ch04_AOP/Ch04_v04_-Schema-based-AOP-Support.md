# Ch04. AOP

# v04. Schema-based AOP Support

- Annotation으로 AOP를 이해하면 코드가 분산되어 이해가 어려워진다.
- 그러므로 Schema-based AOP Support를 먼저 살펴보고 `@AspectJ` annotation을 통한 AOP를 하는 방법을 학습한다.

<br>

## 4.1 Schema-based

- 스키마 베이스이기 때문에 AOP Schema를 추가해야 한다.
- [the AOP Schema](https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/core.html#xsd-schemas-aop)

- 다음 내용을 XML 설정 파일에 추가

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:aop="http://www.springframework.org/schema/aop"
      xsi:schemaLocation="
          http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
  
      <!-- bean definitions here -->
  
  </beans>
  ```

<br>

- 의존성(dependency) 추가

  - [maven central - aspepctjrt](https://search.maven.org/artifact/org.aspectj/aspectjrt/1.9.4/jar)

    ```xml
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>1.9.4</version>
    </dependency>
    ```

  - [maven central - aspectjweaver](https://search.maven.org/artifact/org.aspectj/aspectjweaver/1.9.4/jar)

    ```xml
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.4</version>
    </dependency>
    ```

<br>

## 4.2 Declaring an Aspect

- Aspect 선언

- Aspect는 공통적으로 동작해야 될 특정 코드들이다.

- 이것을 bean으로 설정이 가능하다.

- 아래와 같이 특정 bean을 만든 다음 `<aop:config>` 안에 `<aop:aspect>`를 이용해 Aspect를 만들 때 만들었던 특정 bean과 `ref`를 이용해 reference를 걸어준다.

  ```xml
  <aop:config>
      <aop:aspect id="myAspect" ref="aBean">
          ...
      </aop:aspect>
  </aop:config>
  
  <bean id="aBean" class="...">
      ...
  </bean>
  ```

<br>

## 4.3 Declaring a Pointcut

- Pointcut 선언

- `<aop:aspect>` 안에 다음 내용 추가

  ```xml
  <aop:pointcut id="businessService" expression="execution(* com.xyz.myapp.service.*.*(..))"/>
  ```

<br>

### 4.3.1 Pointcut 문법

- `expression` 속성의 값으로 정의되는 부분
- `execution(* com.xyz.myapp.service.*.*(..))`
  - `execution` : runtime을 execution 할 때
  - `com.xyz.myapp.service` : 특정 패키지에 있는 
  - `*` : 모든 클래스
  - `*` : 모든 메서드
  - `(..)` : 모든 Argument

<br>

## 4.4 Declaring Advice

- Advice 선언

<br>

### 4.4.1 Before Advice

- `pointcut-ref` 에 지정된 Pointcut이 동작하기 전에 동작하는 메서드 지정

- `<aop:aspect>` 내부에 다음 내용 추가

  ```xml
  <aop:before pointcut-ref="dataAccessOperation" method="doAccessCheck"/>
  ```

<br>

### 4.4.2 After Returning Advice

- `pointcut-ref`에 지정된 Pointcut이 동작을 완료한 다음 동작하는 메서드 지정

- `<aop:aspect>` 내부에 다음 내용 추가

  ```xml
  <aop:after-returning pointcut-ref="dataAccessOperation" method="doAccessCheck"/>
  ```

<br>

### 4.4.3 After Throwing Advice

- `pointcut-ref`에 지정된 Pointcut이 Exception을 발생시켰을 때 동작하는 메서드 지정

- `<aop:aspect>` 내부에 다음 내용 추가

  ```xml
  <aop:after-throwing pointcut-ref="dataAccessOperation" method="doRecoveryActions"/>
  ```

<br>

### 4.4.4 After (Finally) Advice

- `pointcut-ref`에 지정된 Pointcut이 동작을 완료하거나, Exception을 발생시켰을 때 동작하는 메서드 지정

- `<aop:aspect>` 내부에 다음 내용 추가

  ```xml
  <aop:after pointcut-ref="dataAccessOperation" method="doReleaseLock"/>
  ```

<br>

### 4.4.5 Around Advice

- 4.4.1 ~ 4.4.4 의 모든 기능을 수행할 수 있는 Advice

- `<aop:aspect>` 내부에 다음 내용 추가

  ```xml
  <aop:around pointcut-ref="businessService" method="doBasicProfiling"/>
  ```

  