# Ch02. Resources

# v01. The Resource Interface

## 1.1 Resource란?

- Spring에서 Java 파일 이외에도 여러 종류의 파일을 사용하게 된다.
- Spring에서는 Resource라는 인터페이스를 구현해 추상화를 해줬다.
- Spring을 사용하지 않았을 때와 사용했을 때의 Resource의 활용을 실습으로 확인

<br>

## 1.2 Resource Interface

- Resource 인터페이스는 `InputSTreamSource`를 상속받으며 총 7개의 메서드로 구성되어 있다.

  ```java
  public interface Resource extends InputStreamSource {
  
      // exists() : 리소스가 있는 지 없는 지 확인**
      boolean exists(); 
  
      // isOpen() : Stream이 열렸는 지에 대한 체크
      boolean isOpen();
  
      // getURL() : 해당 리소스의 URL 가져오기**
      URL getURL() throws IOException; 
  
      // getFile() : 해당 리소스의 파일 가져오기
      File getFile() throws IOException;
  
      // createRelative() : 상대경로를 이용해 다른 리소스 가져오기**
      Resource createRelative(String relativePath) throws IOException;
  
      // getFilename() : 파일명 가져오기
      String getFilename();
  
      // getDescription() : 파일 설명 가져오기
      String getDescription();
  }
  ```

- `InputStreamSource`는 `InputStream`을 갖고 오는 추상 메서드가 정의되어 있다.

- `InputStream`을 가져올 수 있으므로 Byte 배열을 쉽게 가져올 수 있다.

  ```java
  public interface InputStreamSource {
  
      InputStream getInputStream() throws IOException;
  }
  ```

  

 

