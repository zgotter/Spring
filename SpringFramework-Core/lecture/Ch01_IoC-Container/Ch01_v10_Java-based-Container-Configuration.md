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

- XML 설정에 아래 내용을 추가해야 사용할 수 있다.

  ```xml
  <beans>
      <context:component-scan base-package="com.acme"/>
  </beans>
  ```

  

