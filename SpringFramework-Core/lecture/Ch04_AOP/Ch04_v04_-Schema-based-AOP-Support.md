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



