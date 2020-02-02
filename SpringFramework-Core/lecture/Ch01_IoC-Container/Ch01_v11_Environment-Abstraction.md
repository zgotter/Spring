# Ch01. IoC Container

# v11. Environment Abstraction

- 환경에 대한 추상

<br>

**`Environment` 인터페이스**

- profiles`나 `properties` 처럼 시스템 환경 변수와 어플리케이션의 프로필의 정보를 관리할 때 주로 사용된다.
- [`Environment` API](<https://docs.spring.io/spring-framework/docs/5.2.3.RELEASE/javadoc-api/org/springframework/core/env/Environment.html>)
  - `getActiveProfiles()` 나 `getDefaultProfiles()` 메서드를 이용해 프로필 정보를 받을 수 있다.

<br>

## 11.1 Bean Definition Profiles

### 11.1.1 프로필이 필요한 이유

- **개발 단계**
  - `default` profile을 사용 (아무것도 안 준 것)
- **개발 서버에 들어갈 때**
  - `demo` profile 사용
- **운영에 올라가기 전, 운영 DB를 바라볼 때**
  - `stage`라는 profile 사용
- **운영 단계**
  - `product` profile 사용

- 특정 프로필을 받은 어플리케이션은 각각의 프로필에 맞는 환경을 설정하고 그에 맞게끔 동작을 하게 된다.

<br>

### 11.1.2 환경이 필요한 이유

- 예를 들어, 개발 서버에 있을 때에는 개발 DB를 바라봐야 되고, production 서버에서는 운영 DB를 바라봐야 하는 등의 환경 설정이 필요하다.
- Spring framework를 사용하게 된다면 이러한 환경 설정을 profile을 통해 할 수 있다는 장점이 있다. 

<br>

### 11.1.3 `DataSource`

- [`javax.sql.DataSource` API](<https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html>)
- jdbc에서 Connection open을 하면 하나의 connection만 연결 된다.
- 하지만, 웹 서버에서는 여러 명의 request가 동시에 들어오고 여러 명의 request에 맞게끔 적절히 DB 자원을 사용해야 할 필요가 있다.
- 이 때 하나의 Connection만을 가지고 여러 명의 사용자를 관리할 수 없다.
- 그렇기 때문에 `DataSource`라는 인터페이스를 만들고 이 `DataSource`는 여러 개의 Connection을 관리하는 기능을 하고 있다.
- 여러 Connection들을 이 `DataSource`에서 사용을 하고 자원을 반납하는 등의 일련의 작업을 거치게 된다.
- `DataSource`는 JDBC Connection과 비슷하다고 보면 된다.

<br>

### 11.1.4 Bean Profiles 설정을 통한 DataSource 관리

```java
@Bean
public DataSource dataSource() { // 개발
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.HSQL)
        .addScript("my-schema.sql")
        .addScript("my-test-data.sql")
        .build();
}
```

```java
@Bean(destroyMethod="")
public DataSource dataSource() throws Exception {
    Context ctx = new InitialContext();
    return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
}
```

<br>

### 11.1.5 Using `@Profile`

- 클래스의 `@Configuration` annotation 아래에 `@Profile` annotation을 붙이면 해당 클래스를 읽을 때에는 해당 프로필에 적용이 된다.

  ```java
  @Configuration
  @Profile("development") // 개발
  public class StandaloneDataConfig {
  
      @Bean
      public DataSource dataSource() {
          return new EmbeddedDatabaseBuilder()
              .setType(EmbeddedDatabaseType.HSQL)
              .addScript("classpath:com/bank/config/sql/schema.sql")
              .addScript("classpath:com/bank/config/sql/test-data.sql")
              .build();
      }
  }
  ```

  ```java
  @Configuration
  @Profile("production") // 운영
  public class JndiDataConfig {
  
      @Bean(destroyMethod="")
      public DataSource dataSource() throws Exception {
          Context ctx = new InitialContext();
          return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
      }
  }
  ```

 <br>

### 11.1.6 프로필에 대한 설정 :  `!`, `&`, `|`

- `!`
  - A logical "not" of the profile
  - `!production`
    - production이 아닌 경우에 대해 해당 프로필을 적용한다.
- `&`
  - A logical "and" of the profile
  - `dev&production`
    - dev와 production 프로필에 대해서 적용한다.
- `|`
  - A logical "or" of the profiles
  - `dev | production`
    - dev와 production 중 하나의 프로필에 대해서 적용한다.

<br>

### 11.1.7 XML Bean으로 프로필 설정 (XML Bean Definititon Profiles)

- `<beans>` element 하위에 또 다른 `<beans>` element를 정의하고 해당 element에 `profile` 이라는 속성을 입력하고 profile 내용을 정의하여 특정 profile에 맞게끔 동작시킬 수 있다.

  ```java
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:jdbc="http://www.springframework.org/schema/jdbc"
      xmlns:jee="http://www.springframework.org/schema/jee"
      xsi:schemaLocation="...">
  
      <!-- other bean definitions -->
  
      <beans profile="development">
          <jdbc:embedded-database id="dataSource">
              <jdbc:script location="classpath:com/bank/config/sql/schema.sql"/>
              <jdbc:script location="classpath:com/bank/config/sql/test-data.sql"/>
          </jdbc:embedded-database>
      </beans>
  
      <beans profile="production">
          <jee:jndi-lookup id="dataSource" jndi-name="java:comp/env/jdbc/datasource"/>
      </beans>
  </beans>
  ```

<br>

### 11.1.8 Activating a Profile

**방법 1**

-  `getEnvironment()`와 `setActiveProfiles()` 메서드 이용

  ```java
  AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
  ctx.getEnvironment().setActiveProfiles("development");
  ctx.register(SomeConfig.class, StandaloneDataConfig.class, JndiDataConfig.class);
  ctx.refresh();
  ```

<br>

**방법 2**

- `spring.profiles.active` 라는 property 사용

  ```java
  -Dspring.profiles.active="profile1,profile2"
  ```

<br>
**방법 3**

- `@ActiveProfiles` annotation 사용
- 테스트 코드를 작성할 때 주로 사용한다.

<br>

- 주로 방법 2 를 많이 사용한다.
  - JVM을 띄울 때 이 값을 넣어주기 편하기 때문

<br>

### 11.1.9 Default Profile

```java
@Configuration
@Profile("default")
public class DefaultDataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .build();
    }
}
```

<br>

### 11.1.10 Profile 실습

- XML 파일에 2개의 프로필(default, dev) 생성

- 인텔리제이에서 실행 시 "Run/Debug Configurations" 에서 "VM options" 부분에 실행하고자 하는 프로필에 맞는 명령어 입력

  - default 프로필 실행 시

    ```
    -Dspring.profiles.active=default
    ```

  - dev 프로필 실행 시

    ```
    -Dspring.profiles.active=dev
    ```

- 또는 Main에 아래 내용을 추가하여 실행하고자 하는 프로필을 설정할 수 있다.

  - default 프로필 실행 시

    ```java
    context.getEnvironment().setActiveProfiles("default");
    ```

  - dev 프로필 실행 시

    ```java
    context.getEnvironment().setActiveProfiles("dev");
    ```

    