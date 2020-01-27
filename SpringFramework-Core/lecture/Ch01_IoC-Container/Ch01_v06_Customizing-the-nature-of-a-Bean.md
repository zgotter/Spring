# Ch01. IoC Container

## 1.6 Customizing the nature of a Bean

- Lifecycle Callbacks
  - bean lifecycle
- `ApplicationContextAware`, `BeanNameAware`
- 다른 `Aware` 인터페이스

<br>

### 1.6.1 Lifecycle Callbacks

#### 1.6.1.1 Lifecycle 관리 

- Spring에서 객체를 managing하는  프레임워크이기 때문에 다음 두 가지 행위를 할 수 있다.
  - 객체를 만든다.
  - 객체를 없앤다.
- 객체 생성 시 `new` 키워드를 사용하여 객체를 생성하기 때문에 `new` 키워드를 통해 객체를 만들기 전에 특정 Callback을 호출할 수 있다.
- `connection.close()` 와 같이 특정 bean을 close할 때에도 특정 bean에 대해서 close에 대한 Callback을 호출할 수 있다.
- Callback은 특정 함수로 지정된다.

<br>

#### 1.6.1.2 Callback 함수 구현 방법

1. `InitializingBean`, `DisposableBean` 인터페이스 구현
2. JSR-250의 `@PostConstruct` 또는 `@PreDestroy` annotation 사용
3. XML의 `init-method`, `destroy-method` 설정

<br>

#### 1.6.1.3 Initialization Callbacks

- bean이 만들어질 때 특정 callback 함수가실행되도록 설정

<br>

**1) `InitializingBean` 인터페이스 구현**

- `org.springframework.beans.factory.InitializingBean` 인터페이스
  - [API 문서](<https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/InitializingBean.html>)
  -  `afterPropertiesSet()` 이라는 하나의 메서드만 가지고 있는 인터페이스이다.

- `afterPropertiesSet()` 메서드

  - 특정 bean이 만들어진 다음 해당 메서드가 호출됨

  - 클래스에 `InitializingBean` 인터페이스를 구현한 뒤 해당 메서드를 overriding하여 사용

- `ConnectionFactory.java` 참고  

<br>

**2) XML의 `init-method` 설정**

- bean의 `init-method` 속성 값으로 bean 생성 시 실행할 메서드 지정

- 소스 코드 참고

<br>

#### 1.6.1.4 Destruction Callbacks

- bean이 종료될 때 동작할 callback들에 대한 설정

<br>

**1) `DisposableBean` 인터페이스 구현**

- `org.springframework.beans.factory.DisposableBean`
  - [API 문서](<https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/DisposableBean.html>)
  - `destroy()` 메서드 하나만 가지고 있는 인터페이스이다.
- Connection 객체 close를 하는 데 활용
- 소스 코드 참고

<br>

**2) XML의 `destroy-method` 설정**

- bean의 `destroy-method` 속성 값으로 bean 종료 시 실행할 메서드 지정
- 소스 코드 참고

<br>

#### 1.6.1.5 Default Initialization and Destroy Methods

- 매번 bean들에다가 `init-method` 또는 `destroy-method`를 매번 정의해주기 귀찮을 때는 `<beans>` 의 속성에다가 `default-init-method="init 메서드명"`, `default-destroy-method="destroy 메서드명"` 을 지정하면 모든 `<bean>` 의 생성 및 종료 시 마다 init method와 destroy method를 실행시켜준다.
- 소스 코드 참고
- bean과 bean 간의 의존성 설정과 bean들 사이에 만들어지는 순서 설정에도 해당 기능이 영향을 미치게 된다.