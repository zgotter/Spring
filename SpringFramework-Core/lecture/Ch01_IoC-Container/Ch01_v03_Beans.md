# Ch01. IoC Container

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

  1. **생성자 이용 (Instantiation with a Constructor)**

     - `<bean/>` 에 `id`와 `class` 만 지정

     ```xml
     <bean id="exampleBean" class="examples.ExampleBean" /> 
     ```

     - 생성자를 이용한 bean 생성 시, 지정한 클래스에 기본 생성자가 아닌 매개변수를 받는 생성자가 존재하면 에러가 발생한다. (기본 생성자가 만들어지지 않기 때문)
     - 이는 bean 설정으로 해결 가능하다.

  2. **static 이용 (Instantiation with a Static Factory Method)**

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

  3. **Factory이용 (Instantiation by Using an Instance Factory Method )**

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

<br>

## 1.4 Dependencies (의존성)

- 어플리케이션은 하나 이상의 객체로 이루어져 있다.
- 그 객체간의 함께 working을 하게 된다.
- 객체 간의 의존성 관계가 발생하게 된다.
- `kr.co.test.cli.di` 패키지의 클래스 예제 파일 참고

<br>

### 1.4.1 Dependency Injection (DI)

- DI를 이용해 Bean을 통해 객체 간의 관계를 설정하고 객체 간의 의존성을 디커플링할 수 있다.

- DI를 통해 테스트를 좀 더 수월하게 할 수 있다.
  - 인터페이스를 이용해 `stub` 이나 `mock` 을 만들 수 있다.

<br>

#### 1.4.1.1 생성자 기반 의존성 주입 (Constructor-based Dependency Injection)

- bean 설정 시, `constructor-arg` 태그를 이용하여 객체 간 의존성 주입
- 객체가 생성될 때, 한 번만 실행되기 때문에 객체 생성 시 필요한 코드를 넣고 싶을 때 사용
- 생성자 기반 의존성 주입 권장

<br>

#### 1.4.1.2 `Setter` 기반 의존성 주입 (Setter-based Dependency Injection)

- bean 설정 시, `property` 태그를 이용하여 `setter` 지정
- 코드가 실행되다가 객체의 dependency를 변경하고 싶을 때 Runtime의 어느 시점에서든지 관계를 재설정할 수 있다.
- 하지만, 객체 간의 관계는 객체 생성 시 정의하고 이후에는 변경을 안 하는 것이 복잡도를 감소시킨다.

<br>

#### 1.4.1.3 의존성 처리 프로세스 (Dependency Resolution Process)

- `ApplicationContext`가 XML, Java Code, Annotation을 통해 Configuration Metadata를 읽어서 Beans에 대한 설정을 확인
- 각각의 Bean들에 대해 properties, constructor(생성자), factory method를 통해 Bean들을 제공한다.

<br>

#### 1.4.1.4 상호 참조 (Circular dependencies)

- A 클래스가 B 클래스에 의존성이 있고, B 클래스가 A 클래스에 의존성이 있을 때, Spring Framework는 서로 간의 상호 참조가 가능하기 때문에 서로 만들 지 못하는 상황이 발생하는 데 이를 상호 참조라고 한다.

<br>

### 1.4.2 의존성 및 설정 (Dependencies and Configuration in Detail)

