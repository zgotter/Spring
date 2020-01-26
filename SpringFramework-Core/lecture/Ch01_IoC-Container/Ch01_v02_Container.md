# Ch01. IoC Container

## 1.2 Container Overview

- `org.springframework.context.ApplicationContext` 인터페이스가 가장 핵심적인 부분이다.

<img src="https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/images/container-magic.png" />

- Spring Container를 통해서 `POJO(Plan Old Java Object)`  로 만들어진 나의 Business Object를 Spring에서 제공하는 `Configuration Metadata`에 맞춰서 설정 정보를 만들어주면 스프링이 해당 객체들을 자동으로 만들어주고 사용할 수 있게끔 해준다.

<br>

### 1.2.1 Configuration Metadata

- 스프링이 제공하는 여러 가지 설정 방법 중 `XML` 과 `Annotation` 을 통한 방법들이 있다.

<br>

#### 1.2.1.1 XML 기반 설정 방법

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

<br>

### 1.2.2 Instantiating a Container

- 컨테이너 사용

- `spring-web` 실습 : CLI 프로그램 리펙토링

- maven central 에서 dependency 검색
  - 검색어 : org.springframework spring-core
