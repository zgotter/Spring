# 3. lombok

## 3.1 코드 리펙토링

- 2.2 에서 생성한 `Main.java` 코드 리펙토링
- `Main.java` 참고



### 3.1.1 intellij 단축키 : `Ctrl + Shift + A`

- Action 검색
- `constructor`
  - 맴버변수를 선택하면 해당 맴버 변수 값을 초기화하는 생성자 코드가 자동으로 생성된다.

- `overriding method`
  - overriding 가능한 메서드를 선택할 수 있다.



### 3.1.2 Local History

- intellij 에서 java 파일 안에서 우클릭을 한 후, `Local History > Show History` 메뉴를 선택하면 해당 파일의 변경 이력을 확인할 수 있다.



## 3.2 lombok

- [Project Lombok](<https://projectlombok.org/>)
- lombok 은 자바 라이브러리에 코드를 자동으로 생성 해주는 프로그램이다.
- maven이나 gradle 과 같은 build 툴이 build를 할 때 lombok 프로그램이 동작한다.



### 3.2.1 lombok 설치

- `pom.xml`에 lombok dependency 추가

  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.10</version>
      <scope>provided</scope>
  </dependency>
  ```



### 3.2.2 lombok Features

- `@Getter`, `@Setter`



### 3.2.3 intellij lombok 사용 설정

- intellij 에 lombok을 사용한다고 알려줘야 한다.



1. intellij 에서 lombok plugin 설치

2. `File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors` 에서 `Enable annotation processing` 체크



### 3.2.4 lombok 사용

- `Refactor` 메뉴의 최하단에서 lombok plugin을 통해 `Lombok`, `Delombok` 메뉴를 사용할 수 있다.
- `Delombok` 메뉴를 선택하면 현재 사용 중인 lombok 들이 표시된다. 
- 여기서 특정 lombok 을 클릭하면 같은 기능을 하는 메서드들이 생성된다.



#### 3.2.4.1  `@Getter`, `@Setter` 

- 클래스의 맴버 변수 초기화 시 맨 앞에 `@Getter`, `@Setter` 를 명시하여 Getter, Setter 메서드를 사용할 수 있다. 
- 모든 필드에 대해 Getter, Setter 메서드를 사용하려면 class 밖 윗부분에 클래스 레벨로 `@Getter`, `@Setter` 를 명시하면 된다.



#### 3.2.4.2 `@ToString`

- Object 객체의 `toString()` 메서드를 직접 overriding 하지 않고 클래스 레벨에 `@ToString` lombok을 사용하면 맴버의 클래스명, 그 안에 필드들이 명시된 `toString()` 메서드가 overriding된다.



#### 3.2.4.3 `@NoArgsConstructor`

- 기본 생성자 생성



#### 3.2.4.4 `@AllArgsConstructor`

- 모든 맴버 변수를 초기화하는 생성자 생성



#### 3.2.4.5 `@RequiredArgsConstructor`, `@NonNull`

- `@RequiredArgsConstructor` 를 클래스 레벨에 선언
- `@NonNull` annotation 이 지정된 맴버 변수들을 초기화하는 생성자 생성
- `@NonNull` 은 지정된 맴버 변수의 `Null` 여부를 체크한다.



#### 3.2.4.6 `@EqualsAndHashCode`

- Object 객체의 overriding 대상 메서드 중 `equals()` 메서드와 `hashCode()` 메서드를 생성



#### 3.2.4.7 `@Data`

- 위의 모든 Annotation 들을 각각 정의한 것과 동일한 기능을 동작