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

<br>

### 10.3.3 Receiving Lifecycle Callbacks

- `@Bean` annotation에는 `initMethod` 또는 `destroyMethod`를 넣어줄 수 있다.

  ```java
  public class BeanOne {
  
      public void init() {
          // initialization logic
      }
  }
  
  public class BeanTwo {
  
      public void cleanup() {
          // destruction logic
      }
  }
  
  @Configuration
  public class AppConfig {
  
      @Bean(initMethod = "init") // initMethod 사용
      public BeanOne beanOne() {
          return new BeanOne();
      }
  
      @Bean(destroyMethod = "cleanup") // destroyMethod 사용
      public BeanTwo beanTwo() {
          return new BeanTwo();
      }
  }
  ```

- `@PostConstructor`와 `@PreDestroy` annotation을 사용하는 것보다 `@Bean`의 `initMethod`와 `destroyMethod`를 사용하는 것이 선호된다.

  - destroy 할 메서드 위에 `@PreDestroy` annotation을 지정하게 되면 해당 클래스의 설정 값이 들어가게 된다.
  - 가능하다면 특정 클래스에는 그 클래스에서 필요한 정보만 넣고 설정 값들은 외부에서 관리하는 것이 효율적이다.

- 또한, XML보다 `@Configuration`, `@Bean` annotation을 사용하여 환경 설정을 하는 것이 선호된다.

- 이를 통해 환경 설정에 사용되는 자바 클래스와 비즈니스 로직 구현에 사용되는 자바 클래스를 구분할 수 있다.

<br>

### 10.3.4 Using the `@Scope` Annotation

- `@Scope` annotation의 사용

  ```java
  @Configuration
  public class MyConfiguration {
  
      @Bean
      @Scope("prototype")
      public Encryptor encryptor() {
          // ...
      }
  }
  ```

<br>

### 10.3.5 Customizing Bean Naming

- 메서드명은 Bean의 `name`  속성 값으로 지정된다.

- `@Bean` annotation에서 다음과 같이 `name` 을 지정할 수 있다.

  - 해당 bean의 `name`을 `myThing`으로 지정

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean(name = "myThing")
      public Thing thing() {
          return new Thing();
      }
  }
  ```

<br>

### 10.3.6 Bean Aliasing

- 다음과 같이 배열로 값을 넣어서 `name`에 여러 개의 값을 지정할 수 있다.

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean({"dataSource", "subsystemA-dataSource", "subsystemB-dataSource"})
      public DataSource dataSource() {
          // instantiate, configure and return DataSource bean...
      }
  }
  ```

<br>

### 10.3.7  Bean Description

- `@Bean` annotation 다음에 `@Description` annotation을 지정하여 해당 Bean에 대한 설명 내용을 기술할 수 있다.

- Spring보다는 사람이 보기 편하기 위해서 해당 Bean의 설명 내용을 적는다.

  ```java
  @Configuration
  public class AppConfig {
  
      @Bean
      @Description("Provides a basic example of a bean")
      public Thing thing() {
          return new Thing();
      }
  }
  ```

<br>

## 10.4 Composing Java-based Configurationos

- Java-based Configurations 조합

- 먼저 `@Configuration` 를 설정하여 `ConfigA` 클래스를 만든다.

- 설정한 Configuration을 `@Import(ConfigA.class)`를 이용해 불러올 수 있다.

- `@ComponentScan`을 하면 굳이 Import 할 필요는 없기는 하다.

- 테스트 코드를 만들 때 `@Import`를 이용해 외부 클래스를 가져오곤 한다.

  ```java
  @Configuration
  public class ConfigA {
  
      @Bean
      public A a() {
          return new A();
      }
  }
  
  @Configuration
  @Import(ConfigA.class)
  public class ConfigB {
  
      @Bean
      public B b() {
          return new B();
      }
  }
  ```

  ```java
  public static void main(String[] args) {
      ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);
  
      // now both beans A and B will be available...
      A a = ctx.getBean(A.class);
      B b = ctx.getBean(B.class);
  }
  ```

<br>

### 10.4.1 Conditionally Include `@Configuration` Classes or `@Bean` Methods

- 조건절을 통해서 `@Configuration`을 설정한 클래스나 `@Bean`을 설정한 메서드를 가져올 수 있다.

  ```java
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      // Read the @Profile annotation attributes
      MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Profile.class.getName());
      if (attrs != null) {
          for (Object value : attrs.get("value")) {
              if (context.getEnvironment().acceptsProfiles(((String[]) value))) {
                  return true;
              }
          }
          return false;
      }
      return true;
  }
  ```

- SpringBoot에서는 `@Conditional` annotation을 통해서 많은 설정을 하고 있다.

<br>

 ### 10.4.2 XML-centric Use of `@Configuration` Classes

- XML 중심의 `@Configuration` 클래스 사용

  ```java
  <beans>
      <!-- enable processing of annotations such as @Autowired and @Configuration -->
      <context:annotation-config/>
      <context:property-placeholder location="classpath:/com/acme/jdbc.properties"/>
  
      <bean class="com.acme.AppConfig"/>
  
      <bean class="org.springframework.jdbc.datasource.DriverManagerDataSource">
          <property name="url" value="${jdbc.url}"/>
          <property name="username" value="${jdbc.username}"/>
          <property name="password" value="${jdbc.password}"/>
      </bean>
  </beans>
  ```

- 위와 같이 XML에서 `<context:property-placeholder>`에서 특정 classpath값을 이용해서 `properties` 값을 가져올 수 있다.