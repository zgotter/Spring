# Ch01. IoC Container

# v08. Classpath Scanning and Managed Components

## 8.1 Classpath Scanning

- 자바는 classpath 이후로부터 class 파일 및 리소스 파일을 읽을 수 있다.
- 특정 classpath 이후로 관리할 component들을 scanning을 한다.

<br>

- 지금까진 명시적으로 XML에 bean을 정의 또는 annotation Bean을 이용해 명시적으로 bean을 등록함
- 위의 방법이 아닌 class 레벨에 Component라는 annotation을 붙여줌으로서 특정 클래스를 bean으로 등록시켜야 할 것이다라고 알려준다.
- 이때 패키지명.클래스명으로 bean을 등록시켜 주는 것이 아닌 특정 classpath 이하에 있는 component annotation이 붙은 클래스들은 bean으로 등록될 것이라고 자동으로 scanning하여 등록하는 것이다.

<br>

## 8.2 `@Component`

- [java api component document](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html)
- class에 annotate 된다.
- 자동으로 detecting 된다.

<br>

### 8.2.1 Marker Interface

- 특정 annotation이 붙은 component들은 이런 역할을 한다라는 특징적인 것들을 알려주게 된다.
- 종류
  - `@Repository`
  - `@Service`
  - `@Controller`
  - `@ClassPathBeanDefinitionScanner`

<br>

**1) `@Repository`**

- 자동으로 bean에 등록될 component
- 특징
  - DAO처럼 Database에 access할 때 필요한 bean이라고 지정

<br>

**2) `@Service`**

- Business Logic 같은 기능을 수행

<br>

**3) `@Controller`**

- Spring MVC에서 학습할 내용

<br>

## 8.3 Automatically Detecting Classes and Registering Bean Definitions

### 8.3.1 `@Component` annotation을 이용한 classpath scanning bean 등록

```java
@Service
public class SimpleMovieLister {

    private MovieFinder movieFinder;

    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
```

```java
@Repository
public class JpaMovieFinder implements MovieFinder {
    // implementation elided for clarity
}
```

```java
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig  {
    // ...
}
```

- 위에서 언급된 annotation들은 기존의 XML을 통한 bean 설정 중에 다음의 로직이 추가되어 있어야 사용이 가능하다.

  ```xml
  <context:annotation-config/>
  ```

<br>

### 8.3.2 XML을 이용한 classpath scanning bean 등록

- XML에 아래 내용을 등록해줘야 사용할 수 있다.

  ```xml
  <context:component-scan base-package="org.example"/>
  ```

<br>

### 8.3.3 XML을 전혀 사용하지 않는 classpath scanning bean 등록

- Main에서 `ClassPathXmlApplicationContext` 대신 `AnnotationConfigApplicationContext`를 사용
- `AnnotationConfigApplicationContext` 의 매개변수로 basePackages 경로 입력

<br>

## 8.4 Using Filters to Customize Scanning

### 8.4.1 필터의 종류

1. `includeFilters`
2. `excludeFilters`

<br>

### 8.4.2 필터의 타입

1. annotation (default)
2. assignable
3. aspectj
4. regex (많이 사용됨)
5. custom