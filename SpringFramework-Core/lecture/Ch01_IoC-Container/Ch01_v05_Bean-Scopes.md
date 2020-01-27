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



