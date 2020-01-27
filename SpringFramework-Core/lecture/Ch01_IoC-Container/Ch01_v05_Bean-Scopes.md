# Ch01. IoC Container

## 1.5 Bean Scopes

- DI와 같이 Spring의 핵심적인 내용은 아니다.
- Bean Scopes 는 Bean 의 범위에 대한 내용을 다루고 있다.
- 컨테이너가 이 객체를 만들 때 한 번만 호출하는 지, 아니면 호출마다 객체를 여러 번 만드는 지

<br>

### 1.5.1 Bean Scope의 종류

| Scope       | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| singleton   | - Spring IoC Container에서 호출될 때 하나의 객체만을 만들어 재사용<br />- prototype과 함께 실무에서 99% 사용됨 |
| prototype   | - IoC Container에 객체를 호출할 때 매번 객체를 생성<br />- 클래스 이름에 `new`를 붙여 객체를 생성하는 것과 동일 <br />singleton과 함께 실무에서 99% 사용됨 |
| request     |                                                              |
| session     |                                                              |
| application |                                                              |
| websocket   |                                                              |

<br>

### 1.5.2 객체의 동일성(identity)과 동등성(equals)

- `src > test > java > test` 패키지 참고

- test 코드를 위한 dependency 및 plugin 추가

  - junit maven dependency 추가

    ```xml
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-launcher</artifactId>
        <version>1.6.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.6.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>5.6.0</version>
        <scope>test</scope>
    </dependency>
    ```

  - test에 대한 plugin 추가

    ```xml
    <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.2</version>
    </plugin>
    <plugin>
        <artifactId>maven-failsafe-plugin</artifactId>
        <version>2.22.2</version>
    </plugin>
    ```

- `equals()`, `hashCode()` 메서드를 overriding 하여 동등성을 확인할 수 있다.
  (`@EqualsAndHashCode` lombok 으로 대체 가능)

<br>

### 1.5.3 Scope의 동일성과 동질성

- bean의 default scope은 `singleton`이다. (동일성 보장)
- spring에서 객체를 가져오고 싶을 때 각각 다른 객체가 생성되고 싶은 경우에는 scope를 `prototype`으로 지정하면 된다.

<br>

### 1.5.4 Singleton Scope

![singleton scope](<https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/images/singleton.png>)

 <br>

### 1.5.5 Prototype Scope

![prototype scope](https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/images/prototype.png)

<br>

### 1.5.6 Singleton Beans with Prototype-bean Dependencies

- bean들이 의존성을 가지고 있을 때 prototype으로 만든 bean들을 가지고 의존성을 주입시키면 관리가 힘들어진다.
- 그러므로 설정 시 singleton인 bean을 많이 사용하게 된다.