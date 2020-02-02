# Ch02. Resources

# v02. Built-in Resource Implementations

- 인터페이스는 대부분 바로 사용하지 않고 구현체를 사용한다.
- `Resource` 를 구현한 구현체를 사용하게 된다.
- Spring이 제공하는 구현체는 아래와 같다.
  - `UrlResource`
  - `ClassPathResource`
  - `FileSystemResource`
  - `ServletContextResource`
  - `InputStreamResource`
  - `ByteArrayResource`
- 보통 `Resource` 인터페이스를 통해 직접 프로그래밍하기 보다는 SpringBoot에서 사용하는 특정 관례를 따라서 특정 파일을 읽어들일 때 `Resource` 인터페이스를 사용하게 된다.
  - 이 관례는 ResourceLoader에서 사용하는 관례이다.
- 파일과 관련된 것이기 때문에 `ClassPathResource`를 주로 사용하게 된다.

<br>

## 2.1 `UrlResource`

- `file:` 또는 `http:` 와 같은 식으로 프로토콜에 명시를 해주고 해당 리소스의 위치를 알려주는 URL 방식을 통해 리소스의 위치를 알려주는 방식

<br>

## 2.2 `FileSystemResource`

- ClassLoader를 통해서 프로젝트에 있는 파일들을 `classpath`를 기준으로 파일의 경로를 읽어들이는 방식
- 거의 사용하지 않는다.

<br>

## 2.3 `ServletContextResource`

- 웹 프로젝트인 경우 주로 사용

<br>

## 2.4 `InputStreamResource`

- InputStream을 넣어주고 사용할 수 있는 Resource

<br>

## 2.5 `ByteArrayResource`

- Byte Array를 넣어주고 사용할 수 있는 Resource
- 파일을 Writing할 때 사용하면 편하다.
- Spring에서는 외부 파일을 읽어들일 떄 주로 Resource를 사용하므로 해당 구현체는 잘 사용되지 않는다.

<br>

## 2.6 `ClassPathResource`

- 코딩을 통해 실습