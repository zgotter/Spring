# Ch01. IoC Container

# v09. Using JSR 330 Standard Annotations

- JSR 330 표준 annotation 사용
- 자바 진영의 Spring에서는 JSR 330 표준을 일부 따르고 있다.

<br>

## 9.1 JSR 330

- `javax.inject`라는 groupId를 가지고 있는 스펙(spec)

- 스펙이므로 interface만 있다.

- 아래의 dependency를 추가하여 사용할 수 있다.

  ```xml
  <dependency>
      <groupId>javax.inject</groupId>
      <artifactId>javax.inject</artifactId>
      <version>1</version>
  </dependency>
  ```

<br>

### 9.1.1 `javax.inject`

- `javax.inject` 에 있는 interface들은 Spring에서 기존에 사용하고 있는 것들을 자바 표준 기술로 interface만 추가한 것들이다.
- 해당 인터페이스들도 다음의 3가지 레벨에서 사용 가능하다.
  - Field Level
  - Setter Level
  - Constructor Level

<br>

### 9.1.2 `@Inject`

- `@Autowired` 기능을 대신한다.

<br>

### 9.1.3 `@Named`

- `@Component` 기능을 대신한다.

<br>

## 9.2 JSR 330의 제한적인 기능

- Spring annotation 중 `@Values`, `@Required`, `@Lazy` 에 매칭되는 annotation이 없다.
- JSR 330 표준을 따르기 보단 Spring에서 제공하는 기술들을 온전히 사용하는 것을 선호한다.

<br>

### 9.2.1 Spring vs `javax.inject.*`

| Spring                | `javax.inject.*`          |
| :-------------------- | :------------------------ |
| `@Autowired`          | `@Inject`                 |
| `@Component`          | `@Named` / `@ManagedBean` |
| `@Scope("singleton")` | `@Singleton`              |
| `@Qualifier`          | `@Qualifier` / `@Named`   |
| `@Value`              | -                         |
| `@Required`           | -                         |
| `@Lazy`               | -                         |
| `ObjectFactory`       | `Provider`                |