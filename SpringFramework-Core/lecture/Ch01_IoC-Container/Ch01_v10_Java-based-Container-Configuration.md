# Ch01. IoC Container

# v10. Java-based Container Configuration

## 10.1 Basic Concepts: `@Bean` and `@Configuration`

- 기존의 XML 설정 대신 `@Configuration` annotation을 클래스 레벨에 붙이고, `@Bean` annotation을 함수(메서드) 레벨에 붙여서 Bean 설정을 만들 수 있다.

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean
      public MyService myService() {
          return new MyServiceImpl();
      }
  }
  ```

<br>

## 10.2 `AnnotationConfigApplicationContext`

- Instantiating the Spring Container by Using `AnnotationConfigApplicationContext`

  ```java
  public static void main(String[] args) {
      ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
      MyService myService = ctx.getBean(MyService.class);
      myService.doStuff();
  }
  ```

  ```java
  public static void main(String[] args) {
      ApplicationContext ctx = new AnnotationConfigApplicationContext(MyServiceImpl.class, Dependency1.class, Dependency2.class);
      MyService myService = ctx.getBean(MyService.class);
      myService.doStuff();
  }
  ```

<br>

### 10.2.1 `register(Class<?>..)`

- `AnnotationConfigApplicationContext` 생성자에 아무런 값을 넣어주지 않는다.

- 그런 다음 appConfig 처럼 Configuration 관련 정보가 들어가 있는 클래스들을 `register()` 메서드를 이용해 등록한다.

- 그리고 `refresh()` 메서드를 호출해주는 방식으로 환경 설정이 가능하다.

  ```java
  public static void main(String[] args) {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.register(AppConfig.class, OtherConfig.class);
      ctx.register(AdditionalConfig.class);
      ctx.refresh();
      MyService myService = ctx.getBean(MyService.class);
      myService.doStuff();
  }
  ```

<br>

### 10.2.2 `scan(String..)`

- `scan()` 메서드를 활용한 Component Scaning 사용

  ```java
  @Configuration
  @ComponentScan(basePackages = "com.acme") 
  public class AppConfig  {
      ...
  }
  ```

  ```java
  public static void main(String[] args) {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
      ctx.scan("com.acme");
      ctx.refresh();
      MyService myService = ctx.getBean(MyService.class);
  }
  ```

- XML 설정에 아래 내용을 추가해야 사용할 수 있다.

  ```xml
  <beans>
      <context:component-scan base-package="com.acme"/>
  </beans>
  ```

<br>

## 10.3 Using the `@Bean` Annotation

- `@Bean` annotation을 사용할 때에는 다음과 같은 특징이 있다.

<br>

### 10.3.1 Declaring a Bean

- 함수명이 bean의 `id` 값으로 들어간다.

  - `transferService`

- 함수의 리턴 타입이 `class` 값으로 들어간다.

  - `TransferServiceImpl`

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean
      public TransferServiceImpl transferService() {
          return new TransferServiceImpl();
      }
  }
  ```

  ```xml
  <beans>
      <bean id="transferService" class="com.acme.TransferServiceImpl"/>
  </beans>
  ```

- Spring에서는 직접 구현 클래스보다 인터페이스를 통해서 사용하는 편이다.

- 그래서 구현 클래스로 리턴을 하지만 실제 리턴 타입은 인터페이스로 지정하면 인터페이스로 타입이 등록되기 때문에 좀 더 유연한 설계가 가능하다.

  - 리턴값 : `TransferServiceImpl()`
  - 리턴 타입 : `TransferService`

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean
      public TransferService transferService() {
          return new TransferServiceImpl();
      }
  }
  ```

<br>

### 10.3.2 Bean Dependencies

- `@Bean` annotation이 지정된 함수에서는 굳이 `@Autowired` annotation을 붙이지 않고 Argument로 넣어주게 되면 필요한 의존성이 자동으로 주입된다.

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean
      public TransferService transferService(AccountRepository accountRepository) {
          return new TransferServiceImpl(accountRepository);
      }
  }
  ```

  