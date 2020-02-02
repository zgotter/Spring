# Ch02. Resources

# v03. The `ResourceLoader`

- `Resource` 구현체를 사용하는 것보다 `ResourceLoader`를 통해서 리소스를 가져온다.

  ```java
  public interface ResourceLoader {
  
      Resource getResource(String location);
  }
  ```

- 대부분의 `ApplicationContext` 들은 `ResourceLoader`를 인터페이스로 상속받고 있다.

  ```java
  Resource template = ctx.getResource("some/resource/path/myTemplate.txt");
  ```

<br>

## 3.1 `ResourceLoader`의 사용

### 3.1.1 Resources strings 문법

| Prefix       | Example                          | Explanation                                |
| ------------ | -------------------------------- | ------------------------------------------ |
| `classpath:` | `classpath:com/myapp/config.xml` | - classpath ResourceLoader로 부터 읽어들임 |
| `file:`      | `file:///data/config.xml`        | - `file:` 다음에 절대경로 입력             |
| `http:`      | `https://myserver/logo.png`      | - 외부에 있는 파일을 사용할 때             |
| `(none)`     | `/data/config.xml`               |                                            |

