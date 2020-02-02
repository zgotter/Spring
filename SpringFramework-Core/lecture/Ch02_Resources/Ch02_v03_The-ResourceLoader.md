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

  