# Ch02. Spring Framework Core

- [Spring Framework Core](<https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/core.html#spring-core>)
  - IoC Container, Events, Resources, Validation, SpEL, AOP

<br>

# 1. IoC Container

## 1.1 Introduction to the Spring IoC Container and Beans

- 스프링은 IoC(Inversion of Control) 이라는 원칙을 구현한 프레임워크이다.
- 스프링 프레임워크는 `ApplicationContext` 라는 인터페이스를 통해 IoC를 구현하고 있다.

<br>

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

<br>

## 1.3 Bean Overview

- Spring IoC Container는 하나 이상의 Bean을 관리한다.
- Bean은 Configuration Metadata을 통해 만들어진 것으로서 `<bean/>` element로 설정할 수 있다.
- 설정 가능한 것들
  - `package`
  - `classname`
  - bean의 동작에 대한 설정 

<br>

### 1.3.1 Naming Beans

- bean들은 모두 하나 이상의 `id`를 가져야 한다.
- bean에 `id`를 지정하지 않아도 spring에서 자동적으로 `id`를 지정하게 된다.
- XML을 통한 설정에서는 `id`, `name` 이라는 속성(attribute)을 통해 bean을 설정할 수 있다.
  - `id` 속성 : 하나의 값만 존재해야 한다.
    - 같은 클래스라 하더라도 spring이 관리해야 하는 bean이 다를 수 있다.
  - `name` 속성 : 콤마(`,`), 세미콜론(`;`), 공백(` `) 을 이용해 여러 개의 이름을 지을 수 있다.  

- spring container가 bean을 만들 때 각각의 bean은 new를 통해 새롭게 만들어진 객체이기 때문에 해시값이 서로 달라야 한다. 
- 특정 bean에서 다른 bean을 참조할 때 `ref` element를 사용한다.

<br>

### 1.3.2 Instantiating Beans

- bean 인스턴스화 방법

  1. 생성자 이용 (Instantiation with a Constructor)

     - `<bean/>` 에 `id`와 `class` 만 지정

     ```xml
     <bean id="exampleBean" class="examples.ExampleBean" /> 
     ```

     - 생성자를 이용한 bean 생성 시, 지정한 클래스에 기본 생성자가 아닌 매개변수를 받는 생성자가 존재하면 에러가 발생한다. (기본 생성자가 만들어지지 않기 때문)
     - 이는 bean 설정으로 해결 가능하다.

  2. static 이용 (Instantiation with a Static Factory Method)

     - `factory-method` 속성(attribute)를 추가한다.

     ```xml
     <bean id="clientService"
           class="examples.ClientService"
           factory-method="createInstance" />
     ```

     ```java
     public class ClientService {
         private static ClientService clientService = new ClientService();
         private ClientService() {}
         
         public static ClientService createInstance() {
             return clientService; 
         }
     }
     ```

     

     - factory-method` 속성에 객체 생성에 관련된 메서드명을 입력한다.

  3. Factory이용 (Instantiation by Using an Instance Factory Method )

     - 2개의 bean이 필요

       - bean을 만들어준 class가 정의된 bean

         ```xml
         <bean id="serviceLocator" class="examples.DefaultServiceLocator"></bean>
         ```

       - 사용할 bean : 동작시킬 bean을 `factory-bean` 속성에 지정

         ```xml
         <bean id="clientService"
             factory-bean="serviceLocator"
             factory-method="createClientServiceInstance"/>
         ```

         